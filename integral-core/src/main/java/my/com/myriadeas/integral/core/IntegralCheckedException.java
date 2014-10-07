package my.com.myriadeas.integral.core;

public class IntegralCheckedException extends Exception {

	private static final long serialVersionUID = -7250835475466052172L;

	public IntegralCheckedException(String msg) {
		super(msg);
	}

	public IntegralCheckedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
