package my.com.myriadeas.integral.cataloguing.index.service.impl;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;
import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.index.service.IndexService;
import my.com.myriadeas.integral.data.solr.domain.VuFindMarc;
import my.com.myriadeas.integral.data.solr.repository.VuFindMarcRepository;

import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "solrIndexServiceImpl")
public class SolrIndexServiceImpl extends AbstractIndexService implements
		IndexService {

	private static final Logger logger = LoggerFactory
			.getLogger(SolrIndexServiceImpl.class);

	@Autowired
	private VuFindMarcRepository repository;

	@Autowired
	private IntegralIndexer integralIndexer;

	public SolrIndexServiceImpl() {
		super();
	}

	@Override
	public void index(Record record, String materialNo)
			throws IndexFailureException {
		MarcFactory factory = MarcFactory.newInstance();
		record.addVariableField(factory.newControlField("001", materialNo));
		System.out.println(materialNo + ", " + record.getControlNumber() + ", " + record.getControlFields().get(0));
		logger.debug("Map record to vufindMarc");
		VuFindMarc vufindMarc = integralIndexer.mapVufindMarc(record);
		logger.debug("Save vufindMarc");
		System.out.println(vufindMarc.getId());
		repository.save(vufindMarc);		
		logger.debug("Done index solr");
	}

	public void unindex(String id) throws IndexFailureException {
		repository.delete(id);
		logger.debug("Done unindex solr");
	}
}
