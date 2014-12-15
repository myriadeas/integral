package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.xml.sax.SAXException;

@Import(value = { JpaInfrastructureConfigDev.class,
		IntegralMysticRouteConfigImpl.class })
@PropertySource(name = "properties", value = { "classpath:config-test.properties" })
@Configuration
@Profile(TEST)
public class IndexConfigTest extends IndexCommonConfig {
	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		HttpSolrServer solrServer = new HttpSolrServer(this.solrServerUrl);
		return solrServer;
	}
}
