package my.com.myriadeas.integral.identityaccess.domain.model;

import my.com.myriadeas.integral.identityaccess.IdentityAccessException;

import org.springframework.validation.Errors;

public class RegisterUserException extends IdentityAccessException {

	private static final long serialVersionUID = 1L;
	private static final String messageId = "identityAccess.registerUserError";

	public RegisterUserException(String message, Throwable cause,
			Object arguments[]) {
		super(message, cause, messageId, arguments);
	}

	public RegisterUserException(String message, Errors result) {
		super(message, messageId, result);
	}

}