package my.com.myriadeas.integral.receipting.exception;

import my.com.myriadeas.integral.core.IntegralReceiptingException;

public class UndefineReceiptingTransactionCodeException extends IntegralReceiptingException {

	private static final long serialVersionUID = 1L;

	private static final String messageId = "receipting.undefineReceiptingTransactionCode";

	public UndefineReceiptingTransactionCodeException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public UndefineReceiptingTransactionCodeException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
