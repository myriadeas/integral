package my.com.myriadeas.integral.security.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;


public interface IntegralSecurityConfig {
	
	public AuthenticationProvider authenticationProvider();

	public AuthenticationManager authenticationManager();
}
