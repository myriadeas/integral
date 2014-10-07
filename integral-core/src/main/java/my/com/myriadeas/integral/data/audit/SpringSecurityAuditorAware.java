package my.com.myriadeas.integral.data.audit;

import my.com.myriadeas.integral.data.JpaUtils;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.security.IntegralUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "springSecurityAuditorAware")
public class SpringSecurityAuditorAware implements AuditorAware<Officer> {
	@Autowired
	private JpaUtils jpaUtilsImpl;

	public Officer getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return jpaUtilsImpl.getSystemUser();// We always assume the
												// possibility to update repo
			// without login is always SYSTEM USER.
		} else {
			return (Officer) ((IntegralUserDetails) authentication
					.getPrincipal()).getUser();
		}
	}
}
