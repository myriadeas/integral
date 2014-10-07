package my.com.myriadeas.integral.eligibility.config;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.eligibility.LookupTableFactoryBean;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(SpringEnvironmentUtil.DEV)
@Configuration
public class IntegralEligibilityConfigDev implements IntegralEligibilityConfig {

	@Bean
	public LookupTableFactoryBean lookupTableFactoryBean() {
		LookupTableFactoryBean factoryBean = new LookupTableFactoryBean();
		List<String> commands = new ArrayList<String>();
		commands.add("-pc administrator -pc lecturer -ic OS -el administratorPolicy");
		factoryBean.setCommands(commands);
		return factoryBean;
	}

	@Bean
	public LookupTableFactoryBean lookupTableFactoryBean2() {
		LookupTableFactoryBean factoryBean = new LookupTableFactoryBean();
		List<String> commands = new ArrayList<String>();
		commands.add("-pc administrator -pc lecturer -ic OS -el administratorPolicy2");
		factoryBean.setCommands(commands);
		return factoryBean;

	}
}
