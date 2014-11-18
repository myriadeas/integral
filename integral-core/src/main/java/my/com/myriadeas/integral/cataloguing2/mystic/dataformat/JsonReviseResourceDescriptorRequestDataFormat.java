package my.com.myriadeas.integral.cataloguing2.mystic.dataformat;

import java.io.InputStream;
import java.io.OutputStream;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.ReviseResourceDescriptorRequest;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

public class JsonReviseResourceDescriptorRequestDataFormat extends
		AbstractResourceDescriptorRequestDataFormat implements DataFormat {

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		// not implemented
	}

	@Override
	public ReviseResourceDescriptorRequest unmarshal(Exchange exchange,
			InputStream stream) throws Exception {

		Long id = exchange.getIn().getHeader("id", Long.class);
		ReviseResourceDescriptorRequest reviseResourceDescriptorRequest = new ReviseResourceDescriptorRequest(
				id, this.getRecordFromInputStream(stream));
		return reviseResourceDescriptorRequest;
	}
}
