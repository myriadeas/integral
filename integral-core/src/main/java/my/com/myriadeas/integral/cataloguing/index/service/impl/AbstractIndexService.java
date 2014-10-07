package my.com.myriadeas.integral.cataloguing.index.service.impl;

import java.util.List;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;
import my.com.myriadeas.integral.cataloguing.exception.UnexpectedRecordSize;
import my.com.myriadeas.integral.cataloguing.index.service.IndexService;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.MarcXmlConverter;

import org.marc4j.marc.Record;
import org.springframework.stereotype.Service;

@Service("indexServiceImpl")
public abstract class AbstractIndexService implements IndexService {

	private final MarcXmlConverter marcXmlConverter;

	public AbstractIndexService() {
		marcXmlConverter = new MarcXmlConverter();
	}

	@Override
	public void index(String record, String materialNo)
			throws IndexFailureException {
		List<Record> marcRecords = marcXmlConverter
				.convertMarcToListOfRecords(record);
		if (marcRecords == null || marcRecords.size() != 1) {
			throw new UnexpectedRecordSize(
					"Marc Record expected to be 1. Marc record size= "
							+ marcRecords.size());
		}
		index(marcRecords.get(0), materialNo);
	}

}
