package my.com.myriadeas.integral.cataloguing.exception;



public class CreateFailureException extends IntegralDaoBusinessLogicException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966182063840219774L;

	public CreateFailureException(String message){
		super(message);
	}
	
	public CreateFailureException(String message, Throwable e){
		super(message, e);
	}
}
