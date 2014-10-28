package my.com.myriadeas.integral.assetmanager.application.service;

import java.util.Map;

import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.domain.event.AllItemsForResourceDescriptorDeleted;
import my.com.myriadeas.integral.assetmanager.domain.model.Item;
import my.com.myriadeas.integral.assetmanager.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.publisher.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assetManagerWriteService")
public class AssetManagerWriteServiceImpl implements AssetManagerWriteService {

	private static final Logger logger = LoggerFactory
			.getLogger(AssetManagerWriteServiceImpl.class);

	private ItemRepositoryImpl itemRepository;

	private Publisher publisher;

	public ItemRepositoryImpl getItemRepository() {
		return itemRepository;
	}

	@Autowired
	public void setItemRepository(ItemRepositoryImpl itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	@Transactional
	public void releaseItem(ReleaseItemCommand releaseItemCommand) {
		logger.debug("Entering releaseItem(releaseItemCommand={})",
				releaseItemCommand);

		Item item = itemRepository.findByItemIdentifier(releaseItemCommand
				.getItemIdentifier());
		Map<String, DomainEvent> events = item.release();
		itemRepository.save(item);

		publisher.publish(events);

		logger.debug("Leaving releaseItem().");
	}

	@Override
	@Transactional
	public void unreleaseItem(UnreleaseItemCommand unreleaseItemCommand) {
		logger.debug("Entering unreleaseItem(unreleaseItemCommand={})",
				unreleaseItemCommand);

		Item item = itemRepository.findByItemIdentifier(unreleaseItemCommand
				.getItemIdentifier());
		Map<String, DomainEvent> events = item.unrelease();
		itemRepository.save(item);

		publisher.publish(events);

		logger.debug("Leaving unreleaseItem().");
	}

	@Override
	@Transactional
	public void deleteItem(DeleteItemCommand deleteItemCommand) {
		logger.debug("Entering deleteItem(deleteItemCommand={})",
				deleteItemCommand);

		Item item = itemRepository.findByItemIdentifier(deleteItemCommand
				.getItemIdentifier());
		Map<String, DomainEvent> events = item.delete();
		itemRepository.save(item);

		checkEmptyItemInResourceDescriptor(item, events);
		publisher.publish(events);

		logger.debug("Leaving deleteItem().");
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

		logger.debug("Leaving checkEmptyItemInResourceDescriptor().");
	}

}
