package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

public class CheckOutRequest extends AbstractCirculationRequest {
	//11YN20100115    123322                  AOPTAR|AA2008558337|AB0000628042|AC|AD20085583|BON|BIN|
	//11<SC renewal policy><no block><transaction date><nb due date><institution id><patron identifier><item identifier><terminal password><patron password><item properties><fee acknowledged><cancel>
	
	private boolean scRenewalPolicy;
	private boolean noBlock;
	private Date nbDueDate;
	private String institutionId;
	private String patronIdentifier;
	private String itemIdentifier;
	private String terminalPassword;
	private String itemProperties;
	private String patronPassword;
	private boolean feeAcknowledged;
	private boolean cancel;
	
	public CheckOutRequest() {
		
	}
	
	public CheckOutRequest(String patronIdentifier, String itemIdentifier) {
		super();
		this.patronIdentifier = patronIdentifier;
		this.itemIdentifier = itemIdentifier;
	}
	public boolean isScRenewalPolicy() {
		return scRenewalPolicy;
	}
	public void setScRenewalPolicy(boolean scRenewalPolicy) {
		this.scRenewalPolicy = scRenewalPolicy;
	}
	public boolean isNoBlock() {
		return noBlock;
	}
	public void setNoBlock(boolean noBlock) {
		this.noBlock = noBlock;
	}
	public Date getNbDueDate() {
		return nbDueDate;
	}
	public void setNbDueDate(Date nbDueDate) {
		this.nbDueDate = nbDueDate;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
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
	public String getTerminalPassword() {
		return terminalPassword;
	}
	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}
	public String getItemProperties() {
		return itemProperties;
	}
	public void setItemProperties(String itemProperties) {
		this.itemProperties = itemProperties;
	}
	public String getPatronPassword() {
		return patronPassword;
	}
	public void setPatronPassword(String patronPassword) {
		this.patronPassword = patronPassword;
	}
	public boolean isFeeAcknowledged() {
		return feeAcknowledged;
	}
	public void setFeeAcknowledged(boolean feeAcknowledged) {
		this.feeAcknowledged = feeAcknowledged;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	
	
	
}
