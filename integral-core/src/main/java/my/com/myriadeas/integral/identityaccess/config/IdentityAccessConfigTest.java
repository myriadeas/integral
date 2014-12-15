package my.com.myriadeas.integral.identityaccess.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigTest;
import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.core.publisher.PublisherImpl;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigTest.class,
		IntegralMysticRouteConfigImpl.class })
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.identityaccess",
		"my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.internalization" }, excludeFilters = { @Filter(Configuration.class) })
@EnableJpaRepositories(basePackages = { "my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa" })
@EnableSpringConfigured
@Configuration
@Profile(TEST)
@EnableTransactionManagement
@ImportResource(value = { "classpath:META-INF/spring/identityAccessServiceRouteContext.xml" })
public class IdentityAccessConfigTest {

	@Autowired
	@Qualifier("identityAccessProducerTemplate")
	private ProducerTemplate identityAccessProducerTemplate;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Publisher identityAccessPublisher() {
		return new PublisherImpl(identityAccessProducerTemplate,
				"identityAccess");
	}

}