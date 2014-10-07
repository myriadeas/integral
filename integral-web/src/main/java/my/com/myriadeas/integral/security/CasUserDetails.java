package my.com.myriadeas.integral.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CasUserDetails extends UserDetails {

	public String getProxyTicket();
    
    public String getServiceUrl();
}
