package my.com.myriadeas.integral.security.authority;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public interface Authority {
	
	public Set<Authority> getAuthorities();
	
	public Set<GrantedAuthority> getGrantedAuthorities();

	public void setName(String name);

	public String getName();

	public void setAttribute(String attribute);

	public String getAttribute();

	public boolean isExposed();

	public void setExposed(boolean exposed);
	
	public Authority addAuthority(Authority authority);
}
