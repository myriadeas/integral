package my.com.myriadeas.integral.circulation.response;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;


public class ReserveResponse implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	//16
	//16<ok><available><transaction date><expiration date><queue position><pickup location><institution id><patron identifier><item identifier><title identifier><screen message><print line>
	
	private boolean ok;
	private boolean available;
	private Date transactionDate;
	private Date expirationDate;
	private String queuePosition;
	private String pickupLocation;
	private String institutionId;
	private String patronIdentifier;	
	private String itemIdentifier;
	private String titleIdentifier;
	private String screenMessage;
	private String printLine;
	
	private ReservationTransaction reservationDetail;
	private Material material;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getQueuePosition() {
		return queuePosition;
	}
	public void setQueuePosition(String queuePosition) {
		this.queuePosition = queuePosition;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ReservationTransaction getReservationDetail() {
		return reservationDetail;
	}
	public void setReservationDetail(ReservationTransaction reservationDetail) {
		this.reservationDetail = reservationDetail;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	
}
