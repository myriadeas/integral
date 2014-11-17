package my.com.myriadeas.integral.cataloguing2.mystic.dataformat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.marc4j.MarcJsonReader;
import org.marc4j.marc.Record;

public abstract class AbstractResourceDescriptorRequestDataFormat {

	private String encoding;

	private Record record;

	public Record getRecordFromInputStream(InputStream stream) {
		record = null;
		MarcJsonReader reader = new MarcJsonReader(new InputStreamReader(
				stream, getEncoding()));
		while (reader.hasNext()) {
			record = reader.next();
		}
		return record;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Charset getEncoding() {
		if (this.encoding == null) {
			return StandardCharsets.UTF_8;
		} else {
			return Charset.forName(this.encoding);
		}

	}
}
