package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class PendingAvailable implements HoldingStatusOperations {

	@Override
	public HoldingStatus release(Holding holding, ItemCategory itemCategory,
			Map<String, DomainEvent> events) {
		// TODO Auto-generated method stub
		return null;
	}

}
