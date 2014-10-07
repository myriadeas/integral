package my.com.myriadeas.integral.circulation.response;


import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public class CheckInResponse implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//101NNN20100115    123325AOPTAR|AB0000628042|AQOS|AJPatan Museum : the transformation of a royal palace in Nepal / Gotz Hagmuller.|AA2008558337|CK|CHNA1510.8.N42 P35 2003|AFItem is successfully Discharged.|AGItem is successfully Discharged.|
	//10<ok><resensitize><magnetic media><alert><transaction date><institution id><item identifier><permanent location><title identifier><sort bin><patron identifier><media type><item properties><screen message><print line>
	private boolean ok;
	private boolean resensitize;
	private boolean magneticMedia;
	private boolean alert;
	private Date transactionDate;
	private String institutionId;
	private String itemIdentifier;	
	private String permanentLocation;	
	private String titleIdentifier;
	private String sortBin;
	private String patronIdentifier;
	private String mediaType;
	private String itemProperties;
	private String screenMessage;
	private String printLine;
	
	private Item item;
	private CirculationTransaction circulationDetail;
	private AbstractUser user;
	private Date checkInDateTime;
	
	public boolean getOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public boolean isResensitize() {
		return resensitize;
	}
	public void setResensitize(boolean resensitize) {
		this.resensitize = resensitize;
	}
	public boolean getMagneticMedia() {
		return magneticMedia;
	}
	public void setMagneticMedia(boolean magneticMedia) {
		this.magneticMedia = magneticMedia;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
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
	public String getPermanentLocation() {
		return permanentLocation;
	}
	public void setPermanentLocation(String permanentLocation) {
		this.permanentLocation = permanentLocation;
	}
	public String getTitleIdentifier() {
		return titleIdentifier;
	}
	public void setTitleIdentifier(String titleIdentifier) {
		this.titleIdentifier = titleIdentifier;
	}
	public String getSortBin() {
		return sortBin;
	}
	public void setSortBin(String sortBin) {
		this.sortBin = sortBin;
	}
	public String getPatronIdentifier() {
		return patronIdentifier;
	}
	public void setPatronIdentifier(String patronIdentifier) {
		this.patronIdentifier = patronIdentifier;
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
	public void setUser(AbstractUser patron) {
		this.user = patron;
	}
	public Date getCheckInDateTime() {
		return checkInDateTime;
	}
	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}
	
	
	
	

}
