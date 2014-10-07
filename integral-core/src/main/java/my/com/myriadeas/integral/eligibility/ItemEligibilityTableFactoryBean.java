package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.ItemEligibilityRepository;
import my.com.myriadeas.integral.data.populator.ItemEligibilityDatabasePopulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("itemEligibilityTableFactoryBean")
public class ItemEligibilityTableFactoryBean implements EligibilityTableFactory {

	private LookupTable instance;

	private ItemEligibilityRepository itemEligibilityRepository;

	@Autowired
	private ItemEligibilityDatabasePopulator eligibilityDatabasePopulator;

	@Autowired
	public void setItemEligibilityRepository(
			ItemEligibilityRepository itemEligibilityRepository) {
		this.itemEligibilityRepository = itemEligibilityRepository;
	}

	public LookupTable getLookupTable() {
		List<ItemEligibility> itemEligibilities = itemEligibilityRepository
				.findAll(new Sort(Sort.Direction.DESC, "weight"));
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		for (ItemEligibility itemEligibility : itemEligibilities) {
			ruleCommands.add(itemEligibility.getEligibilityRuleCommand());
		}
		instance = new LookupTable(ruleCommands);
		return instance;
	}

}
