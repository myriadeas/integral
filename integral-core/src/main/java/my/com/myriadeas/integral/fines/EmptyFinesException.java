package my.com.myriadeas.integral.fines;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class EmptyFinesException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyFinesException(String message) {
		super(message);
	}
	
	public EmptyFinesException(String message, Throwable cause) {
		super(message, cause);
	}

}
