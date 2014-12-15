package my.com.myriadeas.integral.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import javax.sql.DataSource;

import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile(STAGING)
public class JpaInfrastructureConfigStaging extends
		AbstractJpaInfrastructureConfig implements JpaInfrastructureConfig {

	private static Logger logger = LoggerFactory
			.getLogger(JpaInfrastructureConfigStaging.class);

	@Value("${database.url}")
	private String databaseUrl;

	@Value("${database.username}")
	private String databaseUsername;

	@Value("${database.password}")
	private String databasePassword;

	private static final String databaseDriverClassName = "com.mysql.jdbc.Driver";

	@Override
	@Bean
	public DataSource dataSource() {
		DataSource dataSource;
		DriverAdapterCPDS cpds = new DriverAdapterCPDS();
		try {
			cpds.setDriver(databaseDriverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		cpds.setUrl(databaseUrl);
		cpds.setUser(databaseUsername);
		cpds.setPassword(databasePassword);
		SharedPoolDataSource tds = new SharedPoolDataSource();
		tds.setConnectionPoolDataSource(cpds);
		tds.setMaxActive(10);
		tds.setMaxWait(50);
		dataSource = tds;
		logger.debug("Use dataSource={}", dataSource);
		return dataSource;
	}

	@Override
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(dataSource());
		return factory;
	}

}
