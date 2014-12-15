package my.com.myriadeas.integral.cataloguing2.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class ResourceDescriptorRevised implements DomainEvent {
	
	private String resourceDescriptorId;

	public ResourceDescriptorRevised(String resourceDescriptorId) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
	}
	
	

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}



	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((resourceDescriptorId == null) ? 0 : resourceDescriptorId
						.hashCode());
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
		ResourceDescriptorRevised other = (ResourceDescriptorRevised) obj;
		if (resourceDescriptorId == null) {
			if (other.resourceDescriptorId != null)
				return false;
		} else if (!resourceDescriptorId.equals(other.resourceDescriptorId))
			return false;
		return true;
	}
	
	

}
