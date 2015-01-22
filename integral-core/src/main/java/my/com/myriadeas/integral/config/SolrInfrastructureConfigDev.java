package my.com.myriadeas.integral.config;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

public class SolrInfrastructureConfigDev {

	
	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		SolrServer solrServer = factory.getSolrServer();
		return solrServer;
	}



}
