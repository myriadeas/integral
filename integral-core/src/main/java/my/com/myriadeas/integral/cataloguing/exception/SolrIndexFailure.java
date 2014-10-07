package my.com.myriadeas.integral.cataloguing.exception;


public class SolrIndexFailure extends IndexFailureException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -234202487366695474L;

	public SolrIndexFailure(String msg) {
		super(msg);
	}

	public SolrIndexFailure(String msg, Throwable cause) {
		super(msg, cause);
	}

}
