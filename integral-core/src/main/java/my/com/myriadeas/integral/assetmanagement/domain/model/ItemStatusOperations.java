package my.com.myriadeas.integral.assetmanagement.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface ItemStatusOperations {

	ItemStatus release(Item item, Map<String, DomainEvent> events);
	ItemStatus unrelease(Item item, Map<String, DomainEvent> events);
	ItemStatus delete(Item item, Map<String, DomainEvent> events);
	
}
