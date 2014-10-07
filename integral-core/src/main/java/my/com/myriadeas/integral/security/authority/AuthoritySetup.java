package my.com.myriadeas.integral.security.authority;

import org.springframework.beans.factory.InitializingBean;

public interface AuthoritySetup extends InitializingBean {

	public void populateAuthorities();
}
