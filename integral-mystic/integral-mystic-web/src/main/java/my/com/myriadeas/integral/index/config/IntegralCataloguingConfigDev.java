package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfig;
import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.index.service.impl.IntegralSolrIndexer;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigDev;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@Configuration
@Profile(DEV)
@Import(value = { JpaInfrastructureConfigDev.class,
		IntegralMysticRouteConfigDev.class })
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.marc",
		"my.com.myriadeas.integral.cataloguing",
		"my.com.myriadeas.integral.dao", "my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.internalization",
		"my.com.myriadeas.integral.security" }, excludeFilters = { @Filter(Configuration.class) })
@ImportResource(value = { "classpath:META-INF/spring/cataloguingServiceRouteContext.xml" })
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.data.solr" })
public class IntegralCataloguingConfigDev implements IntegralCataloguingConfig {

	@Value("${solr.server.biblio.url}")
	private String solrServerUrl;

	@Value("${vufind.indexer.properties}")
	private String vufindIndexerProperties;

	@Value("${vufind.indexer.scripts}")
	private String[] vufindIndexerScripts;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		// TODO - should use embedded when in integration test and http when in
		// staging mode - change here manually now. Do it in next release
		
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		return factory.getSolrServer();
		
		/*
		HttpSolrServer server = new HttpSolrServer(solrServerUrl); 
		return server;
		*/ 
	}

	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException {
		return new SolrTemplate(solrServer());
	}

	@Bean
	public IntegralIndexer integralIndexer() throws FileNotFoundException, IOException,
			ParseException {
		return new IntegralSolrIndexer(solrIndexer());
	}

	@Bean
	public SolrIndexer solrIndexer() throws FileNotFoundException, IOException,
			ParseException {
		SolrIndexer indexer = new VuFindIndexer(vufindIndexerProperties,
				vufindIndexerScripts);

		return indexer;
	}

}
