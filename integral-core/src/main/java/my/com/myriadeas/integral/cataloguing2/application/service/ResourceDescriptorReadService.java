package my.com.myriadeas.integral.cataloguing2.application.service;

import org.marc4j.marc.Record;

public interface ResourceDescriptorReadService {

	public String verifyRecord(Record record);

	public String convertRecord(Record record);

}
