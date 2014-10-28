package my.com.myriadeas.integral.cataloguing2.interfaces.facade.request;

public class DeleteResourceDescriptorRequest {

	private String resourceDescriptorId;

	public DeleteResourceDescriptorRequest(String resourceDescriptorId) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}

}
