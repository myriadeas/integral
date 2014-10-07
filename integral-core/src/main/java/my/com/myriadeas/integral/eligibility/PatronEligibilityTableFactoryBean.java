package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.PatronEligibilityRepository;
import my.com.myriadeas.integral.data.populator.PatronEligibilityDatabasePopulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("patronEligibilityTableFactoryBean")
public class PatronEligibilityTableFactoryBean implements
		EligibilityTableFactory {

	private LookupTable instance;

	private PatronEligibilityRepository patronEligibilityRepository;

	@Autowired
	private PatronEligibilityDatabasePopulator eligibilityDatabasePopulator;

	@Autowired
	public void setPatronEligibilityRepository(
			PatronEligibilityRepository patronEligibilityRepository) {
		this.patronEligibilityRepository = patronEligibilityRepository;
	}

	@Override
	public LookupTable getLookupTable() {
		List<PatronEligibility> patronEligibilities = patronEligibilityRepository
				.findAll(new Sort(Sort.Direction.DESC, "weight"));
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		for (PatronEligibility patronEligibility : patronEligibilities) {
			ruleCommands.add(patronEligibility.getEligibilityRuleCommand());
		}
		instance = new LookupTable(ruleCommands);
		return instance;
	}

}
