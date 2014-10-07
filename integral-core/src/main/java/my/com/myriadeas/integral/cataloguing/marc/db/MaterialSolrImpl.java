package my.com.myriadeas.integral.cataloguing.marc.db;

import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.cataloguing.exception.DeleteFailureException;
import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;
import my.com.myriadeas.integral.cataloguing.exception.UpdateFailureException;
import my.com.myriadeas.integral.cataloguing.index.service.IndexService;
import my.com.myriadeas.integral.cataloguing.marc.dao.MarcDao;

import org.marc4j.marc.Record;
import org.marc4j.marc.impl.RecordImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "materialSolrImpl")
public class MaterialSolrImpl extends MaterialRepoImpl implements MarcDao {

	@Autowired
	@Qualifier("solrIndexServiceImpl")
	private IndexService indexService;

	private Logger logger = LoggerFactory.getLogger(MaterialSolrImpl.class);

	@Override
	public Record createRecord(String library, String userId, Record record)
			throws IndexFailureException, RuntimeException,
			UnsupportedEncodingException {

		try {
			record = super.createRecord(library, userId, record);
			indexService.index(record, record.getControlNumber());
			logger.debug("CreateRecord = " + record.getControlNumber());
		} catch (Exception e) {
			throw new IndexFailureException(e.getMessage());
		}
		return record;
	}

	@Override
	public Record updateRecord(String library, String userId, String recordId,
			Record record) throws IndexFailureException {

		try {
			record = super.updateRecord(library, userId, recordId, record);
			indexService.index(record, record.getControlNumber());
		} catch (Exception e) {
			throw new UpdateFailureException(e.getMessage());
		}

		return record;

	}

	@Override
	public Record deleteRecord(String library, String userId, String recordId)
			throws IndexFailureException {

		Record record = new RecordImpl();
		try {

			record = super.deleteRecord(library, userId, recordId);
			if (record.getControlNumber() != null) {
				indexService.unindex(record.getControlNumber());
			}
		} catch (Exception e) {
			throw new DeleteFailureException(e.getMessage());
		}
		return record;
	}

}
