package my.com.myriadeas.integral.assetmanager.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import javax.sql.DataSource;

import my.com.myriadeas.integral.assetmanager.AssetManagerConstant;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.core.publisher.PublisherImpl;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigDev.class,
		IntegralMysticRouteConfigImpl.class })
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.assetmanager",
		"my.com.myriadeas.integral.internalization" }, excludeFilters = { @Filter(Configuration.class) })
@EnableJpaRepositories(basePackages = { "my.com.myriadeas.integral.assetmanager.infrastructure" })
@ImportResource(value = { "classpath:META-INF/spring/assetManagerServiceRouteContext.xml" })
@EnableSpringConfigured
@Configuration
@Profile(DEV)
@EnableTransactionManagement
public class AssetManagerConfigDev {

	@Autowired
	DataSource dataSource;

	@Autowired
	@Qualifier("assetManagerProducerTemplate")
	private ProducerTemplate producerTemplate;

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
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate()
			throws Exception {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public SpringEnvironmentUtil envUtil() {
		SpringEnvironmentUtil envUtil = new SpringEnvironmentUtil();
		return envUtil;
	}

	@Bean(name = "assetManagerPublisher")
	public Publisher publisher() {
		return new PublisherImpl(producerTemplate,
				AssetManagerConstant.MODULE_NAME);
	}

}
