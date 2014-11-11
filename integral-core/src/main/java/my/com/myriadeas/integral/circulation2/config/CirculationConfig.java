package my.com.myriadeas.integral.circulation2.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import java.util.Map;

import my.com.myriadeas.integral.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.publisher.Publisher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigDev.class })
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.circulation2" }, excludeFilters = { @Filter(Configuration.class) })
@EnableJpaRepositories(basePackages = { "my.com.myriadeas.integral.circulation2.infrastructure.jpa" })
@EnableSpringConfigured
@Configuration
@Profile(DEV)
@EnableTransactionManagement
public class CirculationConfig {

	/**
	 * This method required to solve property placeholder refer to
	 * http://www.baeldung.com/2012/02/06/properties-with-spring/
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

	@Bean
	public Publisher publisher() {
		return new Publisher() {

			@Override
			public void publish(String destination, Object domainEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void publish(Map<String, DomainEvent> events) {
				// TODO Auto-generated method stub

			}

		};
	}
}
