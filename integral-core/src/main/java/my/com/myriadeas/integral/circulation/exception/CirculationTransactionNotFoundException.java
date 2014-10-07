package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class CirculationTransactionNotFoundException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "circulationTransaction.notFound";

	public CirculationTransactionNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public CirculationTransactionNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
