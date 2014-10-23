package my.com.myriadeas.integral.cataloguing2.application.service.command;

public class DeleteResourceDescriptorCommand {

	private String resourceDescriptorId;

	public DeleteResourceDescriptorCommand(String resourceDescriptorId) {
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
