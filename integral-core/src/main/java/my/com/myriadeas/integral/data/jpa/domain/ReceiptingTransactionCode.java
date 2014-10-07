package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLDOCS database table.
 * 
 */
@Entity
public class ReceiptingTransactionCode extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	public ReceiptingTransactionCode() {
	}

	public ReceiptingTransactionCode(String code, String description) {
		super(code, description);
	}

}