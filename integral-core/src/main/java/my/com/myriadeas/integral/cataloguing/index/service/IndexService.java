package my.com.myriadeas.integral.cataloguing.index.service;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;

import org.marc4j.marc.Record;

public interface IndexService {

	public void index(Record record, String materialNo)
			throws IndexFailureException;

	public void index(String record, String materialNo)
			throws IndexFailureException;

	public void unindex(String id) throws IndexFailureException;

}
