package my.com.myriadeas.integral.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUtils {
	// Set a user account that will initially own all the created data
	private static Authentication authRequest = new UsernamePasswordAuthenticationToken(
			"SYSTEM", "SYSTEM",
			AuthorityUtils.createAuthorityList("ROLE_SUPERUSER"));

	private static boolean isAuthenticate = false;
	/*-
	public static void authenticateAsSystemUser() {
		if (isAuthenticate) {
			throw new RuntimeException("Must clear the last authentication");
		}
		SecurityContextHolder.getContext().setAuthentication(authRequest);
		isAuthenticate = true;
	}

	public static void logoutSystemUser() {
		SecurityContextHolder.clearContext();
		isAuthenticate = false;
	}*/
}
