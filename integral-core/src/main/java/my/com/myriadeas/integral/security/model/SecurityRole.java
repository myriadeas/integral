package my.com.myriadeas.integral.security.model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public interface SecurityRole {

	public Set<SecurityPermission> getSecurityPermissions();

	public Set<GrantedAuthority> getAuthorities();
	
	public GrantedAuthority getAuthority();
}
