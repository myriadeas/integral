package my.com.myriadeas.integral.cataloguing2.domain.service;

import org.marc4j.marc.Record;

public interface Converter {
	
	public String convert(Record record);
}
