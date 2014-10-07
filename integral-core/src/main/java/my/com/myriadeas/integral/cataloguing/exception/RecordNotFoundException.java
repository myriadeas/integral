package my.com.myriadeas.integral.cataloguing.exception;



public class RecordNotFoundException extends IntegralDaoBusinessLogicException {
	
	private static final long serialVersionUID = -2966182063840219774L;

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}