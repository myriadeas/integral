package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.request.RecallRequest;

public interface RecallPolicyValidator {
 
	public void validate(RecallRequest recallRequest);
}
