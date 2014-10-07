package my.com.myriadeas.integral.data.populator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "patronItemEligibilityDatabasePopulator")
public class PatronItemEligibilityDatabasePopulator extends AbstractDatabasePopulator
		implements PatronItemEligibilityDatabasePopulatorIntf {

	@Autowired
	private PatronItemEligibilityRepositoryImpl patronItemEligibilityRepository;

	public void init() {
		List<PatronItemEligibility> patronItemEligibilities = new ArrayList<PatronItemEligibility>();
		patronItemEligibilities.add(GENERAL_PATR_ITEM_ELIGIBILITY);
		patronItemEligibilities.add(OPEN_SHELVES_LECTURER_ITEM_ELIGIBILITY);
		patronItemEligibilities.add(RED_SPOT_ITEM_ELIGIBILITY);

		GENERAL_PATR_ITEM_ELIGIBILITY.setLoanPeriod(5);
		GENERAL_PATR_ITEM_ELIGIBILITY.setAllowOverdue(true);
		GENERAL_PATR_ITEM_ELIGIBILITY.setMaxRenewAllowed(5);
		GENERAL_PATR_ITEM_ELIGIBILITY.setMaxFines(new BigDecimal("10.00"));
		GENERAL_PATR_ITEM_ELIGIBILITY.setCriteria("-pc ANY -ic ANY");
		GENERAL_PATR_ITEM_ELIGIBILITY.setWeight(100);
		GENERAL_PATR_ITEM_ELIGIBILITY.setMaxLoanAllowed(7);
		OPEN_SHELVES_LECTURER_ITEM_ELIGIBILITY.setMaxFines(new BigDecimal(
				"20.00"));
		OPEN_SHELVES_LECTURER_ITEM_ELIGIBILITY.setCriteria("-pc lecturer -ic OS");
		OPEN_SHELVES_LECTURER_ITEM_ELIGIBILITY.setWeight(900);
		RED_SPOT_ITEM_ELIGIBILITY.setMaxFines(new BigDecimal("30.00"));
		RED_SPOT_ITEM_ELIGIBILITY.setCriteria("-pc ANY -ic RS");
		RED_SPOT_ITEM_ELIGIBILITY.setWeight(800);
		RED_SPOT_ITEM_ELIGIBILITY.setMaxLoanAllowed(5);
		RED_SPOT_ITEM_ELIGIBILITY.setLoanPeriod(5);
		RED_SPOT_ITEM_ELIGIBILITY.setAllowOverdue(true);
		RED_SPOT_ITEM_ELIGIBILITY.setMaxRenewAllowed(2);
		setupFinesForOverdueItemWithAFlatRate(GENERAL_PATR_ITEM_ELIGIBILITY);
		patronItemEligibilityRepository.save(patronItemEligibilities);
	}

	private void setupFinesForOverdueItemWithAFlatRate(
			PatronItemEligibility patronItemEligibility) {
		if (!patronItemEligibility.getFines().contains(FLAT_RATE_ONE_DOLLAR))
			patronItemEligibility.getFines().add(FLAT_RATE_ONE_DOLLAR);
	}

}
