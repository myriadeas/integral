package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;

public interface ItemCategoryData {
	ItemCategory OPEN_SHELF = new ItemCategory("OS", "Open Shelf");
	ItemCategory RED_SPOT = new ItemCategory("RS", "Red Spot");
}
