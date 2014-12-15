package my.com.myriadeas.integral.assetmanager.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface Publisher {

	public void publish(String destination, Object eventCommand);

	public void publish(Map<String, DomainEvent> events);

}
