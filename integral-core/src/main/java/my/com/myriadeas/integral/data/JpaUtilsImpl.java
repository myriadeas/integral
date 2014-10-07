package my.com.myriadeas.integral.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.impl.OfficerRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "jpaUtilsImpl")
public class JpaUtilsImpl implements JpaUtils, InitializingBean,
		ApplicationListener<ContextRefreshedEvent> {
	
	private static Logger logger = LoggerFactory
			.getLogger(JpaUtilsImpl.class);

	@Autowired
	private OfficerRepositoryImpl userRepo;

	private JdbcTemplate jdbcTemplate;

	private Boolean databaseEmpty = null;

	private Officer SYSTEM = null;

	private JpaUtilsImpl() {

	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);		
		
	}

	public Boolean isDatabaseEmpty() {
		return this.databaseEmpty;
	}

	@Override
	public Officer getSystemUser() {
		Assert.notNull(SYSTEM);
		return SYSTEM;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (databaseEmpty == null) {
			databaseEmpty = jdbcTemplate.queryForObject(
					"select count(*) from officer where username='SYSTEM'",
					Integer.class) <= 0;
		}
		if (isDatabaseEmpty()) {
			SYSTEM = new Officer("SYSTEM", "SYSTEM", "SYSTEM USER", null);
			userRepo.save(SYSTEM);
		} else {
			SYSTEM = userRepo.findByUsername("SYSTEM");
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		SYSTEM = userRepo.findByUsername("SYSTEM");
	}
}
