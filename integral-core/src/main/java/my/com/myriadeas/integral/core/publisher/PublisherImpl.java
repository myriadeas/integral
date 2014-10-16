package my.com.myriadeas.integral.core.publisher;

import java.util.Map;
import java.util.Map.Entry;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.publisher.Publisher;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "publisher")
public class PublisherImpl implements Publisher {

	private static final Logger logger = LoggerFactory
			.getLogger(PublisherImpl.class);

	@Autowired
	@Qualifier("integralProducerTemplate")
	private ProducerTemplate producerTemplate;

	private static final String DESTINATION_PREFIX = "vm:";

	@Override
	public void publish(String destination, Object domainEvent) {
		logger.debug("Entering publish(destination={}, eventCommand={})",
				destination, domainEvent);
		producerTemplate
				.sendBody(DESTINATION_PREFIX + destination, domainEvent);
		logger.debug("Leaving publish()");
	}

	@Override
	public void publish(Map<String, DomainEvent> events) {
		logger.debug("Entering publish(events={})", events);
		for (Entry<String, DomainEvent> eventEntry : events.entrySet()) {
			publish(eventEntry.getKey(), eventEntry.getValue());
		}
		logger.debug("Leaving publish()");

	}

}
