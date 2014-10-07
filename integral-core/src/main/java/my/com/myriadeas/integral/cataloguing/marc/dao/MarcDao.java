package my.com.myriadeas.integral.cataloguing.marc.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;

import org.marc4j.marc.Record;

public interface MarcDao {
	public Map<String, String> getAllRecords(String library, String userId);

	public Record getRecord(String library, String userId, String recordId);

	public Record createRecord(String library, String userId, Record record)
			throws IndexFailureException, RuntimeException,
			UnsupportedEncodingException;

	public Record updateRecord(String library, String userId, String recordId,
			Record record) throws IndexFailureException;

	public Record deleteRecord(String library, String userId, String recordId)
			throws IndexFailureException;

	public List<String> verifyRecord(String library, String userId,
			Record record);

	public List<String> verifyAllRecords(String library, String userId);

	public List<String> submitAllRecords(String library, String userId);
}
