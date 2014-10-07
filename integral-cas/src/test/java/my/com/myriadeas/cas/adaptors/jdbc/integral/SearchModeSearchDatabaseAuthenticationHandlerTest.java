package my.com.myriadeas.cas.adaptors.jdbc.integral;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SearchModeSearchDatabaseAuthenticationHandlerTest {

	@Test
	public void testIsPasswordMatched() {

		SearchModeSearchDatabaseAuthenticationHandler handler = new SearchModeSearchDatabaseAuthenticationHandler();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String credentialsPassword = "password";
		String userPassword = passwordEncoder.encode("password");
		
		boolean actualResult = handler.isPasswordMatched(credentialsPassword, userPassword);
		assertTrue(actualResult);
		
		userPassword = passwordEncoder.encode("sysadmin");
		actualResult = handler.isPasswordMatched(credentialsPassword, userPassword);
		assertFalse(actualResult);

	}

}
