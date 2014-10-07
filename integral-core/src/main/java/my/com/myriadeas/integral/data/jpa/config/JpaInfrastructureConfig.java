package my.com.myriadeas.integral.data.jpa.config;

import javax.sql.DataSource;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@EnableJpaRepositories(basePackages = "my.com.myriadeas.integral.data.jpa.repositories.impl")
public interface JpaInfrastructureConfig {
	public DataSource dataSource();

	public LocalContainerEntityManagerFactoryBean entityManagerFactory();

	public PlatformTransactionManager transactionManager();
}
