package my.com.myriadeas.integral.assetmanagement.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanagement.domain.event.AllItemsForResourceDescriptorDeleted;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReceived;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;
import my.com.myriadeas.integral.item.query.solr.ResourceDescriptorSolrRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemWriteService")
public class ItemWriteServiceImpl implements ItemWriteService {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemWriteServiceImpl.class);

	@Autowired
	private ItemRepositoryImpl itemRepository;

	@Autowired
	private ResourceDescriptorSolrRepositoryImpl resourceDescriptorSolrRepository;

	@Autowired
	@Qualifier("assetManagementPublisher")
	private Publisher publisher;

	@Override
	public void receiveItem(ReceiveItemCommand receiveItemCommand) {
		logger.debug("Entering receiveItem(receiveItemCommand={})",
				receiveItemCommand);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		DomainEvent event = new ItemReceived(receiveItemCommand.getTitle(),
				receiveItemCommand.getAuthor(), receiveItemCommand.getIsbn(),
				receiveItemCommand.getNumberOfCopy(),
				receiveItemCommand.getForeignCost(),
				receiveItemCommand.getLocalCost());
		events.put("itemReceived", event);
		publisher.publish(events);
		logger.debug("Leaving receiveItem().");
	}

	@Override
	@Transactional
	public Long createItem(CreateItemCommand createItemCommand) {
		logger.debug("Entering createItem(createItemCommand={})",
				createItemCommand);
		// catalog search
		Item item = new Item(
				createItemCommand.getResourceDescriptorIdentifier(),
				createItemCommand.getLocalCost(),
				createItemCommand.getForeignCost());
		logger.info("Item Identifier={}", item.getItemIdentifier());
		itemRepository.save(item);
		logger.info("Item Status={}", item.getItemStatus());
		logger.debug("Leaving createItem().");
		return item.getId();
	}

	@Transactional
	public void create(ReceiveItemCommand receiveItemCommand) {
		logger.debug("Entering create(receiveItemCommand={})",
				receiveItemCommand);
		Item item = null;
		int numberOfCopy = receiveItemCommand.getNumberOfCopy().intValue();
		List<ResourceDescriptorSolr> resourceDescriptorToListItemList = resourceDescriptorSolrRepository
				.findByQueryAnnotation(receiveItemCommand.getTitle(),
						receiveItemCommand.getAuthor(),
						receiveItemCommand.getIsbn());
		logger.debug("resourceDescriptorToListItemList.size={}",
				resourceDescriptorToListItemList.size());

		if (resourceDescriptorToListItemList.size() > 0) {
			for (int i = 0; i < numberOfCopy; i++) {
				item = new Item(
						resourceDescriptorToListItemList.get(0).getId(),
						receiveItemCommand.getLocalCost(),
						receiveItemCommand.getForeignCost());
				logger.info("Item Identifier={}", item.getItemIdentifier());
				itemRepository.save(item);
			}

		} else {
			//ToDo
		}

		logger.debug("Leaving create().");
	}

	@Override
	@Transactional
	public Long releaseItem(ReleaseItemCommand releaseItemCommand) {
		logger.debug("Entering releaseItem(releaseItemCommand={})",
				releaseItemCommand);

		Item item = itemRepository.findByItemIdentifier(releaseItemCommand
				.getItemIdentifier());
		logger.info("Item Identifier={}", item.getItemIdentifier());
		Map<String, DomainEvent> events = item.release();
		itemRepository.save(item);

		publisher.publish(events);
		logger.info("Item Status={}", item.getItemStatus());
		logger.debug("Leaving releaseItem().");
		return item.getId();
	}

	@Override
	@Transactional
	public Long unreleaseItem(UnreleaseItemCommand unreleaseItemCommand) {
		logger.debug("Entering unreleaseItem(unreleaseItemCommand={})",
				unreleaseItemCommand);

		Item item = itemRepository.findByItemIdentifier(unreleaseItemCommand
				.getItemIdentifier());
		logger.info("Item Identifier={}", item.getItemIdentifier());
		Map<String, DomainEvent> events = item.unrelease();
		itemRepository.save(item);

		publisher.publish(events);
		logger.info("Item Status={}", item.getItemStatus());
		logger.debug("Leaving unreleaseItem().");
		return item.getId();
	}

	@Override
	@Transactional
	public Long deleteItem(DeleteItemCommand deleteItemCommand) {
		logger.debug("Entering deleteItem(deleteItemCommand={})",
				deleteItemCommand);

		Item item = itemRepository.findByItemIdentifier(deleteItemCommand
				.getItemIdentifier());
		logger.info("Item Identifier={}", item.getItemIdentifier());
		Map<String, DomainEvent> events = item.delete();
		itemRepository.save(item);

		checkEmptyItemInResourceDescriptor(item, events);
		publisher.publish(events);
		logger.info("Item Status={}", item.getItemStatus());
		logger.debug("Leaving deleteItem().");

		return item.getId();
	}

	public void checkEmptyItemInResourceDescriptor(Item item,
			Map<String, DomainEvent> events) {
		logger.debug(
				"Entering checkEmptyItemInResourceDescriptor(item={}, events={})",
				new Object[] { item, events });

		if (itemRepository.findByResourceDescriptorIdentifier(
				item.getResourceDescriptorIdentifier()).isEmpty()) {
			DomainEvent event = new AllItemsForResourceDescriptorDeleted(
					item.getResourceDescriptorIdentifier());
			events.put("allItemsForResourceDescriptorDeleted", event);
		}
		logger.info("Item Status={}", item.getItemStatus());
		logger.debug("Leaving checkEmptyItemInResourceDescriptor().");
	}

}
