package my.com.myriadeas.integral.cataloguing2.application.service.command;

import org.marc4j.marc.Record;

public class UpdateResourceDescriptorCommand {

	private String resourceDescriptorId;

	private Record record;

	public UpdateResourceDescriptorCommand(String resourceDescriptorId,
			Record record) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
		this.record = record;
	}

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	public void setResourceDescriptorId(String resourceDescriptorId) {
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
