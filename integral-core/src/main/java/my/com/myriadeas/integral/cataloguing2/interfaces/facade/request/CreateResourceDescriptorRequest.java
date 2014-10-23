package my.com.myriadeas.integral.cataloguing2.interfaces.facade.request;

import org.marc4j.marc.Record;

public class CreateResourceDescriptorRequest {
	
	private Record record;

	public CreateResourceDescriptorRequest(Record record) {
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
