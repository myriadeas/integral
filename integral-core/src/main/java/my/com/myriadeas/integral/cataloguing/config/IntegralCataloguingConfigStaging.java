package my.com.myriadeas.integral.cataloguing.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.core.config.IntegralConfigStaging;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.xml.sax.SAXException;

@Configuration
@Profile(STAGING)
@Import(value = { JpaInfrastructureConfigStaging.class,
		IntegralMysticRouteConfigImpl.class, IntegralConfigStaging.class })
public class IntegralCataloguingConfigStaging extends
		AbstractIntegralCataloguingConfig implements IntegralCataloguingConfig {
	
	@Value("${solr.server.biblio.url}")
	private String solrServerUrl;

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {

		HttpSolrServer server = new HttpSolrServer(solrServerUrl); 
		return server;

	}

}
