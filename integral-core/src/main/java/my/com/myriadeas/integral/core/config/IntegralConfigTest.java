package my.com.myriadeas.integral.core.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile(TEST)
@PropertySource(name = "properties", value = { "classpath:config-test.properties" })
public class IntegralConfigTest {
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {

		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

}
