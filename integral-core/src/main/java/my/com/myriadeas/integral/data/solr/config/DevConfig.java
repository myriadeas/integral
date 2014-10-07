/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package my.com.myriadeas.integral.data.solr.config;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

@PropertySource(name = "properties", value = { "classpath:my/com/myriadeas/integral/data/solr/config/config-dev.properties" })
@Profile(SpringEnvironmentUtil.DEV)
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.data.solr.repository" })
public class DevConfig implements Config {

	/**
	 * This method required to solve property placeholder refer to
	 * http://www.baeldung.com/2012/02/06/properties-with-spring/
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

	@Bean
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"classpath:my/com/myriadeas/integral/data/solr");
		return factory.getSolrServer();
	}

	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException {
		return new SolrTemplate(solrServer());
	}
}
