package my.com.myriadeas.integral.cataloguing.exception;



public class DeleteFailureException extends IntegralDaoBusinessLogicException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966182063840219774L;

	public DeleteFailureException(String message){
		super(message);
	}
	
	public DeleteFailureException(String message, Throwable e){
		super(message, e);
	}
}
