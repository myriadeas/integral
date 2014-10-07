package my.com.myriadeas.integral.circulation.response;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import com.ceridwen.circulation.SIP.types.enumerations.CurrencyType;
import com.ceridwen.circulation.SIP.types.enumerations.FeeType;
import com.ceridwen.circulation.SIP.types.enumerations.MediaType;


public class CheckOutResponse implements java.io.Serializable{
	
	@Override
	public String toString() {
		return "CheckOutResponse [ok=" + ok + ", renewalOk=" + renewalOk
				+ ", magneticMedia=" + magneticMedia + ", desensitize="
				+ desensitize + ", transactionDate=" + transactionDate
				+ ", institutionId=" + institutionId + ", patronIdentifier="
				+ patronIdentifier + ", itemIdentifier=" + itemIdentifier
				+ ", titleIdentifier=" + titleIdentifier + ", dueDate="
				+ dueDate + ", feeType=" + feeType + ", securityInhibit="
				+ securityInhibit + ", currencyType=" + currencyType
				+ ", feeAmount=" + feeAmount + ", mediaType=" + mediaType
				+ ", itemProperties=" + itemProperties + ", transactionId="
				+ transactionId + ", screenMessage=" + screenMessage
				+ ", printLine=" + printLine + "]";
	}
	private static final long serialVersionUID = 1L;
	//121NNY20100115    123324AOPTAR|AA2008558337|AB0000628042|AJPatan Museum : the transformation of a royal palace in Nepal / Gotz Hagmuller.|AH29/01/2010|BHMYR|BV0|AFItem is successfully charged|
	//12<ok><renewal ok><magnetic media><desensitize><transaction date><institution id><patron identifier><item identifier><title identifier><due date><fee type><security inhibit><currency type><fee amount><media type><item properties><transaction id><screen message><print line>
	
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
	private CurrencyType currencyType;
	private String feeAmount;
	private MediaType mediaType;
	private String itemProperties;
	private String transactionId;
	private String screenMessage;
	private String printLine;
	
	private Item item;
	private CirculationTransaction circulationDetail;
	private AbstractUser user;
	private Date checkOutDateTime;
	
	public boolean getOk() {
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
	public boolean getMagneticMedia() {
		return magneticMedia;
	}
	public void setMagneticMedia(boolean magneticMedia) {
		this.magneticMedia = magneticMedia;
	}
	public boolean getDesensitize() {
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
	
	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public MediaType getMediaType() {
		return mediaType;
	}
	public void setMediaType(MediaType mediaType) {
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
	public boolean isSecurityInhibit() {
		return securityInhibit;
	}
	public void setSecurityInhibit(boolean securityInhibit) {
		this.securityInhibit = securityInhibit;
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
	public void setUser(AbstractUser patron) {
		this.user = patron;
	}
	public Date getCheckOutDateTime() {
		return checkOutDateTime;
	}
	public void setCheckOutDateTime(Date checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}
	
	

}
