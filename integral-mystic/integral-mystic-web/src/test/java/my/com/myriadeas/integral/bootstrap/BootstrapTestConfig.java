package my.com.myriadeas.integral.bootstrap;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.index.config.IntegralVufindConfigDev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DEV)
@Import(value = { IntegralVufindConfigDev.class })
public class BootstrapTestConfig {

	@Bean
	public CirculationBootstrap circulationBootstrap() {
		return new CirculationBootstrap();
	}
}
