package my.com.myriadeas.integral.cataloguing2.application.service.command;

public class SendToDeleteResourceDescriptorCommand {

	private String resourceDescriptorId;

	public SendToDeleteResourceDescriptorCommand(String resourceDescriptorId) {
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
