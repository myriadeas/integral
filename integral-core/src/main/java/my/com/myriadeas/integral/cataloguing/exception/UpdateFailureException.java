package my.com.myriadeas.integral.cataloguing.exception;



public class UpdateFailureException extends IntegralDaoBusinessLogicException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966182063840219774L;

	public UpdateFailureException(String message){
		super(message);
	}
	
	public UpdateFailureException(String message, Throwable e){
		super(message, e);
	}
}
