package my.com.myriadeas.integral.data.jpa.config;

import javax.sql.DataSource;

import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@ComponentScan(basePackages = "my.com.myriadeas.integral.data", excludeFilters = { @Filter(Configuration.class) })
@EnableJpaRepositories(basePackages = "my.com.myriadeas.integral.data.jpa.repositories.impl")
@EnableAspectJAutoProxy
@EnableSpringConfigured
public abstract class AbstractJpaInfrastructureConfig implements
		JpaInfrastructureConfig {

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	

	@Bean
	public SpringEnvironmentUtil envUtil() {
		SpringEnvironmentUtil envUtil = new SpringEnvironmentUtil();
		return envUtil;
	}

	@Override
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.HSQL);
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(dataSource());
		return factory;
	}
}
