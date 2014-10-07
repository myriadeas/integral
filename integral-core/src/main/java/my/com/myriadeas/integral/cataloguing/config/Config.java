package my.com.myriadeas.integral.cataloguing.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.SolrOperations;
import org.xml.sax.SAXException;

public interface Config {
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException;

	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException;

	public IntegralIndexer integralIndexer() throws FileNotFoundException, IOException,
			ParseException;

}
