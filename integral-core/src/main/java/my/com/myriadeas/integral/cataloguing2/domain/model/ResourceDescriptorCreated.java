package my.com.myriadeas.integral.cataloguing2.domain.model;

import my.com.myriadeas.integral.cataloguing2.marc.model.RecordType;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class ResourceDescriptorCreated implements DomainEvent {

	private String resourceDescriptorId;

	private RecordType record;

	private String marc;

	public ResourceDescriptorCreated(String resourceDescriptorId,
			RecordType record, String marc) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
		this.record = record;
		this.marc = marc;
	}

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public RecordType getRecord() {
		return record;
	}

	public void setRecord(RecordType record) {
		this.record = record;
	}

	public String getMarc() {
		return marc;
	}

	public void setMarc(String marc) {
		this.marc = marc;
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
		ResourceDescriptorCreated other = (ResourceDescriptorCreated) obj;
		if (resourceDescriptorId == null) {
			if (other.resourceDescriptorId != null)
				return false;
		} else if (!resourceDescriptorId.equals(other.resourceDescriptorId))
			return false;
		return true;
	}

}
