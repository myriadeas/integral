package my.com.myriadeas.integral.identityaccess.domain.model.access;

import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.Table;

import my.com.myriadeas.integral.core.domain.model.Entity;
import my.com.myriadeas.integral.security.model.SecurityPermission;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
@Configurable
@Table(name = "permission_")
public class Permission extends AbstractPersistable<Long> implements Entity,
		SecurityPermission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442992871442829483L;

	@NotBlank
	private String name;

	private String description;

	@ManyToMany
	private Set<Role> roles;

	public Permission() {

	}

	public Permission(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String getAttribute() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
