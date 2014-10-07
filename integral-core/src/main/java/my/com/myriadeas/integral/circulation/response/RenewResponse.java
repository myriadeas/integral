package my.com.myriadeas.integral.circulation.response;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import com.ceridwen.circulation.SIP.types.enumerations.FeeType;


public class RenewResponse {
	//300NNN20100115    112442AOPTAR|AA790525086249|AB0000841414|AJColloquial Japanese : the complete course for beginners / Hugh Clarke and Motoko Hamamura.|AH|BHMYR|BV0|AFThis item has been reserved for System Administrator, SYSADMIN|
	//30<ok><renewal ok><magnetic media><desensitize><transaction date><institution id><patron identifier><item identifier><title identifier><due date><fee type><security inhibit><currency type><fee amount><media type><item properties><transaction id><screen message><print line>
	
	private boolean ok;
	private boolean renewalOk;
	private boolean magneticMedia;
	private boolean desensitize;
	private Date transactionDate;
	private String institutionId;
	private String patronIdentifier;
	private String itemIdentifier;
	private String titleIdentifier;
	private Date dueDate;
	private FeeType feeType;
	private boolean securityInhibit;
	private String currencyType;
	private String feeAmount;
	private String mediaType;
	private String itemProperties;
	private String transactionId;
	private String screenMessage;
	private String printLine;

	private Item item;
	private CirculationTransaction circulationDetail;
	private CirculationTransaction parentCirculationDetail;
	private AbstractUser user;
	private Date renewDateTime;
	private FinesTransaction finesDetail;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public boolean isRenewalOk() {
		return renewalOk;
	}
	public void setRenewalOk(boolean renewalOk) {
		this.renewalOk = renewalOk;
	}
	public boolean isMagneticMedia() {
		return magneticMedia;
	}
	public void setMagneticMedia(boolean magneticMedia) {
		this.magneticMedia = magneticMedia;
	}
	public boolean isDesensitize() {
		return desensitize;
	}
	public void setDesensitize(boolean desensitize) {
		this.desensitize = desensitize;
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
	public String getTitleIdentifier() {
		return titleIdentifier;
	}
	public void setTitleIdentifier(String titleIdentifier) {
		this.titleIdentifier = titleIdentifier;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public FeeType getFeeType() {
		return feeType;
	}
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	public boolean isSecurityInhibit() {
		return securityInhibit;
	}
	public void setSecurityInhibit(boolean securityInhibit) {
		this.securityInhibit = securityInhibit;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getItemProperties() {
		return itemProperties;
	}
	public void setItemProperties(String itemProperties) {
		this.itemProperties = itemProperties;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getScreenMessage() {
		return screenMessage;
	}
	public void setScreenMessage(String screenMessage) {
		this.screenMessage = screenMessage;
	}
	public String getPrintLine() {
		return printLine;
	}
	public void setPrintLine(String printLine) {
		this.printLine = printLine;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public CirculationTransaction getCirculationDetail() {
		return circulationDetail;
	}
	public void setCirculationDetail(CirculationTransaction circulationDetail) {
		this.circulationDetail = circulationDetail;
	}
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser user) {
		this.user = user;
	}
	public Date getRenewDateTime() {
		return renewDateTime;
	}
	public void setRenewDateTime(Date renewDateTime) {
		this.renewDateTime = renewDateTime;
	}
	public FinesTransaction getFinesDetail() {
		return finesDetail;
	}
	public void setFinesDetail(FinesTransaction fines) {
		this.finesDetail = fines;
	}
	public CirculationTransaction getParentCirculationDetail() {
		return parentCirculationDetail;
	}
	public void setParentCirculationDetail(CirculationTransaction parentCirculationDetail) {
		this.parentCirculationDetail = parentCirculationDetail;
	}
	
	
}
