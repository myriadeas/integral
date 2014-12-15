package my.com.myriadeas.integral.index.application;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorFinalized;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("resourceDescriptorFinalizedListener")
public class ResourceDescriptorFinalizedListener implements EventListener {

	private IndexService indexService;

	private static final Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorFinalizedListener.class);

	@Consume(uri = "vm:cataloguing.resourceDescriptorFinalized?multipleConsumers=true")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={})", domainEvent);
		ResourceDescriptorFinalized event = (ResourceDescriptorFinalized) domainEvent;
		IndexCommand indexCommand = new IndexCommand(event.getMarc(),
				event.getResourceDescriptorId());
		
		this.indexService.index(indexCommand);
		logger.debug("Leaving listen()");
	}

	@Autowired
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

}
