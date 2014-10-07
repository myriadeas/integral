package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

public class ReserveRequest extends AbstractCirculationRequest {
	// 15
	// 15<hold mode><transaction date><expiration date><pickup location><hold
	// type><institution id><patron identifier><patron password><item
	// identifier><title identifier><terminal password><fee acknowledged>

	private String holdMode;
	private Date expirationDate;
	private String pickupLocation;
	private String holdType;
	private String institutionId;
	private String patronIdentifier;
	private String patronPassword;
	private String itemIdentifier;
	private String titleIdentifier;
	private String terminalPassword;
	private boolean feeAcknowledged;

	public ReserveRequest() {

	}

	public String getHoldMode() {
		return holdMode;
	}

	public void setHoldMode(String holdMode) {
		this.holdMode = holdMode;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getHoldType() {
		return holdType;
	}

	public void setHoldType(String holdType) {
		this.holdType = holdType;
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

	public boolean isFeeAcknowledged() {
		return feeAcknowledged;
	}

	public void setFeeAcknowledged(boolean feeAcknowledged) {
		this.feeAcknowledged = feeAcknowledged;
	}

}
