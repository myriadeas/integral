package my.com.myriadeas.integral.cataloguing2.domain.service;

import javax.ws.rs.core.Response;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "assetManagerService")
public class AssetManagerServiceImpl implements AssetManagerService {

	private static final Logger logger = LoggerFactory
			.getLogger(AssetManagerServiceImpl.class);

	private static final String ENDPOINT = "direct:assetManager.getHoldingListByResourceDescriptorId";

	@Autowired
	@Qualifier("cataloguingProducerTemplate")
	private ProducerTemplate template;

	public AssetManagerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public ProducerTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(ProducerTemplate template) {
		this.template = template;
	}

	@Override
	public int countByResourceDescriptor(String resourceDescriptorId) {
		Response response = template.requestBody(ENDPOINT,
				resourceDescriptorId, Response.class);
		return response.getLength();
	}

}
