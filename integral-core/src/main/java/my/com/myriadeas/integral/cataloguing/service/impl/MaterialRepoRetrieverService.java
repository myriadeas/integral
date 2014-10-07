package my.com.myriadeas.integral.cataloguing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.exception.RecordNotFoundException;
import my.com.myriadeas.integral.cataloguing.service.BibDetailsRetrieverService;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "materialRepoRetriever")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialRepoRetrieverService implements BibDetailsRetrieverService {

	private Logger logger = LoggerFactory
			.getLogger(MaterialRepoRetrieverService.class);

	@Autowired
	private MaterialRepositoryImpl materialRepo;

	protected Material getMaterial(String id) {
		Material material = materialRepo.findByMaterialNo(id);
		if (material == null) {
			throw new RecordNotFoundException("Record not found");
		}
		return material;
	}

	public Map<String, String> get(String id) {
		// Not implemented
		return null;
	}

	public Map<String, String> getTitle(String id) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("245", getMaterial(id).getTitle());
		return result;
	}

	public Map<String, String> getBib(String id, String tag) {
		Map<String, String> result = new HashMap<String, String>();
		result.put(tag, getMaterial(id).getBib(tag));
		return result;
	}

	public List<Map<String, String>> query(String query) {
		// Not implemented
		return null;
	}

	@Override
	public String queryOri(String query) {
		// Not implemented
		return null;
	}

	@Override
	public List<Material> querySolrGetMaterials(String query) {
		// Not implemented
		return null;
	}

}
