package my.com.myriadeas.integral.identityaccess.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class GroupRemoved implements DomainEvent {
	
	private Group group;

	public GroupRemoved(Group group) {
		super();
		this.group = group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
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
		GroupRemoved other = (GroupRemoved) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupRemoved [group=" + group + "]";
	}

}
