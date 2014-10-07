package my.com.myriadeas.integral.rest.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import my.com.myriadeas.spring.context.ContextInitializationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Main configuration file. Define default implementation and configuration for
 * bean.
 * 
 * @author hutingung
 * 
 */

@ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = { @Filter(Configuration.class) })
@org.springframework.context.annotation.ImportResource(value = { "classpath:my/com/myriadeas/integral/rest/config/applicationContext.xml" })
@org.springframework.context.annotation.Import(value = { WebMvcConfig.class })
public abstract class MainConfig implements Config {

	private static final Logger logger = LoggerFactory
			.getLogger(MainConfig.class);

	@Autowired
	protected Environment env;

	@PostConstruct
	public void init() {
		checkProfileInitialization();
	}

	public void checkProfileInitialization() {
		if (env == null || env.getActiveProfiles() == null
				|| env.getActiveProfiles().length == 0) {
			throw new ContextInitializationException(
					"No profile defined. Not allowed. ");
		}
		if (env.getActiveProfiles().length != 1) {
			throw new ContextInitializationException(
					"More than one profile defined. Not allowed. Profiles defined= "
							+ env.getActiveProfiles().length);
		}
		logger.info("Profile defined ={} ", env.getActiveProfiles()[0]);
	}

	@Bean
	public DataSource dataSource() {
		org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
		dataSource.setDriverClassName(env
				.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}
}
