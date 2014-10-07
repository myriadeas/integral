package my.com.myriadeas.integral.mystic.web.route.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.beanvalidation.config.IntegralBeanValidationConfigStaging;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigStaging;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigStaging;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(STAGING)
@Import(value = { IntegralCirculationConfigStaging.class,
		IntegralCataloguingConfigStaging.class,
		IntegralBeanValidationConfigStaging.class,
		IntegralInternalizationConfigStaging.class })
@ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = { @Filter(Configuration.class) })
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class IntegralMysticRouteConfigStaging implements
		IntegralMysticRouteConfig {

}
