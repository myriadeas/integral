package my.com.myriadeas.integral.data.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile(TEST)
public class JpaInfrastructureConfigTest extends
		AbstractJpaInfrastructureConfig implements JpaInfrastructureConfig {

	private static Logger logger = LoggerFactory
			.getLogger(JpaInfrastructureConfigTest.class);


	@Override
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl("jdbc:hsqldb:hsql://localhost/");
		dataSource.setUsername("SA");
		dataSource.setPassword("");
		logger.debug("Use dataSource={}", dataSource);
		return dataSource;

	}

}
