package my.com.myriadeas.integral.index.application;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorRevised;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("resourceDescriptorRevisedListener")
public class ResourceDescriptorRevisedListener implements EventListener {

	private IndexService indexService;

	private static final Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorRevisedListener.class);

	@Consume(uri = "vm://cataloguing.resourceDescriptorRevised")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={})", domainEvent);
		ResourceDescriptorRevised event = (ResourceDescriptorRevised) domainEvent;
		UnindexCommand indexCommand = new UnindexCommand(
				event.getResourceDescriptorId());
		this.indexService.unindex(indexCommand);
		logger.debug("Leaving listen()");
	}

	@Autowired
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

}
