package my.com.myriadeas.integral.cataloguing2.presentation;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorStatus;

public class ResourceDescriptorListItemDto {
	
	private String resourceDescriptorId;

	private String marc;

	private ResourceDescriptorStatus status;

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public String getMarc() {
		return marc;
	}

	public void setMarc(String marc) {
		this.marc = marc;
	}

	public ResourceDescriptorStatus getStatus() {
		return status;
	}

	public void setStatus(ResourceDescriptorStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResourceDescriptorListItemDto [resourceDescriptorId="
				+ resourceDescriptorId + ", marc=" + marc + ", status="
				+ status + "]";
	}
	
}
