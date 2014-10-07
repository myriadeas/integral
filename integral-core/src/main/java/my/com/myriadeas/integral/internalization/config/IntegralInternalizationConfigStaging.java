package my.com.myriadeas.integral.internalization.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.core.config.IntegralConfigStaging;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(STAGING)
@Import(value = { IntegralConfigStaging.class })
@ImportResource(value = { "classpath:META-INF/spring/internalizationServiceRouteContext.xml" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.internalization" }, excludeFilters = { @Filter(Configuration.class) })
public class IntegralInternalizationConfigStaging {

}
