package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.mysticroute.config.IntegralMysticRouteConfigImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Import(value = { JpaInfrastructureConfigStaging.class,
		IntegralMysticRouteConfigImpl.class })
@PropertySource(name = "properties", value = { "classpath:config-staging.properties" })
@Configuration
@Profile(STAGING)
public class IndexConfigStaging extends IndexCommonConfig{
}
