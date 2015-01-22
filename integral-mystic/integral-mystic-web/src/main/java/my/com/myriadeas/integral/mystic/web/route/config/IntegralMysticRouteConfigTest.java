package my.com.myriadeas.integral.mystic.web.route.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigTest;
import my.com.myriadeas.integral.cataloguing2.config.CataloguingConfigTest;
import my.com.myriadeas.integral.circulation2.config.CirculationConfigTest;
import my.com.myriadeas.integral.cqrs.query.accession.config.AccessionConfigTest;
import my.com.myriadeas.integral.cqrs.query.bib.config.BibConfigTest;
import my.com.myriadeas.integral.identityaccess.config.IdentityAccessConfigTest;
import my.com.myriadeas.integral.index.config.IndexConfigTest;
import my.com.myriadeas.integral.internalization.config.IntegralInternalizationConfigTest;
import my.com.myriadeas.integral.item.query.config.ItemReadConfigTest;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(TEST)
@Import(value = { IntegralInternalizationConfigTest.class,
		CirculationConfigTest.class, CataloguingConfigTest.class,
		IndexConfigTest.class, AssetManagementConfigTest.class,
		IdentityAccessConfigTest.class, AccessionConfigTest.class,
		BibConfigTest.class, ItemReadConfigTest.class })
// @ComponentScan(basePackages = "my.com.myriadeas.integral", excludeFilters = {
// @Filter(Configuration.class) })
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class IntegralMysticRouteConfigTest implements IntegralMysticRouteConfig {

}
