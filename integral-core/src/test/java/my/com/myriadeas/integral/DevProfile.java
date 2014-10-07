package my.com.myriadeas.integral;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DEV)
public class DevProfile {

	@Bean
	public ProfileBean profileBean() {
		ProfileBean profileBean = new ProfileBean();
		profileBean.setProfile("development");
		return profileBean;
	}
}
