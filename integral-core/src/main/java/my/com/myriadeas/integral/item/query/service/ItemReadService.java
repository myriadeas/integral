package my.com.myriadeas.integral.item.query.service;

import java.util.List;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.item.query.domain.ItemView;

public interface ItemReadService {
	
	public List<Item> getItemListByTitle(String title);

}
