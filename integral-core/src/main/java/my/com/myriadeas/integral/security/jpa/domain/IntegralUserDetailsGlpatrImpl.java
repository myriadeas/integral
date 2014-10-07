package my.com.myriadeas.integral.security.jpa.domain;

import java.util.Collection;

import my.com.myriadeas.integral.security.IntegralUserDetails;
import my.com.myriadeas.integral.security.model.SecurityUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

public class IntegralUserDetailsGlpatrImpl implements IntegralUserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<? extends GrantedAuthority> authorities;

	private String password;

	private String username;

	private SecurityUser user;

	public IntegralUserDetailsGlpatrImpl(SecurityUser user, String username,
			String password, Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(user);
		Assert.notNull(username);
		Assert.notNull(password);
		Assert.notNull(authorities);
		this.user = user;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return getUser().isEnabled();
	}

	@Override
	public SecurityUser getUser() {
		return this.user;
	}

}
