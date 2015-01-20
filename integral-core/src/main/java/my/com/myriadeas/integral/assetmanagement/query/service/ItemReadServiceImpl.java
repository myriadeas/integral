package my.com.myriadeas.integral.assetmanagement.query.service;

import java.util.LinkedList;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.application.service.ItemWriteServiceImpl;
import my.com.myriadeas.integral.assetmanagement.query.domain.ItemView;
import my.com.myriadeas.integral.assetmanagement.query.domain.ItemViewRepositoryImpl;
import my.com.myriadeas.integral.assetmanagement.query.domain.ResourceDescriptorSolrRepositoryImpl;
import my.com.myriadeas.integral.assetmanagement.query.domain.ResourceDescriptorToListItem;

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
	private ItemViewRepositoryImpl itemViewRepository;

	@Autowired
	private ResourceDescriptorSolrRepositoryImpl resourceDescriptorSolrRepository;

	@Transactional
	public List<ItemView> getItemListByTitle(String title) {
		logger.debug("Entering getItemListByTitle(title={})", title);
		List<ResourceDescriptorToListItem> resourceDescriptorToListItemList = resourceDescriptorSolrRepository
				.findByTitleContains(title);
		logger.debug("resourceDescriptorToListItemList.size={}", resourceDescriptorToListItemList.size());
		List<ItemView> allItemViewList = new LinkedList<ItemView>();

		if (resourceDescriptorToListItemList != null) {
			for (ResourceDescriptorToListItem rd : resourceDescriptorToListItemList) {
				List<ItemView> itemViewList = itemViewRepository
						.findByResourceDescriptorIdentifier(rd.getId());
				allItemViewList.addAll(itemViewList);
			}
			logger.debug("Leaving getItemListByTitle().");
		}

		return allItemViewList;
	}

}
