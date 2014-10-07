package my.com.myriadeas.integral.cataloguing.exception;


public class IntegralSolrIndexerTransformationException extends SolrIndexFailure {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4919645255756102489L;

	public IntegralSolrIndexerTransformationException(String msg) {
		super(msg);
	}

	public IntegralSolrIndexerTransformationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
