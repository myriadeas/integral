package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Scriptable;

public class JsConverter {

	public String convert(String scripts[], String json) {
		String result = null;
		try {
			Context cx = Context.enter();
			Scriptable scope = cx.initStandardObjects();

			// load all js files
			for (String script : scripts) {
				InputStream is = getClass().getResourceAsStream("/" + script);
				cx.evaluateReader(scope, new InputStreamReader(is), script, 1,
						null);
				// cx.evaluateReader(scope, new FileReader(script), script, 1,
				// null);
			}

			scope.put("json", scope, json);
			result = (String) cx.evaluateString(scope, "convert(json);",
					"<cmd>", 1, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EvaluatorException e) {
			e.printStackTrace();
		} catch (EcmaError e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
