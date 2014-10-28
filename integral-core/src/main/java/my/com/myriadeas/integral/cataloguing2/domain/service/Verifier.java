package my.com.myriadeas.integral.cataloguing2.domain.service;

import org.marc4j.marc.Record;

public interface Verifier {

	public boolean isValid(Record record);
	
	public String verify(Record record);

}
