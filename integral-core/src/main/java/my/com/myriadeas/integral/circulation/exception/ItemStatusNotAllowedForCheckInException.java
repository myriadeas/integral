package my.com.myriadeas.integral.circulation.exception;

public class ItemStatusNotAllowedForCheckInException extends CirculationCheckInException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String messageId = "circulation.itemStatusNotAllowedForCheckIn";

	public ItemStatusNotAllowedForCheckInException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public ItemStatusNotAllowedForCheckInException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
