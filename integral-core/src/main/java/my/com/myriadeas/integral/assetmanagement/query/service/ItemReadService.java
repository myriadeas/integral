package my.com.myriadeas.integral.assetmanagement.query.service;

import java.util.List;

import my.com.myriadeas.integral.assetmanagement.query.domain.ItemView;

public interface ItemReadService {
	
	public List<ItemView> getItemListByTitle(String title);

}
