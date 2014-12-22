package my.com.myriadeas.integral.identityaccess.domain.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
@Configurable
@Table(name = "group_")
public class Group extends AbstractPersistable<Long> implements Entity {

	private static final long serialVersionUID = 1L;

	public static final String ROLE_GROUP_PREFIX = "ROLE-INTERNAL-GROUP: ";

	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<GroupMember> groupMembers;
	private String name;

	public Group(String aName, String aDescription) {
		this();

		this.setDescription(aDescription);
		this.setName(aName);
	}

	public Map<String, DomainEvent> addGroup(Group aGroup,
			GroupMemberService aGroupMemberService) {

		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new GroupAdded(
				name(), (Long) getId());
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		if (this.groupMembers().add(aGroup.toGroupMember())
				&& !this.isInternalGroup()) {
			events.put("groupAdded", event);
		}

		return events;
	}

	public Map<String, DomainEvent> addUser(User aUser) {

		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new UserAddedToGroup(
				aUser, this);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();

		if (this.groupMembers().add(aUser.toGroupMember())
				&& !this.isInternalGroup()) {
			events.put("userAddedToGroup", event);
		}

		return events;
	}

	public String description() {
		return this.description;
	}

	public Set<GroupMember> groupMembers() {
		return this.groupMembers;
	}

	public boolean isMember(User aUser, GroupMemberService aGroupMemberService) {

		boolean isMember = this.groupMembers().contains(aUser.toGroupMember());

		if (isMember) {
			isMember = aGroupMemberService.confirmUser(this, aUser);
		} else {
			isMember = aGroupMemberService.isUserInNestedGroup(this, aUser);
		}

		return isMember;
	}

	public String name() {
		return this.name;
	}

	public Map<String, DomainEvent> removeGroup(Group aGroup) {
		// not a nested remove, only direct member
		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new GroupRemoved(
				this);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		if (this.groupMembers().remove(aGroup.toGroupMember())
				&& !this.isInternalGroup()) {
			events.put("groupRemoved", event);
		}

		return events;
	}

	public Map<String, DomainEvent> removeUser(User aUser) {

		my.com.myriadeas.integral.core.domain.model.DomainEvent event = new UserRemovedFromGroup(
				aUser, this);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		// not a nested remove, only direct member
		if (this.groupMembers().remove(aUser.toGroupMember())
				&& !this.isInternalGroup()) {
			events.put("userRemovedFromGroup", event);
		}

		return events;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			Group typedObject = (Group) anObject;
			equalObjects = this.name().equals(typedObject.name());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(2061 * 193) + this.name().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "Group [description=" + description + ", name=" + name + "]";
	}

	protected Group() {
		super();

		this.setGroupMembers(new HashSet<GroupMember>(0));
	}

	protected void setDescription(String aDescription) {
		this.description = aDescription;
	}

	protected void setGroupMembers(Set<GroupMember> aGroupMembers) {
		this.groupMembers = aGroupMembers;
	}

	protected boolean isInternalGroup() {
		return this.isInternalGroup(this.name());
	}

	protected boolean isInternalGroup(String aName) {
		return aName.startsWith(ROLE_GROUP_PREFIX);
	}

	protected void setName(String aName) {
		if (this.isInternalGroup(aName)) {
			String uuid = aName.substring(ROLE_GROUP_PREFIX.length());

			try {
				UUID.fromString(uuid);
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"The group name has an invalid format.");
			}
		}

		this.name = aName;
	}

	protected GroupMember toGroupMember() {
		GroupMember groupMember = new GroupMember(this.name(),
				GroupMemberType.Group);

		return groupMember;
	}
}