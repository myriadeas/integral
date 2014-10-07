package my.com.myriadeas.integral.security.jpa.impl;

import my.com.myriadeas.integral.data.JpaUtils;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.security.IntegralUserDetails;
import my.com.myriadeas.integral.security.jpa.SecurityService;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private JpaUtils jpaUtils;
	
	@Autowired
	private SpringEnvironmentUtil envUtil;

	@Override
	public Officer getLoginUser() {
		if(envUtil.isDev()) {
			return getSystemUser();
		}
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Assert.notNull(authentication, "Must have authentication");
		Assert.notNull(authentication.getPrincipal(), "Must have principal");
		Assert.isInstanceOf(IntegralUserDetails.class,
				authentication.getPrincipal(), "Must be integral user");
		return (Officer) ((IntegralUserDetails) authentication.getPrincipal())
				.getUser();
	}
	
	
	@Override
	public Officer getSystemUser() {
		return jpaUtils.getSystemUser();
	}

}
