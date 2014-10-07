package my.com.myriadeas.integral.security.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.security.config.IntegralSecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@Profile(DEV)
public class SecurityConfigDev implements IntegralSecurityConfig {

	@Override
	public AuthenticationProvider authenticationProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationManager authenticationManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
