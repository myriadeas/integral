package my.com.myriadeas.integral.mystic.web.route.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigStaging;
import my.com.myriadeas.integral.cataloguing2.config.CataloguingConfigStaging;
import my.com.myriadeas.integral.circulation2.config.CirculationConfigStaging;
import my.com.myriadeas.integral.cqrs.query.accession.config.AccessionConfigStaging;
import my.com.myriadeas.integral.identityaccess.config.IdentityAccessConfigStaging;
import my.com.myriadeas.integral.index.config.IndexConfigStaging;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(STAGING)
@Import(value = { IntegralInternalizationConfigStaging.class,
		CirculationConfigStaging.class, CataloguingConfigStaging.class,
		IndexConfigStaging.class, AssetManagementConfigStaging.class,
		IdentityAccessConfigStaging.class, AccessionConfigStaging.class })
// @ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = {
// @Filter(Configuration.class) })
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class IntegralMysticRouteConfigStaging implements
		IntegralMysticRouteConfig {

}
