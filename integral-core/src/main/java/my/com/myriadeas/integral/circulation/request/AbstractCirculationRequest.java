package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

public abstract class AbstractCirculationRequest {

	private String itemIdentifier;
	
	private String patronIdentifier;

	private Date transactionDate;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public Date getTransactionDate() {
        if(transactionDate == null) {
            transactionDate = new Date();
        }
        return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getPatronIdentifier() {
		return patronIdentifier;
	}

	public void setPatronIdentifier(String patronIdentifier) {
		this.patronIdentifier = patronIdentifier;
	}

}
