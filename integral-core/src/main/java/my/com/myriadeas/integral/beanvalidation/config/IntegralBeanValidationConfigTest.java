package my.com.myriadeas.integral.beanvalidation.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(TEST)
@ImportResource(value = { "classpath:META-INF/spring/beanValidationServiceRouteContext.xml"})
@ComponentScan(basePackages = { "my.com.myriadeas.integral.beanvalidation"}, excludeFilters = { @Filter(Configuration.class) })
@Import(value = { JpaInfrastructureConfigTest.class })
public class IntegralBeanValidationConfigTest {
}
