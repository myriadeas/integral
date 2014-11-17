package my.com.myriadeas.integral.cataloguing2.mystic.dataformat;

import java.io.InputStream;
import java.io.OutputStream;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.UpdateResourceDescriptorRequest;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

public class JsonUpdateResourceDescriptorRequestDataFormat extends
		AbstractResourceDescriptorRequestDataFormat implements DataFormat {

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		// not implemented
	}

	@Override
	public UpdateResourceDescriptorRequest unmarshal(Exchange exchange,
			InputStream stream) throws Exception {

		String resourceDescriptorId = exchange.getIn().getHeader(
				"resourceDescriptorId", String.class);
		UpdateResourceDescriptorRequest updateResourceDescriptorRequest = new UpdateResourceDescriptorRequest(
				resourceDescriptorId, this.getRecordFromInputStream(stream));
		return updateResourceDescriptorRequest;
	}
}
