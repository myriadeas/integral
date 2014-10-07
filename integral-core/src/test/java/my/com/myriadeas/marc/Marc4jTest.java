package my.com.myriadeas.marc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.marc4j.MarcJsonReader;
import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;

/**
 * Unit test for simple App.
 */
public class Marc4jTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public Marc4jTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(Marc4jTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMarc4j() {
		try {
			FileInputStream fis = new FileInputStream("data/669564.mrc");
			Record record = null;
			MarcReader reader = new MarcStreamReader(fis);
			while (reader.hasNext()) {
				record = reader.next();
			}
			System.out.println(record);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testMarcToJson() {
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
			System.out.println(baos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testChineseMarcToJson() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			FileInputStream fis = new FileInputStream("data/malaixiya .mrc");
			Record record = null;
			MarcReader reader = new MarcStreamReader(fis, "UTF-8");
			while (reader.hasNext()) {
				record = reader.next();
			}
			MarcJsonWriter writer = new MarcJsonWriter(baos);
			writer.write(record);
			System.out.println(baos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baos.close();
		}
	}

	public void testUnicode() {
		// create a factory instance
		MarcFactory factory = MarcFactory.newInstance();

		// create a record with leader
		Record record = factory.newRecord("00000cam a2200000 a 4500");

		// add a control field
		record.addVariableField(factory.newControlField("001", "12883376"));

		// add a data field
		DataField df = factory.newDataField("245", '1', '0');
		df.addSubfield(factory.newSubfield('a', "Summerland 萧美儿/"));
		df.addSubfield(factory.newSubfield('c', "Michael Chabon."));
		record.addVariableField(df);

		//MarcWriter writer = new MarcStreamWriter(System.out, "UTF-8");
		//writer.write(record);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MarcJsonWriter jsonWriter = new MarcJsonWriter(baos);
		jsonWriter.write(record);
		System.out.println(baos);
	}

	public void testJsonToMarc() {
		try {
			FileInputStream fis = new FileInputStream("data/669564.json");
			Record record = null;
			MarcJsonReader reader = new MarcJsonReader(fis);
			while (reader.hasNext()) {
				record = reader.next();
			}
			String encoding = "UTF8";
			MarcWriter writer = new MarcStreamWriter(System.out, encoding);
			writer.write(record);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
