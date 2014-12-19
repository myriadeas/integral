package my.com.myriadeas.integral.assetmanagement.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.assetmanagement.application.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemUnreleased;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class ReleasedItem implements ItemStatusOperations {

	@Override
	public ItemStatus release(Item item, Map<String, DomainEvent> events) {
	
		throw new UnsupportedStatusTransitionException("release", ItemStatus.RELEASED);
	}

	@Override
	public ItemStatus unrelease(Item item, Map<String, DomainEvent> events) {
	
		DomainEvent event = new ItemUnreleased(item.getItemIdentifier());
		events.put("itemUnreleased", event);
		return ItemStatus.UNRELEASED;
	}

	@Override
	public ItemStatus delete(Item item, Map<String, DomainEvent> events) {

		throw new UnsupportedStatusTransitionException("delete", ItemStatus.DELETED);
	}

}
