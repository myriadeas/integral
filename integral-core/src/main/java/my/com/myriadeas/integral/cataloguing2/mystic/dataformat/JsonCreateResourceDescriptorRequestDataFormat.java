package my.com.myriadeas.integral.cataloguing2.mystic.dataformat;

import java.io.InputStream;
import java.io.OutputStream;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

public class JsonCreateResourceDescriptorRequestDataFormat extends
		AbstractResourceDescriptorRequestDataFormat implements DataFormat {

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		// not implemented
	}

	@Override
	public CreateResourceDescriptorRequest unmarshal(Exchange exchange,
			InputStream stream) throws Exception {
		CreateResourceDescriptorRequest createResourceDescriptorRequest = new CreateResourceDescriptorRequest(
				this.getRecordFromInputStream(stream));
		return createResourceDescriptorRequest;
	}

}
