package my.com.myriadeas.integral.cataloguing2.marc.domain.service;

import my.com.myriadeas.integral.cataloguing2.marc.model.RecordType;

import org.marc4j.marc.Record;

public interface MarcRecordConverter {

	public RecordType convert(Record record)
			throws RecordToRecordTypeConvertException;
}
