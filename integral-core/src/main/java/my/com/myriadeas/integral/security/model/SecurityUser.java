package my.com.myriadeas.integral.security.model;

import java.util.Set;

public interface SecurityUser {

	public Set<SecurityRole> getSecurityRoles();
	
	public boolean isEnabled();
}
