package my.com.myriadeas.integral.index.application;

import my.com.myriadeas.integral.core.application.Command;

public class UnindexCommand implements Command {

	private String resourceDescriptorId;

	public UnindexCommand(String resourceDescriptorId) {
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
