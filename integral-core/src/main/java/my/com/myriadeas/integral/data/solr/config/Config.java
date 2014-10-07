package my.com.myriadeas.integral.data.solr.config;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.SolrOperations;
import org.xml.sax.SAXException;

public interface Config {
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException;

	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException;

}
