package my.com.myriadeas.integral.cataloguing.facade.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;
import my.com.myriadeas.integral.cataloguing.exception.UploadFailureException;
import my.com.myriadeas.integral.cataloguing.facade.AbstractFacadeService;
import my.com.myriadeas.integral.cataloguing.facade.MarcServiceFacade;
import my.com.myriadeas.integral.cataloguing.marc.dao.MarcDao;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsConverter;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsVerifier;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.StringToRecord;
import my.com.myriadeas.integral.cataloguing.service.BibDetailsRetrieverService;
import my.com.myriadeas.integral.data.jpa.domain.Material;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.json.simple.JSONValue;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "marcService")
public class MarcServiceFacadeImpl extends AbstractFacadeService implements MarcServiceFacade {

	@Value("${marc.verification.scripts}")
	private String[] verificationScripts;

	@Value("${marc.convertion.scripts}")
	private String[] convertionScripts;

	private JsVerifier jsVerifier = new JsVerifier();
	private JsConverter jsConverter = new JsConverter();

	private MarcDao marcDao;
	private BibDetailsRetrieverService solrRetriever;
	private BibDetailsRetrieverService materialRetriever;

	@Autowired
	@Qualifier("materialRepoImpl")
	public void setMarcDao(MarcDao marcDao) {
		this.marcDao = marcDao;
	}

	@Autowired
	@Qualifier("solrRetriever")
	public void setSolrRetriever(BibDetailsRetrieverService solrRetriever) {
		this.solrRetriever = solrRetriever;
	}

	@Autowired
	@Qualifier("materialRepoRetriever")
	public void setMaterialRetriever(
			BibDetailsRetrieverService materialRetriever) {
		this.materialRetriever = materialRetriever;
	}

	public Map<String, Map<String, String>> list(String library, String userid) {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		map.put("records", marcDao.getAllRecords(library, userid));
		return map;
	}

	public Record create(String library, String userid, Record record)
			throws UnsupportedEncodingException, RuntimeException {
		record = marcDao.createRecord(library, userid, record);
		return record;
	}

	public Record retrieve(String library, String userid, String marcid) {
		return marcDao.getRecord(library, userid, marcid);
	}

	public Record update(String library, String userid, String marcid,
			Record record) {
		record = marcDao.updateRecord(library, userid, marcid, record);
		return record;
	}

	public Record delete(String library, String userid, String marcid)
			throws IndexFailureException {
		Record record = marcDao.deleteRecord(library, userid, marcid);
		return record;
	}

	public String verify(String library, String userid, String json) {

		Map<String, Object> obj = new LinkedHashMap<String, Object>();

		boolean valid = jsVerifier.verify(verificationScripts, json);

		obj.put("valid", valid);
		obj.put("msg", jsVerifier.getMsg());
		obj.put("errMsg", jsVerifier.getErrMsg());
		if (valid) {
			obj.put("message", getMessage("marcServiceFacade.verify.valid", "Record is valid"));
		} else {
			obj.put("message", jsVerifier.getErrMsg());
		}

		return mapToJsonString(obj);

	}

	public String convert(String library, String userid, String json) {
		return jsConverter.convert(convertionScripts, json);
	}

	public String checkDuplicate(String library, String userid, String tag,
			char subfield, Record record) {

		Map<String, Object> result = new HashMap<String, Object>();
		String query = "";
		String term = "";
		String searchTerm = "";
		List<DataField> fields = record.getDataFields();
		for (DataField field : fields) {
			if (field.getTag().equals(tag)) {
				term = field.getSubfield(subfield).toString().substring(2)
						.trim();
				searchTerm = term;
				if (term.indexOf(" ") > 0) {
					searchTerm = searchTerm.substring(0, term.indexOf(" "));
				}
				if (tag.equals("020")) {
					query = "isbn:" + searchTerm.toLowerCase() + "*";
				} else if (tag.equals("022")) {
					query = "issn:" + searchTerm.toLowerCase() + "*";
				}

				result.put("query", query);

				if (termExists(query, tag, term)) {
					result.put("exists", true);
					break;
				}
			}
		}

		if (result.get("exists") == null) {
			result.put("exists", false);
		}

		return mapToJsonString(result);
	}

	public String checkDuplicate(String library, String userid, String tag,
			Record record) {

		return checkDuplicate(library, userid, tag, 'a', record);
	}

	private boolean termExists(String query, String tag, String term) {
		if (query.length() != 0 && tag.length() != 0 && term.length() != 0) {
			List<Map<String, String>> docs = solrRetriever.query(query);
			Object o = null;
			for (Map<String, String> doc : docs) {
				if (tag.equals("020")) {
					o = (Object) doc.get("isbn");
				} else if (tag.equals("022")) {
					o = (Object) doc.get("issn");
				}
				List<String> listOfTerm = (List<String>) o;
				for (String solrTerm : listOfTerm) {
					if (solrTerm.equals(term)) {
						return true;
					}
				}

			}
		}
		return false;
	}

	public Map<String, String> getFromSolr(String id) {
		return solrRetriever.get(id);
	}

	public Map<String, String> getTitleFromSolr(String id) {
		return solrRetriever.getTitle(id);
	}

	public Map<String, String> getBibFromSolr(String id, String tag) {
		return solrRetriever.getBib(id, tag);
	}

	public List<Map<String, String>> querySolr(String query) {
		return solrRetriever.query(query);
	}

	public String querySolrOri(String query) {
		return solrRetriever.queryOri(query);
	}

	public List<Material> querySolrGetMaterials(String query) {
		return solrRetriever.querySolrGetMaterials(query);
	}

	public Map<String, String> getTitleFromMaterial(String id) {
		return materialRetriever.getTitle(id);
	}

	public Map<String, String> getBibFromMaterial(String id, String tag) {
		return materialRetriever.getBib(id, tag);
	}

	private String mapToJsonString(Map map) {
		StringWriter out = new StringWriter();
		try {
			JSONValue.writeJSONString(map, out);
			String jsonText = out.toString();
			return jsonText;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public Record uploadFile(LinkedHashMap<String, Object> attachments,
			String library, String userid) {

		InputStream is = null;
		Record record = null;
		StringToRecord stringToRecord = new StringToRecord();
		for (Map.Entry<String, Object> attachment : attachments.entrySet()) {
			if (!attachment.getKey().equals("text/plain")) {
				Attachment att = (Attachment) attachment.getValue();
				try {
					is = att.getDataHandler().getInputStream();
					record = stringToRecord.convert(is).get(0);
				} catch (IOException e) {
					throw new UploadFailureException(
							"Failed to upload file due to " + e.getMessage());
				}
				break;
			}
		}
		return record;
	}
}
