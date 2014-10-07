package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class CirculationCheckInException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;

	public CirculationCheckInException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public CirculationCheckInException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

	@Override
	public String getMessageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
