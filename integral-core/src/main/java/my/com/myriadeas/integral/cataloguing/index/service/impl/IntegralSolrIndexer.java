package my.com.myriadeas.integral.cataloguing.index.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import my.com.myriadeas.integral.cataloguing.exception.IntegralSolrIndexerTransformationException;
import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.data.solr.domain.VuFindMarc;

import org.marc4j.ErrorHandler;
import org.marc4j.marc.Record;
import org.solrmarc.index.SolrIndexer;

public class IntegralSolrIndexer implements IntegralIndexer {

	private final SolrIndexer vufindIndexer;// we try not to modify solr indexer
											// from

	// solrmac.why use this because cglib
	// proxy unable to create proxy of final
	// constructor

	public IntegralSolrIndexer(SolrIndexer vufindIndexer) {
		this.vufindIndexer = vufindIndexer;
	}

	@SuppressWarnings("unchecked")
	public VuFindMarc mapVufindMarc(Record record)
			throws IntegralSolrIndexerTransformationException {
		VuFindMarc vufindMarc = new VuFindMarc();
		ErrorHandler errors = new ErrorHandler();
		Map<String, Object> results = vufindIndexer.map(record, errors);
		for (Entry<String, Object> result : results.entrySet()) {
			String fieldName = result.getKey().replace("-", "_");
			fieldName = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			Method getter;
			try {
				Object value = null;
				getter = VuFindMarc.class.getMethod("get" + fieldName);
				String typeName = getter.getReturnType().getCanonicalName();
				if (typeName.equals("java.util.LinkedHashSet")
						&& result.getValue() instanceof String) {
					value = new java.util.LinkedHashSet<String>();
					((java.util.LinkedHashSet<String>) value)
							.add((String) result.getValue());
				} else {
					value = result.getValue();
				}
				Method setter = VuFindMarc.class.getMethod("set" + fieldName,
						value.getClass());
				setter.invoke(vufindMarc, value);

			} catch (NoSuchMethodException e) {
				throw new IntegralSolrIndexerTransformationException(
						"Transformation Exception", e);
			} catch (SecurityException e) {
				throw new IntegralSolrIndexerTransformationException(
						"Transformation Exception", e);
			} catch (IllegalAccessException e) {
				throw new IntegralSolrIndexerTransformationException(
						"Transformation Exception", e);
			} catch (IllegalArgumentException e) {
				throw new IntegralSolrIndexerTransformationException(
						"Transformation Exception", e);
			} catch (InvocationTargetException e) {
				throw new IntegralSolrIndexerTransformationException(
						"Transformation Exception", e);
			}
		}
		return vufindMarc;
	}
}
