package my.com.myriadeas.integral.mystic.web.route.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.assetmanager.config.AssetManagerConfig;
import my.com.myriadeas.integral.beanvalidation.config.IntegralBeanValidationConfigTest;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigTest;
import my.com.myriadeas.integral.cataloguing2.config.CataloguingConfig;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigTest;
import my.com.myriadeas.integral.circulation2.config.CirculationConfig;
import my.com.myriadeas.integral.index.config.IndexDevConfig;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigDev;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigTest;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(TEST)
@Import(value = { IntegralInternalizationConfigDev.class,
		CirculationConfig.class, CataloguingConfig.class, IndexDevConfig.class,
		AssetManagerConfig.class })
//@ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = { @Filter(Configuration.class) })
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class IntegralMysticRouteConfigTest implements IntegralMysticRouteConfig {

}
