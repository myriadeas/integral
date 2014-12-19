package my.com.myriadeas.integral.identityaccess.domain.model;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
@Configurable
public class GroupMember extends AbstractPersistable<Long> implements Entity {

	private static final long serialVersionUID = 1L;

	private String name;
	private GroupMemberType type;

	public boolean isGroup() {
		return this.type().isGroup();
	}

	public boolean isUser() {
		return this.type().isUser();
	}

	public String name() {
		return this.name;
	}

	public GroupMemberType type() {
		return this.type;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			GroupMember typedObject = (GroupMember) anObject;
			equalObjects = this.name().equals(typedObject.name())
					&& this.type().equals(typedObject.type());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(21941 * 197) + this.name().hashCode()
				+ this.type().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "GroupMember [name=" + name + ", type=" + type + "]";
	}

	protected GroupMember(String name, GroupMemberType type) {
		this();

		this.setName(name);
		this.setType(type);
	}

	protected GroupMember() {
		super();
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void setType(GroupMemberType type) {
		this.type = type;
	}
}