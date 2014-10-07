package my.com.myriadeas.integral.eligibility;

import java.util.Map;

public interface WhatEligibilityLookup<X, Y> extends EligibilityLookup {

	public Eligibility<Y> lookup(X objectIdentity);

	public Map<String, String> buildCriteria(X objectIdentity);
}
