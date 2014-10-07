package my.com.myriadeas.integral.index.domain.service;

import my.com.myriadeas.integral.index.domain.model.VuFindMarc;

import org.marc4j.marc.Record;

public interface Indexer {

	public VuFindMarc mapVufindMarc(Record record);
}
