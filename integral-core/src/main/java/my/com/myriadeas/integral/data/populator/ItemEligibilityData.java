package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.ItemEligibility;

public interface ItemEligibilityData {
	ItemEligibility OPEN_SHELVES_ITEM_ELIGIBILITY = new ItemEligibility("OS",
			"Open Shelf Item Eligibility");
	ItemEligibility RED_SPOT_ITEM_ELIGIBILITY = new ItemEligibility("RS",
			"Red Spot Item Eligibility");
	ItemEligibility GENERAL_ITEM_ELIGIBILITY = new ItemEligibility("GENERAL",
			"General Item Eligibility");
}
