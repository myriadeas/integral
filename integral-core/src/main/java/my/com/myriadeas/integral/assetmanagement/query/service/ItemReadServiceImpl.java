package my.com.myriadeas.integral.assetmanagement.query.service;

import java.util.LinkedList;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.query.domain.ItemView;
import my.com.myriadeas.integral.assetmanagement.query.domain.ItemViewRepository;
import my.com.myriadeas.integral.assetmanagement.query.domain.ResourceDescriptorSolrRepository;
import my.com.myriadeas.integral.assetmanagement.query.domain.ResourceDescriptorToListItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemReadService")
public class ItemReadServiceImpl implements ItemReadService {

	@Autowired
	private ItemViewRepository itemViewRepository;

	@Autowired
	private ResourceDescriptorSolrRepository resourceDescriptorSolrRepository;

	public List<ItemView> getItemListByTitle(String title) {

		List<ResourceDescriptorToListItem> resourceDescriptorToListItemList = resourceDescriptorSolrRepository
				.findByTitleContains(title);
		List<ItemView> allItemViewList = new LinkedList<ItemView>();

		if (resourceDescriptorToListItemList != null) {
			for (ResourceDescriptorToListItem rd : resourceDescriptorToListItemList) {
				List<ItemView> itemViewList = itemViewRepository
						.findByResourceDescriptorId(rd.getId());
				allItemViewList.addAll(itemViewList);
			}
		}

		return allItemViewList;
	}

}
