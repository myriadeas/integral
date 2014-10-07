package my.com.myriadeas.integral.holiday;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class EmptyBranchException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyBranchException(String message) {
		super(message);
	}
	
	public EmptyBranchException(String message, Throwable cause) {
		super(message, cause);
	}

}
