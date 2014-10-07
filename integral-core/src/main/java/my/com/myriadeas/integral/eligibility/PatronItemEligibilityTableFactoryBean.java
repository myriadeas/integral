package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.PatronItemEligibilityRepository;
import my.com.myriadeas.integral.data.populator.PatronItemEligibilityDatabasePopulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("patronItemEligibilityTableFactoryBean")
public class PatronItemEligibilityTableFactoryBean implements
		EligibilityTableFactory {

	private LookupTable instance;

	private PatronItemEligibilityRepository patronItemEligibilityRepository;
	
	@Autowired
	private PatronItemEligibilityDatabasePopulator eligibilityDatabasePopulator;

	@Autowired
	public void setPatronItemEligibilityRepository(
			PatronItemEligibilityRepository patronItemEligibilityRepository) {
		this.patronItemEligibilityRepository = patronItemEligibilityRepository;
	}

	@Override
	public LookupTable getLookupTable() {
		List<PatronItemEligibility> patronItemEligibilities = patronItemEligibilityRepository
				.findAll(new Sort(Sort.Direction.DESC, "weight"));
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		for (PatronItemEligibility patronItemEligibility : patronItemEligibilities) {
			ruleCommands.add(patronItemEligibility.getEligibilityRuleCommand());
		}
		instance = new LookupTable(ruleCommands);
		return instance;
	}

}
