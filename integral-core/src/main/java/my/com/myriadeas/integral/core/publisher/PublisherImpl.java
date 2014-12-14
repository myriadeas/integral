package my.com.myriadeas.integral.core.publisher;

import java.util.Map;
import java.util.Map.Entry;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class PublisherImpl implements Publisher {

	private static final Logger logger = LoggerFactory
			.getLogger(PublisherImpl.class);

	private ProducerTemplate producerTemplate;

	private final String moduleName;

	private static final String DESTINATION_PREFIX = "vm";

	public PublisherImpl(ProducerTemplate producerTemplate, String moduleName) {
		Assert.notNull(producerTemplate);
		Assert.notNull(moduleName);
		this.producerTemplate = producerTemplate;
		this.moduleName = moduleName;
	}

	@Override
	public void publish(String destination, Object domainEvent) {
		logger.debug("Entering publish(destination={}, eventCommand={})",
				destination, domainEvent);
		String endpoint = this.getRouteEndpoint(destination);
		logger.debug("Endpoint={}", endpoint);
		producerTemplate.sendBody(endpoint, domainEvent);
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

	private String getRouteEndpoint(String destination) {
		return DESTINATION_PREFIX + "://" + moduleName + "." + destination;
	}
}
