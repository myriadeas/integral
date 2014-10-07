package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.marc4j.MarcException;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;

public class StringToRecord {
	
	private static final String DEFAULT_ENCODING = "UTF8";


	public Record convert(String marc) throws MarcException {
		try {
			
			return convert(new ByteArrayInputStream(marc.getBytes(DEFAULT_ENCODING))).get(0);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Record> convert(InputStream marc) throws MarcException {
		List<Record> records = new ArrayList<Record>();
		MarcReader reader = new MarcStreamReader(marc, DEFAULT_ENCODING);
		Record record = null;
		while (reader.hasNext()) {
			record = reader.next();
			records.add(record);
		}
		return records;
	}
	
	

}
