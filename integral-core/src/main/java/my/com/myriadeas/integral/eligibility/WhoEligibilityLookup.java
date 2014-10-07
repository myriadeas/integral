package my.com.myriadeas.integral.eligibility;

import java.util.Map;



public interface WhoEligibilityLookup<X, Y> extends EligibilityLookup {

	public Eligibility<Y> lookup(X identity);
	
	public Map<String, String> buildCriteria(X identity);
}
