package my.com.myriadeas.integral.cataloguing2.application.service;

import my.com.myriadeas.integral.cataloguing2.application.service.command.VerifyResourceDescriptorCommand;

import org.marc4j.marc.Record;

public interface ResourceDescriptorReadService {

	public String verifyRecord(
			VerifyResourceDescriptorCommand verifyResourceDescriptorCommand);

	public String convertRecord(Record record);

}
