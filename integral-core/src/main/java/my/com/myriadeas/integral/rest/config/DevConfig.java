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
package my.com.myriadeas.integral.rest.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.index.service.impl.IntegralSolrIndexer;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.xml.sax.SAXException;

@Configuration
@PropertySource(name = "properties", value = {
		"classpath:my/com/myriadeas/integral/index/config/config-dev.properties",
		"classpath:config.properties" })
@Profile(SpringEnvironmentUtil.DEV)
@Import(value = { JpaInfrastructureConfigDev.class })
@EnableSolrRepositories(basePackages = { "my.com.myriadeas.integral.data.solr" })
public class DevConfig extends MainConfig implements Config,
		my.com.myriadeas.integral.cataloguing.config.Config {
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
	@Override
	public DataSource dataSource() {
	//	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	//	builder.setName("dataSource").setType(EmbeddedDatabaseType.HSQL);
	//	for (String script : getScripts()) {
			///TODO - Remove build script. This is not tally with JPA repository
			//builder.addScript("classpath:data/integral/" + script);
	//	}
	//	return builder.build();
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl("jdbc:hsqldb:file:db/testDevConfig");
		return dataSource;
	}

	private String[] getScripts() {
		return new String[] { "ctpont.sql", "ctmatm.sql", "gllibr.sql",
				"gltagp.sql", "glsubf.sql", "glnumb.sql", "ctaudit.sql",
				"ctterm.sql", "ctterm_view.sql", "ctwork.sql" };
	}

	@Value("${solr.server.biblio.url}")
	private String solrServerUrl;

	@Value("${vufind.indexer.properties}")
	private String vufindIndexerProperties;

	@Value("${vufind.indexer.scripts}")
	private String[] vufindIndexerScripts;

	@Bean(name = "solrServer")
	public SolrServer solrServer() throws ParserConfigurationException,
			IOException, SAXException {
		/*
		 * EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
		 * "classpath:my/com/myriadeas/integral/data/solr"); return
		 * factory.getSolrServer();
		 */

		HttpSolrServer server = new HttpSolrServer(solrServerUrl);

		return server;
	}

	@Bean
	public SolrOperations solrTemplate() throws ParserConfigurationException,
			IOException, SAXException {
		return new SolrTemplate(solrServer());
	}

	@Bean
	public IntegralIndexer integralIndexer() throws FileNotFoundException, IOException,
			ParseException {
		return new IntegralSolrIndexer(solrIndexer());
	}

	@Bean
	public SolrIndexer solrIndexer() throws FileNotFoundException, IOException,
			ParseException {
		SolrIndexer indexer = new VuFindIndexer(vufindIndexerProperties,
				vufindIndexerScripts);

		return indexer;
	}

}
