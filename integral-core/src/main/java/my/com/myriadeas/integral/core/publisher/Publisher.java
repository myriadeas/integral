package my.com.myriadeas.integral.core.publisher;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;


public interface Publisher {

	public void publish(String destination, Object domainEvent);
	
	public void publish(Map<String, DomainEvent> events);
}
