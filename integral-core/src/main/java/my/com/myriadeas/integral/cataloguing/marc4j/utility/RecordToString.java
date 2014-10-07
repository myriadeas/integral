package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.marc.Record;

public class RecordToString {

	private static final String DEFAULT_ENCODING = "UTF8";

	public String convert(Record record) throws UnsupportedEncodingException {
		ByteArrayOutputStream baosMarc = new ByteArrayOutputStream();
		MarcWriter marcWriter = new MarcStreamWriter(baosMarc, DEFAULT_ENCODING);
		marcWriter.write(record);
		marcWriter.close();
		return baosMarc.toString(DEFAULT_ENCODING);
	}

}
