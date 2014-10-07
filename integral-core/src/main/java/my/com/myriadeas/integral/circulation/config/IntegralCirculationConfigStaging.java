package my.com.myriadeas.integral.circulation.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigStaging;
import my.com.myriadeas.integral.core.config.IntegralConfigStaging;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(STAGING)
@Import(value = { JpaInfrastructureConfigStaging.class,
		IntegralCataloguingConfigStaging.class,
		IntegralMysticRouteConfigImpl.class, IntegralConfigStaging.class })
@ImportResource(value = { "classpath:META-INF/spring/eligibilityContext.xml",
		"classpath:META-INF/spring/circulationServiceRouteContext.xml",
		"classpath:META-INF/spring/vufindServiceRouteContext.xml",
		"classpath:META-INF/spring/reportServiceRouteContext.xml",
		"classpath:META-INF/spring/rptServiceRouteContext.xml",
		"classpath:META-INF/spring/jsonViewContext.xml" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.internalization",
		"my.com.myriadeas.integral.circulation",
		"my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.eligibility",
		"my.com.myriadeas.integral.holiday", "my.com.myriadeas.integral.fines",
		"my.com.myriadeas.integral.security" }, excludeFilters = { @Filter(Configuration.class) })
public class IntegralCirculationConfigStaging implements
		IntegralCirculationConfig {

}
