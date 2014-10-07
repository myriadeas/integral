package my.com.myriadeas.integral.circulation.request;

import java.util.Date;


public class RecallRequest {
	private String itemIdentifier;
	
	private String patronIdentifier;
	
	private String recallBranch;
	
	private Date transactionDate;

	public String getPatronIdentifier() {
		return patronIdentifier;
	}

	public void setPatronIdentifier(String patronIdentifier) {
		this.patronIdentifier = patronIdentifier;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getRecallBranch() {
		return recallBranch;
	}

	public void setRecallBranch(String recallBranch) {
		this.recallBranch = recallBranch;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	

}
