package my.com.myriadeas.integral.security.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.security.config.IntegralSecurityConfig;
import my.com.myriadeas.integral.security.core.IntegralSuperUserVoter;
import my.com.myriadeas.integral.security.jpa.JpaRepositoryDelegatingMethodSecurityMetadataSource;
import my.com.myriadeas.integral.security.jpa.JpaRepositoryMapBasedMethodSecurityMetadataSource;
import my.com.myriadeas.integral.security.jpa.JpaRepositorySecurityService;
import my.com.myriadeas.integral.security.jpa.JpaRepositorySecurityServiceImpl;
import my.com.myriadeas.integral.security.jpa.JpaRepositoryService;
import my.com.myriadeas.integral.security.jpa.JpaRepositoryServiceImpl;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@Profile(STAGING)
@ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = { @Filter(Configuration.class) })
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = { JpaInfrastructureConfigStaging.class })
public class SecurityConfigStaging extends GlobalMethodSecurityConfiguration
		implements IntegralSecurityConfig, CasSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public JpaRepositoryService jpaRepositoryService() {
		JpaRepositoryService jpaRepositoryService = new JpaRepositoryServiceImpl();
		jpaRepositoryService.setExcludeClasses(AbstractUser.class);
		return jpaRepositoryService;
	}

	@Bean
	public JpaRepositorySecurityService jpaRepositorySecurityService() {
		return new JpaRepositorySecurityServiceImpl();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		CasAuthenticationProvider authenticationProvider = new CasAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setServiceProperties(serviceProperties());
		authenticationProvider
				.setTicketValidator(new Cas20ServiceTicketValidator(
						"https://ec2-54-214-246-116.us-west-2.compute.amazonaws.com:8443/cas"));
		authenticationProvider.setKey("cas");
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
		authenticationProviders.add(authenticationProvider());
		return new ProviderManager(authenticationProviders);
	}

	@Bean
	public MethodSecurityMetadataSource methodSecurityMetadataSource() {
		DelegatingMethodSecurityMetadataSource delegatingMethodSecurityMetadataSource = (DelegatingMethodSecurityMetadataSource) super
				.methodSecurityMetadataSource();
		return new JpaRepositoryDelegatingMethodSecurityMetadataSource(
				delegatingMethodSecurityMetadataSource
						.getMethodSecurityMetadataSources());
	}

	@Bean
	public JpaRepositoryMapBasedMethodSecurityMetadataSource jpaRepositoryMapBasedMethodSecurityMetadataSource() {
		JpaRepositoryMapBasedMethodSecurityMetadataSource methodSecurityMetadataSource = new JpaRepositoryMapBasedMethodSecurityMetadataSource(
				jpaRepositorySecurityService().getMethodMap());
		return methodSecurityMetadataSource;
	}

	/**
	 * Provides a custom {@link MethodSecurityMetadataSource} that is registered
	 * with the {@link #methodSecurityMetadataSource()}. Default is null.
	 * 
	 * @return a custom {@link MethodSecurityMetadataSource} that is registered
	 *         with the {@link #methodSecurityMetadataSource()}
	 */
	protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
		// Removed security on jpa repository. Secure from rest api request only
		return null;
		// return jpaRepositoryMapBasedMethodSecurityMetadataSource();
	}

	@SuppressWarnings("rawtypes")
	protected AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
		ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
		expressionAdvice.setExpressionHandler(getExpressionHandler());
		RoleVoter permVoter = new RoleVoter();
		permVoter.setRolePrefix("PERM_");
		decisionVoters.add(new IntegralSuperUserVoter());
		decisionVoters.add(new PreInvocationAuthorizationAdviceVoter(
				expressionAdvice));
		decisionVoters.add(permVoter);
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		return new AffirmativeBased(decisionVoters);
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties
				.setService("http://ec2-54-214-246-116.us-west-2.compute.amazonaws.com:8080/integral-mystic/j_spring_cas_security_check");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	public CasAuthenticationFilter authenticationFilter() {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter
				.setAuthenticationManager(authenticationManager());
		return casAuthenticationFilter;
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint
				.setLoginUrl("https://ec2-54-214-246-116.us-west-2.compute.amazonaws.com:8443/cas/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

}
