package my.com.myriadeas.integral.cataloguing2.domain.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsVerifier;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToJson;

import org.json.simple.JSONValue;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "resourceDescriptorVerifier")
public class VerifierImpl implements Verifier {

	private JsVerifier jsVerifier = new JsVerifier();

	@Value("${marc.verification.scripts}")
	private String[] verificationScripts;

	@Override
	public boolean isValid(Record record) {
		return jsVerifier.verify(this.verificationScripts,
				RecordToJson.convert(record));
	}

	@Override
	public String verify(Record record) {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();

		boolean valid = jsVerifier.verify(this.verificationScripts,
				RecordToJson.convert(record));

		obj.put("valid", valid);
		obj.put("msg", jsVerifier.getMsg());
		obj.put("errMsg", jsVerifier.getErrMsg());
		if (valid) {
			obj.put("message", "VerifierImpl.verify.valid: Record is valid");
		} else {
			obj.put("message", jsVerifier.getErrMsg());
		}

		return mapToJsonString(obj);
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

}
