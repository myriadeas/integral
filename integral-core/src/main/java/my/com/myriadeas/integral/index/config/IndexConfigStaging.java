package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.xml.sax.SAXException;

@Import(value = { JpaInfrastructureConfigStaging.class,
		IntegralMysticRouteConfigImpl.class })
@PropertySource(name = "properties", value = { "classpath:config-staging.properties" })
@Configuration
@Profile(STAGING)
public class IndexConfigStaging extends IndexCommonConfig {

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		HttpSolrServer solrServer = new HttpSolrServer(this.solrServerUrl);
		return solrServer;
	}
}
