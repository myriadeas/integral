package my.com.myriadeas.integral.cataloguing.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.exception.RecordNotFoundException;
import my.com.myriadeas.integral.cataloguing.service.BibDetailsRetrieverService;
import my.com.myriadeas.integral.data.jpa.domain.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service(value = "solrRetriever")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrRetrieverService implements BibDetailsRetrieverService {

	@Value("${solr.server.biblio.url}")
	private String solrServerBiblioUrl;

	@Autowired
	MaterialRepoRetrieverService materialRepoRetrieverService;

	private Client client = Client.create();

	private Logger logger = LoggerFactory.getLogger(SolrRetrieverService.class);

	public Map<String, String> get(String id) {

		List<Map<String, String>> docs = query("id:" + id);
		return docs.get(0);

	}

	public Map<String, String> getTitle(String id) {
		return getBib(id, "title_full");
	}

	public Map<String, String> getBib(String id, String tag) {
		Map<String, String> record = get(id);
		Map<String, String> result = new HashMap<String, String>();
		result.put(tag, record.get(tag));
		return result;
	}

	public List<Map<String, String>> query(String query) {
		logger.debug("Entering query=(query={})", query);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			String encodedUrl = URLEncoder.encode(query, "UTF-8");
			WebResource webResource = client.resource(solrServerBiblioUrl
					+ "/select/?wt=json&rows=99999999&q=" + encodedUrl);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String json = response.getEntity(String.class);

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> solrMap = mapper.readValue(json, Map.class);
			Map<String, Object> responseMap = (Map<String, Object>) solrMap
					.get("response");
			result = (List<Map<String, String>>) responseMap.get("docs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Leaving query=(result={})", result);
		return result;
	}

	public String queryOri(String query) {
		logger.debug("Entering query=(query={})", query);
		String result = "";
		try {
			String encodedUrl = URLEncoder.encode(query, "UTF-8");
			WebResource webResource = client.resource(solrServerBiblioUrl
					+ "/select/?wt=json&rows=99999999&q=" + encodedUrl);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			result = response.getEntity(String.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Leaving query=(result={})", result);
		return result;
	}

	@Override
	public List<Material> querySolrGetMaterials(String query) {
		List<Material> materials = new ArrayList<Material>();
		List<Map<String, String>> solrDocs = query(query);
		for (Map<String, String> solrDoc : solrDocs) {
			String id = solrDoc.get("id");
			try {
				Material material = materialRepoRetrieverService
						.getMaterial(id);
				materials.add(material);
			} catch (RecordNotFoundException e) {
				logger.debug("RecordNotFoundException=(material no={})", id);
			}
			
		}
		return materials;
	}

}
