package my.com.myriadeas.integral;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(TEST)
public class TestProfile {

	@Bean
	public ProfileBean profileBean() {
		ProfileBean profileBean = new ProfileBean();
		profileBean.setProfile("test");
		return profileBean;
	}
}
