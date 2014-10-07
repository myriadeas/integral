package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class UnknownLoanTypeException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;

	private static final String messageId = "circulation.unknownLoanType";

	public UnknownLoanTypeException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public UnknownLoanTypeException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
