package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class NewHoldingCreated implements DomainEvent {

	private String itemIdentifier;

	public NewHoldingCreated(Long holdingId, String itemIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

}
