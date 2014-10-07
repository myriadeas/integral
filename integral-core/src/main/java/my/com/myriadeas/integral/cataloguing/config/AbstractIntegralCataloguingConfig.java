package my.com.myriadeas.integral.cataloguing.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.index.service.impl.IntegralSolrIndexer;

import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.xml.sax.SAXException;

@Configuration
@ComponentScan(basePackages = { "my.com.myriadeas.integral.marc",
		"my.com.myriadeas.integral.cataloguing", "my.com.myriadeas.integral.dao",
		"my.com.myriadeas.integral.core", "my.com.myriadeas.integral.internalization",
		"my.com.myriadeas.integral.security" }, excludeFilters = { @Filter(Configuration.class) })
@ImportResource(value = { "classpath:META-INF/spring/cataloguingServiceRouteContext.xml" })
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.data.solr" })
public abstract class AbstractIntegralCataloguingConfig implements IntegralCataloguingConfig {

	@Value("${solr.server.biblio.url}")
	private String solrServerUrl;

	@Value("${vufind.indexer.properties}")
	private String vufindIndexerProperties;

	@Value("${vufind.indexer.scripts}")
	private String[] vufindIndexerScripts;


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
