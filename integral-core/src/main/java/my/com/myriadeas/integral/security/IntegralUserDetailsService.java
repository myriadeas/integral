package my.com.myriadeas.integral.security;

import java.util.Set;

import my.com.myriadeas.integral.security.model.SecurityUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IntegralUserDetailsService extends UserDetailsService {

	public IntegralUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	public Set<GrantedAuthority> getGrantedAuthorities(SecurityUser user);
}
