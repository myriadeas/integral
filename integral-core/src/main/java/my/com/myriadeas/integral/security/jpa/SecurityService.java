package my.com.myriadeas.integral.security.jpa;

import my.com.myriadeas.integral.data.jpa.domain.Officer;

public interface SecurityService {

	public Officer getLoginUser();

	public Officer getSystemUser();
}
