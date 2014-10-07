package my.com.myriadeas.integral.security;

import my.com.myriadeas.integral.security.model.SecurityUser;

import org.springframework.security.core.userdetails.UserDetails;

public interface IntegralUserDetails extends UserDetails {

	public SecurityUser getUser();
}
