package my.com.myriadeas.marc.utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsVerifier;

import org.marc4j.MarcJsonReader;
import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.marc.Record;

public class JsVerifierTest extends TestCase {

	JsVerifier js;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		js = new JsVerifier();
	}

	public void testVerify() {
		try {
			FileInputStream fis = new FileInputStream(
					"src/test/resources/669564.mrc");

			MarcStreamReader reader = new MarcStreamReader(fis);
			while (reader.hasNext()) {
				Record record = reader.next();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MarcJsonWriter writer = new MarcJsonWriter(baos,
						MarcJsonWriter.MARC_IN_JSON);
				// writer.setIndent(true);
				writer.write(record);
				writer.close();
				String jsonOri = baos.toString();
				String[] scripts = {
						"marc21rules.js", "rules.js" };
				js.verify(scripts, jsonOri);

				InputStream input = new ByteArrayInputStream(jsonOri.getBytes());

				Record marcRecord = null;
				MarcJsonReader marcJsonReader = new MarcJsonReader(input);
				while (marcJsonReader.hasNext()) {
					marcRecord = marcJsonReader.next();
				}
				System.out.println(marcRecord);
				MarcWriter marcWriter = new MarcStreamWriter(baos);
				marcWriter.write(marcRecord);
				marcWriter.close();
				System.out.println(baos.toString());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String loadJavascript(String filename) {
		String javascript = "";
		String buffer = null;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			buffer = reader.readLine();
			javascript = buffer + "\n";
			while (buffer != null) {
				buffer = reader.readLine();
				if (buffer != null) {
					javascript += buffer + "\n";
				}
			}
			;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return javascript;
	}
}
