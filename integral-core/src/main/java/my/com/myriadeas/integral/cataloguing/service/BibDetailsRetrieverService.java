package my.com.myriadeas.integral.cataloguing.service;

import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.data.jpa.domain.Material;

public interface BibDetailsRetrieverService {
	
	public Map<String, String> get(String id);
	
	public Map<String, String> getTitle(String id);
	
	public Map<String, String> getBib(String id, String tag);
	
	public List<Map<String, String>> query(String query);
	
	public String queryOri(String query);
	
	public List<Material> querySolrGetMaterials (String query);
	
	
}
