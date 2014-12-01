package my.com.myriadeas.integral.identityaccess.domain.model;

import my.com.myriadeas.integral.identityaccess.IdentityAccessException;

public class DuplicatedUsernameException extends IdentityAccessException {
	private static final long serialVersionUID = 1L;
	private static final String messageId = "identityAccess.user.register.username.duplicated";

	public DuplicatedUsernameException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}