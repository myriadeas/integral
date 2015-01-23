package my.com.myriadeas.integral.item.query.service;

import java.util.LinkedList;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorToListItem;
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
	public List<Item> getItemListByTitle(String title) {
		logger.debug("Entering getItemListByTitle(title={})", title);
		List<ResourceDescriptorToListItem> resourceDescriptorToListItemList = resourceDescriptorSolrRepository
				.findByTitle(title);
		logger.debug("resourceDescriptorToListItemList.size={}",
				resourceDescriptorToListItemList.size());
		List<Item> allItemViewList = new LinkedList<Item>();

		if (resourceDescriptorToListItemList != null) {
			for (ResourceDescriptorToListItem rd : resourceDescriptorToListItemList) {
				logger.debug("resourceDescriptorToListItemList.id={}",
						rd.getId());
				List<Item> itemList = itemRepository
						.findByResourceDescriptorIdentifier(rd.getId());
				logger.debug("itemList.size={}",
						itemList.size());
				allItemViewList.addAll(itemList);
			}
			logger.debug("allItemViewList.size={}",
					allItemViewList.size());
			logger.debug("Leaving getItemListByTitle().");
		}

		return allItemViewList;
	}

}
