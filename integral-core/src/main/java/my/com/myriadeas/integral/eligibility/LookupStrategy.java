package my.com.myriadeas.integral.eligibility;

import java.util.Map;

public interface LookupStrategy {

	public EligibilityRuleCommand lookup(Map<String, String> criterias);
	
}
