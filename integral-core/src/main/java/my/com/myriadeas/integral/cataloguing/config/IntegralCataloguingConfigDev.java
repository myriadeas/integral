package my.com.myriadeas.integral.cataloguing.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.core.config.IntegralConfigDev;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@Configuration
@Profile(DEV)
@Import(value = { JpaInfrastructureConfigDev.class,
		IntegralMysticRouteConfigImpl.class, IntegralConfigDev.class })
public class IntegralCataloguingConfigDev extends AbstractIntegralCataloguingConfig
		implements IntegralCataloguingConfig {

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {

		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		return factory.getSolrServer();

	}

}
