package my.com.myriadeas.integral.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SimpleUserDetails implements CasUserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8166850006262607632L;
	
	private String username;
	
	private String proxyTicket;
    
    private String serviceUrl;
	
	public SimpleUserDetails(String username, String proxyTicket, String serviceUrl) {
		this.username = username;
		this.proxyTicket = proxyTicket;
        this.serviceUrl = serviceUrl;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getUsername();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getProxyTicket() {
		// TODO Auto-generated method stub
		return this.proxyTicket;
	}
    
    @Override
    public String getServiceUrl() {
        return this.serviceUrl;
    }

}
