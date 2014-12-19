package my.com.myriadeas.integral.assetmanagement.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.assetmanagement.application.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReleased;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class NewItem implements ItemStatusOperations {

	@Override
	public ItemStatus release(Item item, Map<String, DomainEvent> events) {
		
		DomainEvent event = new ItemReleased(item.getItemIdentifier());
		events.put("itemReleased", event);
		return ItemStatus.RELEASED;
	}

	@Override
	public ItemStatus unrelease(Item item, Map<String, DomainEvent> events) {
		
		throw new UnsupportedStatusTransitionException("unrelease", ItemStatus.UNRELEASED);
	}

	@Override
	public ItemStatus delete(Item item, Map<String, DomainEvent> events) {

		throw new UnsupportedStatusTransitionException("delete", ItemStatus.DELETED);
	}

}
