package my.com.myriadeas.integral.cataloguing2.domain.service;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "cataloguingPublisher")
public class PublisherImpl implements Publisher {

	private static final Logger logger = LoggerFactory
			.getLogger(PublisherImpl.class);

	@Autowired
	@Qualifier("integralProducerTemplate")
	private ProducerTemplate producerTemplate;

	private static final String DESTINATION_PREFIX = "vm:";

	@Override
	public void publish(String destination, Object eventCommand) {
		logger.debug("Entering publish(destination={}, eventCommand={})",
				destination, eventCommand);
		producerTemplate.sendBody(DESTINATION_PREFIX + destination,
				eventCommand);
		logger.debug("Leaving publish()");
	}

	@Override
	public void publish(Map<String, DomainEvent> events) {
		for (Map.Entry<String, DomainEvent> entry : events.entrySet()) {
			publish(entry.getKey(), entry.getValue());
		}

	}

}
