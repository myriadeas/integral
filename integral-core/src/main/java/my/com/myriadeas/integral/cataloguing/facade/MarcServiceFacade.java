package my.com.myriadeas.integral.cataloguing.facade;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;

import org.marc4j.marc.Record;

public interface MarcServiceFacade {

	public Map<String, Map<String, String>> list(String library, String userid);

	public Record create(String library, String userid, Record record)
			throws UnsupportedEncodingException, RuntimeException;

	public Record retrieve(String library, String userid, String marcid);

	public Record update(String library, String userid, String marcid,
			Record record);

	public Record delete(String library, String userid, String marcid)
			throws IndexFailureException;

	public String verify(String library, String userid, String json);

	public String convert(String library, String userid, String json);

	public String checkDuplicate(String library, String userid, String tag,
			char subfield, Record record);

	public String checkDuplicate(String library, String userid, String tag,
			Record record);

	public Map<String, String> getFromSolr(String id);

	public Map<String, String> getTitleFromSolr(String id);

	public Map<String, String> getBibFromSolr(String id, String tag);

	public List<Map<String, String>> querySolr(String query);

	public Map<String, String> getTitleFromMaterial(String id);

	public Map<String, String> getBibFromMaterial(String id, String tag);
}
