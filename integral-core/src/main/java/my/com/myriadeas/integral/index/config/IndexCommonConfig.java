package my.com.myriadeas.integral.index.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.core.publisher.PublisherImpl;
import my.com.myriadeas.integral.index.IndexConstant;
import my.com.myriadeas.integral.index.domain.service.Indexer;
import my.com.myriadeas.integral.index.domain.service.IndexerImpl;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.camel.ProducerTemplate;
import org.apache.solr.client.solrj.SolrServer;
import org.marc4j.marc.MarcFactory;
import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@Import(value = { IntegralMysticRouteConfigImpl.class})
@ComponentScan(basePackages = { "my.com.myriadeas.integral.index",
		"my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.internalization" }, excludeFilters = { @Filter(Configuration.class) })
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.index.infrastructures.solr" })
@EnableJpaRepositories(basePackages = { "my.com.myriadeas.integral.index.infrastructures.jpa" })
@EnableSpringConfigured
@Configuration
@ImportResource(value = { "classpath:META-INF/spring/indexServiceRouteContext.xml" })
public class IndexCommonConfig {

	
	@Autowired
	@Qualifier("indexProducerTemplate")
	protected ProducerTemplate producerTemplate;
	
	@Value("${vufind.indexer.properties}")
	protected String vufindIndexerProperties;

	@Value("${vufind.indexer.scripts}")
	protected String[] vufindIndexerScripts;

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
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		SolrServer solrServer = factory.getSolrServer();
		return solrServer;
	}

	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException {
		return new SolrTemplate(solrServer());
	}

	@Bean
	public Indexer integralIndexer() throws FileNotFoundException, IOException,
			ParseException {
		return new IndexerImpl(solrIndexer());
	}

	@Bean
	public SolrIndexer solrIndexer() throws FileNotFoundException, IOException,
			ParseException {
		SolrIndexer indexer = new VuFindIndexer(vufindIndexerProperties,
				vufindIndexerScripts);
		return indexer;
	}

	@Bean
	public Publisher indexPublisher() {
		return new PublisherImpl(producerTemplate, IndexConstant.MODULE_NAME);
	}

	@Bean
	public MarcFactory marcFactory() {
		return MarcFactory.newInstance();
	}
}
