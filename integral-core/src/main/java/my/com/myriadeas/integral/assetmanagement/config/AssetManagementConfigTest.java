package my.com.myriadeas.integral.assetmanagement.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.config.JpaInfrastructureConfigTest;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xml.sax.SAXException;

@Import(value = { JpaInfrastructureConfigTest.class })
@PropertySource(name = "properties", value = { "classpath:config-test.properties" })
@Configuration
@Profile(TEST)
@EnableTransactionManagement
public class AssetManagementConfigTest extends AssetManagementCommonConfig {

	@Value("${solr.server.biblio.url}")
	protected String solrServerUrl;

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		HttpSolrServer solrServer = new HttpSolrServer(this.solrServerUrl);
		return solrServer;
	}
}
