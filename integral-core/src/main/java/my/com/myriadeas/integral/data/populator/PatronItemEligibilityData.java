package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;

public interface PatronItemEligibilityData extends FinesData {
	PatronItemEligibility GENERAL_PATR_ITEM_ELIGIBILITY = new PatronItemEligibility(
			"GENERAL", "General Eligibility", LoanUnit.DAILY, 5);
	PatronItemEligibility OPEN_SHELVES_LECTURER_ITEM_ELIGIBILITY = new PatronItemEligibility(
			"OS_LECTURER", "Lecturer's OS Item Eligibility", LoanUnit.DAILY, 5);
	PatronItemEligibility RED_SPOT_ITEM_ELIGIBILITY = new PatronItemEligibility(
			"RS", "Red Spot Item Eligibility", LoanUnit.DAILY, 5);
}
