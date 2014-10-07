package my.com.myriadeas.integral.security.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigStaging;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile(STAGING)
@ComponentScan(basePackages = "my.com.myriadeas.integral.security", excludeFilters = { @Filter(Configuration.class) })
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = { JpaInfrastructureConfigStaging.class,
		IntegralCataloguingConfigStaging.class })
public class SecurityConfigDatabaseProviderStaging extends
		GlobalMethodSecurityConfiguration implements IntegralSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * This method required to solve property placeholder refer to
	 * http://www.baeldung.com/2012/02/06/properties-with-spring/ must
	 * instantiate
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

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
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
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
}
