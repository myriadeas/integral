package my.com.myriadeas.integral.cataloguing2.interfaces.facade.request;

public class SendToDeleteResourceDescriptorRequest {

	private String resourceDescriptorId;

	public SendToDeleteResourceDescriptorRequest(String resourceDescriptorId) {
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
