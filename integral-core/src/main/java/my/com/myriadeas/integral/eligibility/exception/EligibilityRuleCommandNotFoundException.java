package my.com.myriadeas.integral.eligibility.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class EligibilityRuleCommandNotFoundException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2441802534573372811L;

	public EligibilityRuleCommandNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EligibilityRuleCommandNotFoundException(String message) {
		super(message);
	}

}
