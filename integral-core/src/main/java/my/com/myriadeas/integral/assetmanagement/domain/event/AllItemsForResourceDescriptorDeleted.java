package my.com.myriadeas.integral.assetmanagement.domain.event;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class AllItemsForResourceDescriptorDeleted implements DomainEvent {

	private String resourceDescriptorIdentifier;

	public String getResourceDescriptorIdentifier() {
		return resourceDescriptorIdentifier;
	}

	public void setResourceDescriptorIdentifier(
			String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public AllItemsForResourceDescriptorDeleted(
			String resourceDescriptorIdentifier) {
		super();
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((resourceDescriptorIdentifier == null) ? 0
						: resourceDescriptorIdentifier.hashCode());
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
		AllItemsForResourceDescriptorDeleted other = (AllItemsForResourceDescriptorDeleted) obj;
		if (resourceDescriptorIdentifier == null) {
			if (other.resourceDescriptorIdentifier != null)
				return false;
		} else if (!resourceDescriptorIdentifier
				.equals(other.resourceDescriptorIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AllItemsForResourceDescriptorDeleted [resourceDescriptorIdentifier="
				+ resourceDescriptorIdentifier + "]";
	}

}
