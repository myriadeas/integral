package my.com.myriadeas.integral.cataloguing.handler.impl;

import my.com.myriadeas.integral.cataloguing.exception.RecordNotFoundException;
import my.com.myriadeas.integral.cataloguing.index.service.IndexService;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.enumeration.CataloguingStatusEnum;

import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pendingDeleteHandler")
public class PendingDeleteHandlerImpl {
	private static final Logger logger = LoggerFactory
			.getLogger(PendingDeleteHandlerImpl.class);

	@Autowired
	@Qualifier("solrIndexServiceImpl")
	private IndexService indexService;

	@Autowired
	private MaterialRepositoryImpl materialRepo;

	public void handle(Record record) {
		logger.debug("Entering handle(record={})", record);

		String materialNo = record.getControlNumber();
		Material material = materialRepo.findByMaterialNo(materialNo);
		if (material == null) {
			throw new RecordNotFoundException(
					"Unable to find material by material number: " + materialNo);
		}
		if (material.isPendingDelete()){
			indexService.unindex(materialNo);
			material.setStatus(CataloguingStatusEnum.DELETED);
			materialRepo.save(material);
		}
		logger.debug("Leaving handle(). record={}", record);
	}

}
