package my.com.myriadeas.integral.beanvalidation.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class ConstrainedPropertiesNotFoundException extends
		IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8367115300342877447L;

	private static final String MESSAGE_ID = "beanvalidation.constrainedproperties.notfound";

	public ConstrainedPropertiesNotFoundException(String message,
			Throwable cause, Object... arguments) {
		super(message, cause, MESSAGE_ID, arguments);
	}

}
