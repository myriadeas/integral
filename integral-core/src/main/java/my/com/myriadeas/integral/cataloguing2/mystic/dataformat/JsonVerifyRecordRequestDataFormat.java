package my.com.myriadeas.integral.cataloguing2.mystic.dataformat;

import java.io.InputStream;
import java.io.OutputStream;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.VerifyRecordRequest;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

public class JsonVerifyRecordRequestDataFormat extends
		AbstractResourceDescriptorRequestDataFormat implements DataFormat {

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		// not implemented
	}

	@Override
	public VerifyRecordRequest unmarshal(Exchange exchange, InputStream stream)
			throws Exception {
		VerifyRecordRequest verifyRecordRequest = new VerifyRecordRequest(
				this.getRecordFromInputStream(stream));
		return verifyRecordRequest;
	}

}
