package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.ByteArrayOutputStream;

import org.marc4j.MarcJsonWriter;
import org.marc4j.marc.Record;

public class RecordToJson {

	private static final String DEFAULT_ENCODING = "UTF8";

	public static String convert(Record record) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MarcJsonWriter writer = new MarcJsonWriter(baos);
		writer.write(record);
		return baos.toString();
	}

}
