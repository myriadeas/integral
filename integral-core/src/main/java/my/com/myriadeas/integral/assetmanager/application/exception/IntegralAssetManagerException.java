package my.com.myriadeas.integral.assetmanager.application.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public abstract class IntegralAssetManagerException extends IntegralBusinessLogicException {

	private static final long serialVersionUID = 1L;

	public IntegralAssetManagerException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralAssetManagerException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}

