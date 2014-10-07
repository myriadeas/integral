package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.index.domain.service.Indexer;
import my.com.myriadeas.integral.index.domain.service.IndexerImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@ComponentScan(basePackages = "my.com.myriadeas.integral.index", excludeFilters = { @Filter(Configuration.class) })
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.index.infrastructures" })
@EnableSpringConfigured
@Configuration
@Profile(DEV)
public class IndexConfig {
	@Value("${vufind.indexer.properties}")
	private String vufindIndexerProperties;

	@Value("${vufind.indexer.scripts}")
	private String[] vufindIndexerScripts;

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
		return factory.getSolrServer();
	}

	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException {
		return new SolrTemplate(solrServer());
	}

	@Bean
	public Indexer integralIndexer() throws FileNotFoundException,
			IOException, ParseException {
		return new IndexerImpl(solrIndexer());
	}

	@Bean
	public SolrIndexer solrIndexer() throws FileNotFoundException, IOException,
			ParseException {
		SolrIndexer indexer = new VuFindIndexer(vufindIndexerProperties,
				vufindIndexerScripts);

		return indexer;
	}

}
