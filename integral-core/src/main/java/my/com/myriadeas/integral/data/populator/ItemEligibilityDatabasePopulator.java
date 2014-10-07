package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemEligibilityRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemEligibilityDatabasePopulator")
public class ItemEligibilityDatabasePopulator extends AbstractDatabasePopulator implements
		ItemEligibilityDatabasePopulatorIntf {

	@Autowired
	private ItemEligibilityRepositoryImpl itemEligibilityRepository;

	public void init() {
		List<ItemEligibility> itemEligibilities = new ArrayList<ItemEligibility>();
		itemEligibilities.add(OPEN_SHELVES_ITEM_ELIGIBILITY);
		itemEligibilities.add(RED_SPOT_ITEM_ELIGIBILITY);
		itemEligibilities.add(GENERAL_ITEM_ELIGIBILITY);
		OPEN_SHELVES_ITEM_ELIGIBILITY.setCriteria("-ic OS");
		OPEN_SHELVES_ITEM_ELIGIBILITY.setWeight(1000);
		RED_SPOT_ITEM_ELIGIBILITY.setCriteria("-ic RS");
		RED_SPOT_ITEM_ELIGIBILITY.setWeight(900);
		GENERAL_ITEM_ELIGIBILITY.setCriteria("-ic ANY");
		GENERAL_ITEM_ELIGIBILITY.setWeight(100);
		itemEligibilityRepository.save(itemEligibilities);
	}
}
