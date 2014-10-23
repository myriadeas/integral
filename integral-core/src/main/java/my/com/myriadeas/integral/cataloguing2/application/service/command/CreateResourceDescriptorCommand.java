package my.com.myriadeas.integral.cataloguing2.application.service.command;

import org.marc4j.marc.Record;

public class CreateResourceDescriptorCommand {
	
	private Record record;

	public CreateResourceDescriptorCommand(Record record) {
		super();
		this.record = record;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
	
}
