package my.com.myriadeas.integral.cataloguing.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.core.config.IntegralConfigTest;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigTest;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@Configuration
@Profile(TEST)
@Import(value = { JpaInfrastructureConfigTest.class,
		IntegralMysticRouteConfigImpl.class, IntegralConfigTest.class })
public class IntegralCataloguingConfigTest extends
		AbstractIntegralCataloguingConfig implements IntegralCataloguingConfig {

	@Value("${solr.server.biblio.url}")
	private String solrServerUrl;

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {

	//	HttpSolrServer server = new HttpSolrServer(solrServerUrl);
	//	return server;
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		return factory.getSolrServer();
	}

}
