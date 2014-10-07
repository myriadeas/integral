package my.com.myriadeas.integral.eligibility;

import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;

public interface PatronEligibilityLookup extends
		WhoEligibilityLookup<Patron, PatronEligibility> {
}
