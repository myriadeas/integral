package my.com.myriadeas.marc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;

import junit.framework.TestCase;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsConverter;

import org.marc4j.MarcJsonReader;
import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;

public class JsConverterTest extends TestCase {

	JsConverter js;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		js = new JsConverter();
	}

	public void testVerify() {
		try {
			// get the marc record to convert
			FileInputStream fis = new FileInputStream("data/669564.mrc");
			Record record = null;
			MarcReader reader = new MarcStreamReader(fis);
			while (reader.hasNext()) {
				record = reader.next();
			}

			// transform the record to JSON
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MarcJsonWriter writer = new MarcJsonWriter(baos);
			writer.write(record);
			String json = baos.toString();

			// convert the record in JSON
			String[] scripts = { "convert.js" };
			// get back the record in JSON
			String convertedJson = js.convert(scripts, json);

			// transform JSON to record
			MarcJsonReader jsonReader = new MarcJsonReader(new StringReader(
					convertedJson));
			while (jsonReader.hasNext()) {
				record = jsonReader.next();
			}
			System.out.println(record);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
