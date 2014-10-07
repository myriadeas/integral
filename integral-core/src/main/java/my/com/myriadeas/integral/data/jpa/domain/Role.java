package my.com.myriadeas.integral.data.jpa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import my.com.myriadeas.integral.security.model.SecurityPermission;
import my.com.myriadeas.integral.security.model.SecurityRole;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Role extends AbstractDomain implements SecurityRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	@NotBlank
	private String role;

	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Permission> permissions = new HashSet<Permission>();

	@ManyToMany
	private Set<Officer> officer;
	
	@ManyToMany
	private Set<UserGroup> userGroup;

	@Transient
	private Set<my.com.myriadeas.integral.security.model.SecurityPermission> securityPermissions;

	@Transient
	private Set<GrantedAuthority> authorities;

	public Role() {

	}

	public Role(String role, String description) {
		this.role = role;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Set<SecurityPermission> getSecurityPermissions() {
		if (securityPermissions == null) {
			securityPermissions = new HashSet<my.com.myriadeas.integral.security.model.SecurityPermission>();
			securityPermissions.addAll(permissions);
		}
		return securityPermissions;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> gl81permissions) {
		this.permissions = gl81permissions;
	}

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			authorities = new HashSet<GrantedAuthority>();
			authorities.add(getAuthority());
		}
		return authorities;
	}

	@Override
	public String toString() {
		return "Glrole [gl81role=" + role + ", description=" + description
				+ "]";
	}

	@Override
	public GrantedAuthority getAuthority() {
		return new SimpleGrantedAuthority(role);
	}

}
