package my.com.myriadeas.integral.mystic.dataformat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.marc4j.MarcJsonReader;
import org.marc4j.MarcJsonWriter;
import org.marc4j.marc.Record;

public class JsonMarcDataFormat implements DataFormat {

	private String encoding;

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		Record record = exchange.getIn().getBody(Record.class);
		MarcJsonWriter writer = new MarcJsonWriter(stream);
		writer.write(record);
	}

	@Override
	public Record unmarshal(Exchange exchange, InputStream stream)
			throws Exception {
		Record record = null;
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
