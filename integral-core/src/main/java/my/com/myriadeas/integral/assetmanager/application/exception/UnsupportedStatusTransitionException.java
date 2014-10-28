package my.com.myriadeas.integral.assetmanager.application.exception;


public class UnsupportedStatusTransitionException extends IntegralAssetManagerException{
	
	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "assetmanager.unsupportedStatusTransitionException";

	public UnsupportedStatusTransitionException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public UnsupportedStatusTransitionException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
