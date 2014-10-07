package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import my.com.myriadeas.integral.security.model.SecurityPermission;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Permission extends AbstractDomain implements SecurityPermission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442992871442829483L;

	@Column(unique = true)
	@NotBlank
	private String permission;

	private String description;

	@ManyToMany
	private Set<Role> roles;

	public Permission() {

	}

	public Permission(String permission, String description) {
		this.permission = permission;
		this.description = description;
	}

	@Override
	public String getAttribute() {
		return this.permission;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
