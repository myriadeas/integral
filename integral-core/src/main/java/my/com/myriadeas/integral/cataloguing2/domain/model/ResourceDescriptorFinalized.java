package my.com.myriadeas.integral.cataloguing2.domain.model;

import my.com.myriadeas.integral.cataloguing2.marc.model.RecordType;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class ResourceDescriptorFinalized implements DomainEvent {

	private String resourceDescriptorId;

	private RecordType record;

	private String marc;

	public ResourceDescriptorFinalized(String resourceDescriptorId,
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
		result = prime * result + ((marc == null) ? 0 : marc.hashCode());
		result = prime * result + ((record == null) ? 0 : record.hashCode());
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
		ResourceDescriptorFinalized other = (ResourceDescriptorFinalized) obj;
		if (marc == null) {
			if (other.marc != null)
				return false;
		} else if (!marc.equals(other.marc))
			return false;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		if (resourceDescriptorId == null) {
			if (other.resourceDescriptorId != null)
				return false;
		} else if (!resourceDescriptorId.equals(other.resourceDescriptorId))
			return false;
		return true;
	}

}
