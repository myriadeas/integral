package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface HoldingStatusOperations {

	public HoldingStatus release(Holding holding, ItemCategory itemCategory,
			Map<String, DomainEvent> events);
}
