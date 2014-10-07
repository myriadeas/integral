package my.com.myriadeas.integral.mystic.dataformat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.marc4j.marc.Record;

public class JsonMarcDataFormatTest {

	@Test
	public void testMarshal() {
	}

	@Test
	public void testUnmarshal() throws Exception {
		JsonMarcDataFormat dataFormat = new JsonMarcDataFormat();
		String marcJson = "{\"leader\":\"01471cjm a2200349 a 4500\""
				+ ",\"fields\":[{\"245\":{\"subfields\":[{\"a\":\"李白与杜甫\"}],\"ind1\":\"0\",\"ind2\":\"0\"}}]}";
		InputStream stream = new ByteArrayInputStream(marcJson.getBytes());
		Record record = dataFormat.unmarshal(null, stream);
		assertNotNull(record.getLeader());
		assertNotNull(record.getDataFields());
		assertEquals("李白与杜甫", record.getDataFields().get(0).getSubfield('a')
				.getData());
	}
}
