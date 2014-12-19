package my.com.myriadeas.integral.assetmanagement.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public enum ItemStatus implements ItemStatusOperations{
	
	NEW(new NewItem()), 
	RELEASED(new ReleasedItem()),
	UNRELEASED(new UnReleasedItem()),
	DELETED(new DeletedItem());
		
	private final ItemStatusOperations operations;
	
	ItemStatus(ItemStatusOperations operations) {
		
		this.operations= operations;
	}
	
	ItemStatusOperations getOperations() {
		return this.operations;	
	}

	@Override
	public ItemStatus release(Item item, Map<String, DomainEvent> events) {

		return this.operations.release(item, events);
	}

	@Override
	public ItemStatus unrelease(Item item, Map<String, DomainEvent> events) {

		return this.operations.unrelease(item, events);
	}

	@Override
	public ItemStatus delete(Item item, Map<String, DomainEvent> events) {

		return this.operations.delete(item, events);
	}
	
}
