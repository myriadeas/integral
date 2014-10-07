package my.com.myriadeas.integral.eligibility;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;

public interface PatronItemEligibilityLookup extends
		WhoWhatEligibilityLookup<Patron, Item, PatronItemEligibility> {
}
