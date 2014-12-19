package my.com.myriadeas.integral.identityaccess.domain.model.access;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;
import my.com.myriadeas.integral.identityaccess.domain.model.Group;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.security.model.SecurityPermission;
import my.com.myriadeas.integral.security.model.SecurityRole;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;

@javax.persistence.Entity
@Configurable
@Table(name = "role_")
public class Role extends AbstractPersistable<Long> implements Entity,
		SecurityRole {

	private static final long serialVersionUID = 1L;

	private String description;

	@ManyToOne(optional = true, cascade = { CascadeType.ALL })
	private Group group;

	private String name;
	private boolean supportsNesting = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Permission> permissions = new HashSet<Permission>();

	public Role(String aName, String aDescription) {
		this(aName, aDescription, false);
	}

	public Role(String aName, String aDescription, boolean aSupportsNesting) {

		this();

		this.setDescription(aDescription);
		this.setName(aName);
		this.setSupportsNesting(aSupportsNesting);

		this.createInternalGroup();
	}

	public void assignGroup(Group aGroup, GroupMemberService aGroupMemberService) {

		this.group().addGroup(aGroup, aGroupMemberService);

		// publish event
	}

	public void assignUser(User aUser) {
		this.group().addUser(aUser);

		// NOTE: Consider what a consuming Bounded Context would
		// need to do if this event was not enriched with the
		// last three user person properties. (Hint: A lot.)
		// publish event
	}

	public String description() {
		return this.description;
	}

	public boolean isInRole(User aUser, GroupMemberService aGroupMemberService) {
		return this.group().isMember(aUser, aGroupMemberService);
	}

	public String name() {
		return this.name;
	}

	public boolean supportsNesting() {
		return this.supportsNesting;
	}

	public void unassignGroup(Group aGroup) {
		this.group().removeGroup(aGroup);

		// publish event
	}

	public void unassignUser(User aUser) {

		this.group().removeUser(aUser);

		// publish event
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			Role typedObject = (Role) anObject;
			equalObjects = this.name().equals(typedObject.name());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(18723 * 233) + this.name().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description
				+ ", supportsNesting=" + supportsNesting + ", group=" + group
				+ "]";
	}

	protected Role() {
		super();
	}

	protected void createInternalGroup() {
		String groupName = Group.ROLE_GROUP_PREFIX
				+ UUID.randomUUID().toString().toUpperCase();

		this.setGroup(new Group(groupName, "Role backing group for: "
				+ this.name()));
	}

	protected void setDescription(String aDescription) {

		this.description = aDescription;
	}

	protected Group group() {
		return this.group;
	}

	protected void setGroup(Group aGroup) {
		this.group = aGroup;
	}

	protected void setName(String aName) {

		this.name = aName;
	}

	public Map<String, DomainEvent> assignPermission(Set<Permission> permissions) {

		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new PermissionAssigned(
				name(), (Long) getId(), permissions);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		events.put("permissionAssigned", event);

		this.permissions = permissions;

		return events;

	}

	protected void setSupportsNesting(boolean aSupportsNesting) {
		this.supportsNesting = aSupportsNesting;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	@Override
	public Set<SecurityPermission> getSecurityPermissions() {

		Set<SecurityPermission> securityPermissions = new HashSet<SecurityPermission>();
		securityPermissions.addAll(getPermissions());
		return securityPermissions;
	}

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrantedAuthority getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
