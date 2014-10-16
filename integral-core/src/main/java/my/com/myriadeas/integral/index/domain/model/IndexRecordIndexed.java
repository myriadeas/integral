package my.com.myriadeas.integral.index.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class IndexRecordIndexed implements DomainEvent {

	private String marc;

	private String resourceDescriptorId;

	public IndexRecordIndexed(String marc, String resourceDescriptorId) {
		super();
		this.marc = marc;
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public String getMarc() {
		return marc;
	}

	public void setMarc(String marc) {
		this.marc = marc;
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
		result = prime * result + ((marc == null) ? 0 : marc.hashCode());
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
		IndexRecordIndexed other = (IndexRecordIndexed) obj;
		if (marc == null) {
			if (other.marc != null)
				return false;
		} else if (!marc.equals(other.marc))
			return false;
		if (resourceDescriptorId == null) {
			if (other.resourceDescriptorId != null)
				return false;
		} else if (!resourceDescriptorId.equals(other.resourceDescriptorId))
			return false;
		return true;
	}
	
	

}
