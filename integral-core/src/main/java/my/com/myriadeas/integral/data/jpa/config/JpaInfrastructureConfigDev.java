package my.com.myriadeas.integral.data.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile(DEV)
public class JpaInfrastructureConfigDev extends AbstractJpaInfrastructureConfig
		implements JpaInfrastructureConfig {

	private static Logger logger = LoggerFactory
			.getLogger(JpaInfrastructureConfigDev.class);

	@Override
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		DataSource dataSource = builder.setType(EmbeddedDatabaseType.HSQL)
				.build();
		logger.debug("Using embedded data source={}", dataSource);
		return dataSource;
	}


}
