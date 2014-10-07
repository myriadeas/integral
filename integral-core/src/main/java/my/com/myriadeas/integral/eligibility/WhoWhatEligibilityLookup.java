package my.com.myriadeas.integral.eligibility;

import java.util.Map;

import my.com.myriadeas.integral.eligibility.exception.EligibilityNotFoundException;

public interface WhoWhatEligibilityLookup<X, Y,Z> extends EligibilityLookup {

	public Eligibility<Z> lookup(X securityIdentity, Y objectIdentity) throws EligibilityNotFoundException;
	
	public Map<String, String> buildCriteria(X securityIdentity, Y objectIdentity);
}
