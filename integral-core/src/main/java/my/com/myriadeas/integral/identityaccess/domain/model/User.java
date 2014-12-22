package my.com.myriadeas.integral.identityaccess.domain.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;
import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.security.model.SecurityRole;
import my.com.myriadeas.integral.security.model.SecurityUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import com.fasterxml.jackson.annotation.JsonIgnore;

@javax.persistence.Entity
@Configurable
public class User extends AbstractPersistable<Long> implements Entity,
		SecurityUser {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(User.class);
	@Column(unique = true)
	private String username;
	private String password;
	@Transient
	private PasswordEncoder passwordEncoder;
	@Transient
	private RegisterUserValidator registerUserValidator;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Role> roles = new HashSet<Role>();

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	public Map<String, DomainEvent> getRegisteredUserEvent() {
		my.com.myriadeas.integral.core.domain.model.DomainEvent registeredUser = new UserRegistered(
				getUsername(), (Long) getId());
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		events.put("userRegistered", registeredUser);
		return events;
	}

	protected void encryptPassword() {
		password = passwordEncoder.encode(password);
	}

	@PostConstruct
	public void postConstruct() {
		Errors result = new BindException(this, getClass().getName());
		registerUserValidator.validate(this, result);
		if (result.hasErrors()) {
			throw new RegisterUserException("Fail to register user", result);
		}
		encryptPassword();
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setRegisterUserValidator(
			RegisterUserValidator registerUserValidator) {
		this.registerUserValidator = registerUserValidator;
	}

	protected GroupMember toGroupMember() {
		GroupMember groupMember = new GroupMember(this.username,
				GroupMemberType.User);

		return groupMember;
	}
	
	public Group provisionGroup(String aName, String aDescription) {

        Group group = new Group(aName, aDescription);

        return group;
    }

	public Role provisionRole(String name, String description) {
		return this.provisionRole(name, description, false);
	}

	public Role provisionRole(String name, String description,
			boolean supportsNesting) {

		Role role = new Role(name, description, supportsNesting);

		return role;
	}

	public String toString() {
		return (new StringBuilder("User [username=")).append(username)
				.append(", password=").append(password).append("]").toString();
	}

	@Override
	@JsonIgnore
	public Set<SecurityRole> getSecurityRoles() {
		Set<SecurityRole> securityRoles = new HashSet<SecurityRole>();
		securityRoles.addAll(getRoles());
		return securityRoles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Map<String, DomainEvent> assignRoles(Set<Role> roles) {

		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new RoleAssigned(
				getUsername(), (Long) getId(), roles);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		events.put("roleAssigned", event);

		this.roles = roles;

		return events;

	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}