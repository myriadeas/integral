package my.com.myriadeas.integral.security.authority;

import my.com.myriadeas.integral.security.model.SecurityPermission;

public interface AuthorityService extends AuthoritySetup {

	public Authority getAuthority(SecurityPermission permission);
}
