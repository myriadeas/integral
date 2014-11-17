package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.circulation2.CirculationException;

public class DuplicatedNewHoldingException extends CirculationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String messageId = "circulation.duplicatedNewHolding";

	public DuplicatedNewHoldingException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
