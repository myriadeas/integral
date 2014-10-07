package my.com.myriadeas.integral.core;

public class IntegralRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -7250835475466052172L;

	public IntegralRuntimeException(String msg) {
		super(msg);
	}

	public IntegralRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
