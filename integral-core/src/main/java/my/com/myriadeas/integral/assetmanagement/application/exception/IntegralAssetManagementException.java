package my.com.myriadeas.integral.assetmanagement.application.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public abstract class IntegralAssetManagementException extends IntegralBusinessLogicException {

	private static final long serialVersionUID = 1L;

	public IntegralAssetManagementException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralAssetManagementException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}

