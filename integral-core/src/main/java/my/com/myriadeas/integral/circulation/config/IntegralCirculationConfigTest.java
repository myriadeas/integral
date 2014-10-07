package my.com.myriadeas.integral.circulation.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigTest;
import my.com.myriadeas.integral.core.config.IntegralConfigTest;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigTest;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(TEST)
@Import(value = { JpaInfrastructureConfigTest.class,
		IntegralCataloguingConfigTest.class, IntegralMysticRouteConfigImpl.class, IntegralConfigTest.class })

@ImportResource(value = { "classpath:META-INF/spring/eligibilityContext.xml",
		"classpath:META-INF/spring/circulationServiceRouteContext.xml",
		"classpath:META-INF/spring/vufindServiceRouteContext.xml",
		"classpath:META-INF/spring/reportServiceRouteContext.xml",
		"classpath:META-INF/spring/jsonViewContext.xml" })
@ComponentScan(basePackages = { "my.com.myriadeas.integral.internalization",
		"my.com.myriadeas.integral.circulation",
		"my.com.myriadeas.integral.core",
		"my.com.myriadeas.integral.eligibility",
		"my.com.myriadeas.integral.holiday", "my.com.myriadeas.integral.fines",
		"my.com.myriadeas.integral.security" }, excludeFilters = { @Filter(Configuration.class) })
public class IntegralCirculationConfigTest implements IntegralCirculationConfig {

}
