package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;

public class JsVerifier {
	
	private String msg;

	public String getMsg() {
		return msg;
	}
	
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public boolean verify(String scripts[], String json) {
		boolean valid = false;
		try {
			Context cx = Context.enter();
			Scriptable scope = cx.initStandardObjects();

			// load all js file
			for (String script : scripts) {
				InputStream is = getClass().getResourceAsStream("/" + script);
				cx.evaluateReader(scope,  new InputStreamReader(is), script, 1,
						null);
				
				//cx.evaluateReader(scope,  new FileReader(script), script, 1, null);
			}

			scope.put("json", scope, json);
			Object result = cx.evaluateString(scope,
					"verify(json);", "<cmd>", 1, null);
			
			msg = (String) getValueFromJs((NativeObject) result, "msg");
			errMsg = (String) getValueFromJs((NativeObject) result, "errMsg");
			return Boolean.valueOf((String) getValueFromJs((NativeObject) result, "valid"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EvaluatorException e) {
			e.printStackTrace();
		} catch (EcmaError e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return valid;
	}

	private void traverseObject(NativeObject object) {
		for (Entry<Object, Object> entry : object.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof NativeArray) {
				System.out.println(entry.getKey() + ": [");
				traverseObject((NativeArray) value);
				System.out.println("]");
			} else if (value instanceof NativeObject) {
				System.out.println(entry.getKey() + ": {");
				traverseObject((NativeObject) value);
				System.out.println("}");
			} else {
				System.out.println(entry.getKey() + ": " + value + " {"
						+ value.getClass() + "}");
			}
		}
	}

	private void traverseObject(NativeArray array) {
		for (Object object : array) {
			traverseObject((NativeObject) object);
		}
	}

	private Object getValueFromJs(NativeObject obj, String key) {
		Map<String, Object> mapParams = new HashMap<String, Object>();
		for (Entry<Object, Object> e : obj.entrySet()){
			   mapParams.put(e.getKey().toString(), e.getValue().toString());
			}
		return mapParams.get(key);
	}
}
