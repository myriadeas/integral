package my.com.myriadeas.integral.cataloguing.marc.db;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.CatalogType;
import my.com.myriadeas.integral.cataloguing.CataloguingEvents;
import my.com.myriadeas.integral.cataloguing.exception.DeleteFailureException;
import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;
import my.com.myriadeas.integral.cataloguing.exception.RecordNotFoundException;
import my.com.myriadeas.integral.cataloguing.exception.UpdateFailureException;
import my.com.myriadeas.integral.cataloguing.marc.dao.MarcDao;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToString;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.enumeration.CataloguingStatusEnum;
import my.com.myriadeas.integral.publisher.Publisher;

import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "materialRepoImpl")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialRepoImpl implements MarcDao, CatalogType {

	private Logger logger = LoggerFactory.getLogger(MaterialRepoImpl.class);

	@Autowired
	private MaterialRepositoryImpl materialRepo;

	@Autowired
	private ItemRepositoryImpl itemRepo;

	@Autowired
	protected Publisher publisher;

	RecordToString recordToString = new RecordToString();

	public Map<String, String> getAllRecords(String library, String userid) {

		logger.debug("Entering getAllRecords().");

		Map<String, String> records = new LinkedHashMap<String, String>();
		List<Material> materials = materialRepo.findAll();
		for (Material material : materials) {
			records.put(material.getMaterialNo() + "", material.getTitle());
		}

		logger.debug("Leaving getAllRecords(). records=" + records);
		return records;
	}

	public Record getRecord(String library, String userId, String materialNo) {
		logger.debug("Entering getRecord=(recordId={})", materialNo);
		Record record = getRecord(materialNo).getMarcRecord();
		logger.debug("Leaving getRecord=(record={})", record);
		return record;
	}

	public Material getRecord(String materialNo) {
		Material material = materialRepo.findByMaterialNo(materialNo);
		if (material == null) {
			throw new RecordNotFoundException(
					"Unable to find material by material number: " + materialNo);
		}
		return materialRepo.findByMaterialNo(materialNo);
	}

	public Record createRecord(String library, String userId, Record record)
			throws IndexFailureException, RuntimeException,
			UnsupportedEncodingException {
		logger.debug("Entering createRecord=(record={})", record);

		try {
			Material material = materialRepo.save(new Material.MaterialBuilder(
					record).status(CataloguingStatusEnum._PENDING_INDEX)
					.build());
			record = material.getMarcRecord();
			publisher.publish(CataloguingEvents.UPDATE, record);
			logger.debug("Leaving createRecord=(material={})", material);
		} catch (Exception e) {
			throw new IndexFailureException(e.getMessage());
		}

		return record;
	}

	public Record updateRecord(String library, String userId,
			String materialNo, Record record) throws IndexFailureException {

		logger.debug("Entering updateRecord=(record={})", record);
		Material material = new Material();
		try {
			material = getRecord(materialNo);
			material.setMarc(recordToString.convert(record));
			record = updateRecord(material, CataloguingStatusEnum._PENDING_INDEX);
			publisher.publish(CataloguingEvents.UPDATE, record);
		} catch (Exception e) {
			throw new UpdateFailureException(e.getMessage());
		}

		logger.debug("Leaving updateRecord=(material={})", material);
		return record;

	}

	public Record updateRecord(Material material, String status) {
		try {
			material.setStatus(status);
			material = materialRepo.save(material);
		} catch (Exception e) {
			throw new UpdateFailureException(e.getMessage());
		}

		return material.getMarcRecord();
	}

	public Record deleteRecord(String library, String userId, String materialNo)
			throws IndexFailureException {

		logger.debug("Entering deleteRecord=(materialNo={})", materialNo);
		Record record = getRecord(library, userId, materialNo);

		if (record != null) {
			Material material = getRecord(materialNo);
			if (itemRepo.findByMaterial(material).size() > 0){
				throw new DeleteFailureException("Material is associated with item(s). Unable to delete.");
			}
			
			try {
				record = updateRecord(material, CataloguingStatusEnum._PENDING_DELETE);
				publisher.publish(CataloguingEvents.DELETE, record);
			} catch (Exception e) {
				throw new DeleteFailureException(e.getMessage());
			}
		} else {
			throw new RecordNotFoundException("Record not found.");
		}

		logger.debug("Leaving deleteRecord=(record={})", record);
		return record;
	}

	public List<String> verifyRecord(String library, String userId,
			Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> verifyAllRecords(String library, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> submitAllRecords(String library, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
