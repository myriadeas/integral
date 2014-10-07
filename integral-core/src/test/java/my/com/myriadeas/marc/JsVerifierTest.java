package my.com.myriadeas.marc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsVerifier;

import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
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
			FileInputStream fis = new FileInputStream("data/669564.mrc");
			Record record = null;
			MarcReader reader = new MarcStreamReader(fis);
			while (reader.hasNext()) {
				record = reader.next();
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MarcJsonWriter writer = new MarcJsonWriter(baos);
			writer.write(record);
			String json = baos.toString();

			String[] scripts = { "rules.js","marc21.js" };
			js.verify(scripts, json);
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
