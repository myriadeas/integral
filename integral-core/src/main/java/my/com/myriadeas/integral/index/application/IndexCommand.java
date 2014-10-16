package my.com.myriadeas.integral.index.application;

import my.com.myriadeas.integral.core.application.Command;

public class IndexCommand implements Command {

	private String marc;

	private String resourceDescriptorId;

	public IndexCommand(String marc, String resourceDescriptorId) {
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

}
