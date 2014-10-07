package my.com.myriadeas.integral.holiday;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class EmptyHolidayException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyHolidayException(String message) {
		super(message);
	}
	
	public EmptyHolidayException(String message, Throwable cause) {
		super(message, cause);
	}

}
