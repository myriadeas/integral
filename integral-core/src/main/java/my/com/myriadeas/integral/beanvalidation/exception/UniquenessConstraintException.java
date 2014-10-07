package my.com.myriadeas.integral.beanvalidation.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class UniquenessConstraintException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4621788915276350584L;

	private static final String MESSAGE_ID = "beanvalidation.uniqueness.error";

	public UniquenessConstraintException(String message, Throwable cause,
			Object... argument) {
		super(message, cause, MESSAGE_ID, argument);
	}

}
