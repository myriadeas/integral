package my.com.myriadeas.integral.identityaccess.domain.model.access;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import my.com.myriadeas.integral.identityaccess.domain.model.UserAddedToGroup;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRemovedFromGroup;
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

	@Column(unique = true)
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

	public Map<String, DomainEvent> assignGroup(Group group,
			GroupMemberService groupMemberService) {

		Map<String, DomainEvent> groupEvents = this.group().addGroup(group,
				groupMemberService);

		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();

		if (groupEvents.size() > 0) {
			my.com.myriadeas.integral.core.domain.model.DomainEvent event = new GroupAssignedToRole(
					group.name(), group.getId(), this.name(), this.getId());
			events.put("groupAssignedToRole", event);

		}

		return events;

	}

	public Map<String, DomainEvent> assignUser(User user) {
		Map<String, DomainEvent> groupEvents = this.group().addUser(user);

		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		
		System.out.println("groupEvents.size():" + groupEvents.size());
		if (groupEvents.size() > 0) {
			UserAddedToGroup userAddedToGroupEvent = (UserAddedToGroup) groupEvents
					.get("userAddedToGroup");
			my.com.myriadeas.integral.core.domain.model.DomainEvent event = new UserAssignedToRole(
					userAddedToGroupEvent.getUserName(),
					userAddedToGroupEvent.getUserId(), this.name(),
					this.getId());
			events.put("userAssignedToRole", event);

		}

		return events;
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

	public Map<String, DomainEvent> unassignGroup(Group aGroup) {
		this.group().removeGroup(aGroup);

		Map<String, DomainEvent> groupEvents = this.group().removeGroup(aGroup);

		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();

		if (groupEvents.size() > 0) {
			my.com.myriadeas.integral.core.domain.model.DomainEvent event = new GroupUnassignedFromRole(
					group.name(), group.getId(), this.name(), this.getId());
			events.put("groupUnassignedFromRole", event);
		}

		return events;

	}

	public Map<String, DomainEvent> unassignUser(User user) {

		Map<String, DomainEvent> groupEvents = this.group().removeUser(user);

		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();

		if (groupEvents.size() > 0) {
			UserRemovedFromGroup userAddedToGroupEvent = (UserRemovedFromGroup) groupEvents
					.get("userRemovedFromGroup");
			my.com.myriadeas.integral.core.domain.model.DomainEvent event = new UserUnassignedFromRole(
					userAddedToGroupEvent.getUserName(),
					userAddedToGroupEvent.getUserId(), this.name(),
					this.getId());
			events.put("userAssignedToRole", event);

		}

		return events;
	}

	@Override
	public String toString() {
		return "Role [description=" + description + ", group=" + group
				+ ", name=" + name + ", supportsNesting=" + supportsNesting
				+ ", permissions=" + permissions + "]";
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

}
