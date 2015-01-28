package my.com.myriadeas.integral.item.query.service;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.item.query.domain.ItemDisplay;
import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;
import my.com.myriadeas.integral.item.query.solr.ResourceDescriptorSolrRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemReadService")
public class ItemReadServiceImpl implements ItemReadService {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemReadServiceImpl.class);

	@Autowired
	private ItemRepositoryImpl itemRepository;

	@Autowired
	private ResourceDescriptorSolrRepositoryImpl resourceDescriptorSolrRepository;

	@Transactional
	public List<ItemDisplay> getItemListByTitleAuthorIsbn(String title,
			String author, String isbn) {
		logger.debug(
				"Entering getItemListByTitleAuthorIsbn(title={}, author={}, isbn={})",
				new Object[] { title, author, isbn });
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByAvailableInput(title, author, isbn);
		logger.debug("Leaving getItemListByTitleAuthorIsbn().");
		return getItemListByResourceDescriptorSolrList(resourceDescriptorSolrList);
	}

	@Transactional
	public List<ItemDisplay> getItemListByTitle(String title) {
		logger.debug("Entering getItemByTitle(title={})", title);
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByTitle(title);

		logger.debug("Leaving getItemByTitle().");
		return getItemListByResourceDescriptorSolrList(resourceDescriptorSolrList);
	}

	@Transactional
	public List<ItemDisplay> getItemListByAuthor(String author) {
		logger.debug("Entering getItemByAuthor(author={})", author);
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByAuthor(author);

		logger.debug("Leaving getItemByAuthor().");
		return getItemListByResourceDescriptorSolrList(resourceDescriptorSolrList);
	}

	@Transactional
	public List<ItemDisplay> getItemListByIsbn(String isbn) {
		logger.debug("Entering getItemByIsbn(isbn={})", isbn);
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByIsbn(isbn);

		logger.debug("Leaving getItemByAuthor().");
		return getItemListByResourceDescriptorSolrList(resourceDescriptorSolrList);
	}

	@Transactional
	private List<ItemDisplay> getItemListByResourceDescriptorSolrList(
			List<ResourceDescriptorSolr> resourceDescriptorSolrList) {
		logger.debug(
				"Entering getItemListByResourceDescriptorSolrList(resourceDescriptorSolrList={})",
				resourceDescriptorSolrList);
		logger.debug("resourceDescriptorSolrList.size={}",
				resourceDescriptorSolrList.size());

		List<ItemDisplay> allItemDisplayList = new ArrayList<ItemDisplay>();

		if (resourceDescriptorSolrList != null) {
			ItemDisplay itemDisplay = null;

			for (ResourceDescriptorSolr rd : resourceDescriptorSolrList) {
				List<ItemDisplay> itemDisplayList = new ArrayList<ItemDisplay>();
				logger.debug("resourceDescriptorSolrList.id={}", rd.getId());
				List<Item> itemList = itemRepository
						.findByResourceDescriptorIdentifier(rd.getId());

				for (Item item : itemList) {
					itemDisplay = new ItemDisplay(item.getItemIdentifier(),
							rd.getId(), rd.getTitle(), rd.getAuthor(),
							rd.getIsbn());
					itemDisplayList.add(itemDisplay);
				}
				allItemDisplayList.addAll(itemDisplayList);

				logger.debug("itemDisplayList.size={}", itemDisplayList.size());
				
			}

		}
		logger.debug("allItemDisplayList.size={}", allItemDisplayList.size());
		logger.debug(
				"Leaving getItemListByResourceDescriptorSolrList(allItemDisplayList={}).",
				allItemDisplayList);
		return allItemDisplayList;

	}

}
