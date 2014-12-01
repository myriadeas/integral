package my.com.myriadeas.integral.identityaccess;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

import org.springframework.validation.Errors;

public class IdentityAccessException extends IntegralBusinessLogicException {

	public IdentityAccessException(String message, String messageId,
			Errors result) {
		super(message, messageId, result);
	}

	public IdentityAccessException(String message, String messageId,
			Object arguments[]) {
		super(message, messageId, arguments);
	}

	public IdentityAccessException(String message, Throwable cause,
			String msgId, Object arguments[]) {
		super(message, cause, msgId, arguments);
	}

	public IdentityAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdentityAccessException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}