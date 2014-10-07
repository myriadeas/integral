package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserGroup extends AbstractLookupDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Role> roles;

	@ManyToMany
	private Set<Officer> officer;
	
	public UserGroup() {
		
	}
	
	public UserGroup(String code, String description) {
		super(code, description);
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
