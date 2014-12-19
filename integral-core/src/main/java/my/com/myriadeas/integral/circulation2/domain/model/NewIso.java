package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.circulation2.CirculationEvents;
import my.com.myriadeas.integral.circulation2.domain.service.HoldingGroupService;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class NewIso implements HoldingStatusOperations {

	private static final Logger logger = LoggerFactory.getLogger(NewIso.class);

	private HoldingGroupService holdingGroupService;

	@Override
	public HoldingStatus release(Holding holding, ItemCategory itemCategory,
			Map<String, DomainEvent> events) {
		logger.debug("Entering release(holding={}, itemCategory={})", holding,
				itemCategory);
		HoldingGroup holdingGroup = holdingGroupService
				.findOrCreate(itemCategory);
		logger.debug("Holding Group={}", holdingGroup);
		holding.setHoldingGroup(holdingGroup);
		HoldingReleased event = new HoldingReleased(holding.getId(),
				holding.getItemIdentifier(), holdingGroup.getId());
		events.put(CirculationEvents.HOLDING_RELEASED, event);
		logger.debug("Leaving release()");
		return HoldingStatus.PENDING_AVAILABLE;
	}

	@Autowired
	public void setHoldingGroupService(HoldingGroupService holdingGroupService) {
		this.holdingGroupService = holdingGroupService;
	}

}
