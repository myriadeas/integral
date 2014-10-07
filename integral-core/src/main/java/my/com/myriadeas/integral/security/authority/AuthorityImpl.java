package my.com.myriadeas.integral.security.authority;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthorityImpl implements Authority {

	private boolean exposed;

	private Set<Authority> authorities;

	private Set<GrantedAuthority> grantedAuthorities;

	private String name;

	private String attribute;

	public AuthorityImpl() {

	}

	public AuthorityImpl(String name, String attribute, boolean exposed) {
		this.name = name;
		this.attribute = attribute;
		this.exposed = exposed;
	}

	@Override
	public Set<Authority> getAuthorities() {
		if (authorities == null) {
			authorities = new LinkedHashSet<Authority>();
		}
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public boolean isExposed() {
		// TODO Auto-generated method stub
		return this.exposed;
	}

	@Override
	public void setExposed(boolean exposed) {
		this.exposed = exposed;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public Set<GrantedAuthority> getGrantedAuthorities() {
		if (grantedAuthorities == null) {
			grantedAuthorities = new LinkedHashSet<GrantedAuthority>();
			// add own permission to granted authorities
			this.getGrantedAuthorities().addAll(
					AuthorityUtils.createAuthorityList(attribute));
		}
		return grantedAuthorities;
	}

	@Override
	public String toString() {
		return "AuthorityImpl [exposed=" + exposed + ", authorities="
				+ authorities + ", grantedAuthorities=" + grantedAuthorities
				+ ", name=" + name + ", attribute=" + attribute + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute == null) ? 0 : attribute.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityImpl other = (AuthorityImpl) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		return true;
	}

	@Override
	public Authority addAuthority(Authority authority) {
		this.getAuthorities().add(authority);
		return this;
	}
}
