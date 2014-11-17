package my.com.myriadeas.integral.cataloguing2.application.service.command;

import org.marc4j.marc.Record;

public class ReviseResourceDescriptorCommand {

	private Long id;

	private Record record;

	public ReviseResourceDescriptorCommand(Long id, Record record) {
		super();
		this.id = id;
		this.record = record;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
