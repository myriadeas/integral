package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcXmlWriter;
import org.marc4j.marc.Record;

public class MarcXmlConverter {
	
	public ByteArrayOutputStream convertMarcToMarcXml(InputStream input) {
		MarcReader reader = new MarcStreamReader(input);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		MarcXmlWriter writer = new MarcXmlWriter(bytes);
		while (reader.hasNext()) {
			Record record = reader.next();
			writer.write(record);
		}
		writer.close();
		return bytes;
	}
	
	public List<Record> convertMarcToListOfRecords(InputStream input) {
		MarcReader reader = new MarcStreamReader(input);
		List <Record> records = new ArrayList<Record>();
		while (reader.hasNext()) {
			Record record = reader.next();
			records.add(record);
		}
		return records;
	}
	
	public List<Record>convertMarcToListOfRecords(String marc) {
		InputStream input = new ByteArrayInputStream(marc.getBytes());
		List<Record> records = this
				.convertMarcToListOfRecords(input);
		return records;
	}
}
