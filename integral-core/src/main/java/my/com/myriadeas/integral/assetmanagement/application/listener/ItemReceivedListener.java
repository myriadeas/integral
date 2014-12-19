package my.com.myriadeas.integral.assetmanagement.application.listener;

import my.com.myriadeas.integral.assetmanagement.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.service.ItemWriteService;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReceived;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemReceivedListener")
public class ItemReceivedListener implements EventListener {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemReceivedListener.class);

	private ItemWriteService itemWriteService;

	@Override
	@Consume(uri = "vm:assetManagement.itemReceived?multipleConsumers=true")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={}) ", domainEvent);
		ItemReceived itemReceived = (ItemReceived) domainEvent;
		ReceiveItemCommand receiveItemCommand = new ReceiveItemCommand(
				itemReceived.getTitle(), itemReceived.getAuthor(),
				itemReceived.getIsbn(), itemReceived.getNumberOfCopy(),
				itemReceived.getForeignCost(), itemReceived.getLocalCost());
		itemWriteService.create(receiveItemCommand);
		logger.debug("Leaving listen() ");
	}

	@Autowired
	public void setItemWriteService(ItemWriteService itemWriteService) {
		this.itemWriteService = itemWriteService;
	}
}
