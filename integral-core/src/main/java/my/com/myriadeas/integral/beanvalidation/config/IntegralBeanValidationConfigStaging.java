package my.com.myriadeas.integral.beanvalidation.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigStaging;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(STAGING)
@ImportResource(value = { "classpath:META-INF/spring/beanValidationServiceRouteContext.xml" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.beanvalidation" }, excludeFilters = { @Filter(Configuration.class) })
@Import(value = { JpaInfrastructureConfigStaging.class })
public class IntegralBeanValidationConfigStaging {
}
