package my.com.myriadeas.integral.accession.presentation;

import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;

public class AccessionListItemDto {

	private String resourceDescriptorId;
	
	private String AccessionId;

	private ItemStatus status;

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}
	

	public String getAccessionId() {
		return AccessionId;
	}

	public void setAccessionId(String accessionId) {
		AccessionId = accessionId;
	}

	@Override
	public String toString() {
		return "AccessionListItemDto [resourceDescriptorId="
				+ resourceDescriptorId + ", AccessionId=" + AccessionId
				+ ", status=" + status + "]";
	}


}
