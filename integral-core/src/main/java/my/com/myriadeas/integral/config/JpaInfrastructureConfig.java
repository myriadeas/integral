package my.com.myriadeas.integral.config;

import javax.sql.DataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public interface JpaInfrastructureConfig {
	public DataSource dataSource();

	public LocalContainerEntityManagerFactoryBean entityManagerFactory();

	public PlatformTransactionManager transactionManager();
}
