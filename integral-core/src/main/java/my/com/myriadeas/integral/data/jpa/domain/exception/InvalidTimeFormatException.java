package my.com.myriadeas.integral.data.jpa.domain.exception;

public class InvalidTimeFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTimeFormatException(String msg){
		super(msg);
	}
	
	public InvalidTimeFormatException(String msg, Throwable e){
		super(msg, e);
	}
}
