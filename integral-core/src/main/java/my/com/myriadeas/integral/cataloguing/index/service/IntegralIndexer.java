package my.com.myriadeas.integral.cataloguing.index.service;

import my.com.myriadeas.integral.cataloguing.exception.IntegralSolrIndexerTransformationException;
import my.com.myriadeas.integral.data.solr.domain.VuFindMarc;

import org.marc4j.marc.Record;

public interface IntegralIndexer {

	public VuFindMarc mapVufindMarc(Record record)
			throws IntegralSolrIndexerTransformationException;
}
