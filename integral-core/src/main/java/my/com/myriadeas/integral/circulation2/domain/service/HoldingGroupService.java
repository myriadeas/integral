package my.com.myriadeas.integral.circulation2.domain.service;

import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;

public interface HoldingGroupService {
	
	public HoldingGroup findOrCreate(ItemCategory itemCategory);

}
