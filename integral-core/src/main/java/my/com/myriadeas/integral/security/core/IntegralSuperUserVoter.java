package my.com.myriadeas.integral.security.core;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class IntegralSuperUserVoter implements AccessDecisionVoter<Object> {

	/**
	 * Ignored {@link} in the checking. It is the super user voter.
	 */

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	/**
	 * Ignored the type of class. Support any type
	 */
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		return isSuperUser(authentication) ? ACCESS_GRANTED : ACCESS_ABSTAIN;
	}

	private boolean isSuperUser(Authentication authentication) {
		return authentication.getName().equals("SYSADMIN")
				|| authentication.getAuthorities().contains(
						new SimpleGrantedAuthority("ROLE_SUPERUSER"));
	}

}
