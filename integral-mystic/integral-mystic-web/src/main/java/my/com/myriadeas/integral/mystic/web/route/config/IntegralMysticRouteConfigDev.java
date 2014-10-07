package my.com.myriadeas.integral.mystic.web.route.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.beanvalidation.config.IntegralBeanValidationConfigDev;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigDev;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DEV)
@Import(value = { IntegralCirculationConfigDev.class,
		IntegralCataloguingConfigDev.class,
		IntegralBeanValidationConfigDev.class,
		IntegralInternalizationConfigDev.class})
@ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = { @Filter(Configuration.class) })
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class IntegralMysticRouteConfigDev implements IntegralMysticRouteConfig {

}
