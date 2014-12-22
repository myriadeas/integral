package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReleased;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemReleasedListener")
public class ItemReleasedListener implements EventListener {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemReleasedListener.class);

	private HoldingService holdingService;

	@Override
	@Consume(uri = "vm:assetManagement.itemReleased?multipleConsumers=true")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={}) ", domainEvent);
		ItemReleased itemReleased = (ItemReleased) domainEvent;
		NewHoldingCommand command = new NewHoldingCommand(
				itemReleased.getItemIdentifier());
		holdingService.newHolding(command);
		logger.debug("Leaving listen() ");
	}

	@Autowired
	public void setHoldingService(HoldingService holdingService) {
		this.holdingService = holdingService;
	}
}
