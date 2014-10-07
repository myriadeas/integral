package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

public class RenewRequest extends AbstractCirculationRequest {
	// 29YY20040914 12121220040608
	// 121212AOUUM|AASYSADMIN|AB000020000|ACTERMINAL1
	// 29<third party allowed><no block><transaction date><nb due
	// date><institution id><patron identifier><patron password><item
	// identifier><title identifier><terminal password><item properties><fee
	// acknowledged>

	private boolean thirdPartyAllow;
	private boolean noBlock;
	private Date noBlockDueDate;
	private String institutionId;
	private String patronIdentifier;
	private String patronPassword;
	private String itemIdentifier;
	private String titleIdentifier;
	private String terminalPassword;
	private String itemProperties;
	private boolean feeAcknowledged;

	public RenewRequest() {

	}

	public RenewRequest(String patronIdentifier, String itemIdentifier) {
		this.patronIdentifier = patronIdentifier;
		this.itemIdentifier = itemIdentifier;
	}

	public boolean isThirdPartyAllow() {
		return thirdPartyAllow;
	}

	public void setThirdPartyAllow(boolean thirdPartyAllow) {
		this.thirdPartyAllow = thirdPartyAllow;
	}

	public boolean isNoBlock() {
		return noBlock;
	}

	public void setNoBlock(boolean noBlock) {
		this.noBlock = noBlock;
	}

	public Date getNoBlockDueDate() {
		return noBlockDueDate;
	}

	public void setNoBlockDueDate(Date noBlockDueDate) {
		this.noBlockDueDate = noBlockDueDate;
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

	public String getPatronPassword() {
		return patronPassword;
	}

	public void setPatronPassword(String patronPassword) {
		this.patronPassword = patronPassword;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getTitleIdentifier() {
		return titleIdentifier;
	}

	public void setTitleIdentifier(String titleIdentifier) {
		this.titleIdentifier = titleIdentifier;
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

	public boolean isFeeAcknowledged() {
		return feeAcknowledged;
	}

	public void setFeeAcknowledged(boolean feeAcknowledged) {
		this.feeAcknowledged = feeAcknowledged;
	}

}
