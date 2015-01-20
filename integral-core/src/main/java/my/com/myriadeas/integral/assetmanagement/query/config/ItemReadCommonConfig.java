package my.com.myriadeas.integral.assetmanagement.query.config;

import java.io.IOException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.ProducerTemplate;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xml.sax.SAXException;

@Import(value = { IntegralMysticRouteConfigImpl.class })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.assetmanagement",
		"my.com.myriadeas.integral.internalization" }, excludeFilters = { @Filter(Configuration.class) })
@EnableJpaRepositories(basePackages = { "my.com.myriadeas.integral.assetmanagement.query.domain" })
@ImportResource(value = { "classpath:META-INF/spring/itemReadServiceRouteContext.xml" })
@EnableSpringConfigured
@Configuration
@EnableTransactionManagement

public class ItemReadCommonConfig {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	@Qualifier("itemReadProducerTemplate")
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
	
	
	

}
