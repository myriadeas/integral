package my.com.myriadeas.integral.circulation.request;

import java.util.Date;


public class ItemInformationRequest {
	//1720100115    090236AOPTAR|AB0000000003|AC|AZF587
	//17<transaction date><institution id>< item identifier ><terminal password>
	
	private Date transactionDate;
	private String institutionId;
	private String itemIdentifier;
	private String terminalPassword;
	
	public ItemInformationRequest(){
		
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getItemIdentifier() {
		return itemIdentifier;
	}
	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}
	public String getTerminalPassword() {
		return terminalPassword;
	}
	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}
	
	
}
