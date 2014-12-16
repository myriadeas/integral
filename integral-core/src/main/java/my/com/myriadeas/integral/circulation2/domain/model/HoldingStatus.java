package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public enum HoldingStatus implements HoldingStatusOperations {

	NEW(new NewIso()), PENDING_AVAILABLE(new PendingAvailable());

	private final HoldingStatusOperations operations;

	HoldingStatus(HoldingStatusOperations operations) {
		this.operations = operations;
	}

	HoldingStatusOperations getOperations() {
		return this.operations;
	}

	@Override
	public HoldingStatus release(Holding holding, ItemCategory itemCategory,
			Map<String, DomainEvent> events) {
		return this.operations.release(holding, itemCategory, events);
	}

}
