package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class RenewalDateTimeIsSameAsReturnedDateTimeException extends
		IntegralCirculationException {


	private static final long serialVersionUID = 1L;

	private static final String messageId = "item.renewalDateIsSameAsReturnedDate";

	public RenewalDateTimeIsSameAsReturnedDateTimeException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public RenewalDateTimeIsSameAsReturnedDateTimeException(String message,
			Throwable cause, Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
