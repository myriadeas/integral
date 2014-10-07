package my.com.myriadeas.integral.circulation.response;


import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import com.ceridwen.circulation.SIP.types.enumerations.CirculationStatus;
import com.ceridwen.circulation.SIP.types.enumerations.CurrencyType;
import com.ceridwen.circulation.SIP.types.enumerations.FeeType;
import com.ceridwen.circulation.SIP.types.enumerations.SecurityMarker;


public class ItemInformationResponse {
	//1803000120100115    090236CF0|AH|AB0000841414|AJColloquial Japanese : the complete course for beginners / Hugh Clarke and Motoko Hamamura.|BHMYR|CKBOK|AQDEFT|APOS|CHPL539 .C64 2003|AF|AG|
	//18<circulation status><hold queue length><security marker><fee type><transaction date><due date><recall date><hold pickup date><item identifier><title identifier><owner><currency type><fee amount><media type><permanent location><current location><item properties><screen message><print line>
	private CirculationStatus circulationStatus;
	private SecurityMarker securityMarker;
	private FeeType feeType;	
	private Date transactionDate;
	private int holdQueueLength;
	private Date dueDate;
	private Date recallDate;
	private Date holdPickupDate;
	private String itemIdentifier;
	private String titleIdentifier;
	private String owner;
	private CurrencyType currencyType;
	private String feeAmount;
	private String mediaType;
	private String permanentLocation;
	private String currentLocation;
	private String itemProperties;
	private String screenMessage;
	private String printLine;
	
	private Item item;
	private ReservationTransaction awaitingCollectionReservationTransaction;
	private List<FinesTransaction> finesTransactions;
	
	
	public SecurityMarker getSecurityMarker() {
		return securityMarker;
	}
	public void setSecurityMarker(SecurityMarker securityMarker) {
		this.securityMarker = securityMarker;
	}
	public FeeType getFeeType() {
		return feeType;
	}
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getHoldQueueLength() {
		return holdQueueLength;
	}
	public void setHoldQueueLength(int holdQueueLength) {
		this.holdQueueLength = holdQueueLength;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getRecallDate() {
		return recallDate;
	}
	public void setRecallDate(Date recallDate) {
		this.recallDate = recallDate;
	}
	public Date getHoldPickupDate() {
		return holdPickupDate;
	}
	public void setHoldPickupDate(Date holdPickupDate) {
		this.holdPickupDate = holdPickupDate;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getPermanentLocation() {
		return permanentLocation;
	}
	public void setPermanentLocation(String permanentLocation) {
		this.permanentLocation = permanentLocation;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
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
	public CirculationStatus getCirculationStatus() {
		return circulationStatus;
	}
	public void setCirculationStatus(CirculationStatus circulationStatus) {
		this.circulationStatus = circulationStatus;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ReservationTransaction getAwaitingCollectionReservationTransaction() {
		return awaitingCollectionReservationTransaction;
	}
	public void setAwaitingCollectionReservationTransaction(ReservationTransaction awaitingCollectionReservationTransaction) {
		this.awaitingCollectionReservationTransaction = awaitingCollectionReservationTransaction;
	}
	public List<FinesTransaction> getFinesDetail() {
		return finesTransactions;
	}
	public void setFinesTransaction(List<FinesTransaction> finesTransactions) {
		this.finesTransactions = finesTransactions;
	}
	@Override
	public String toString() {
		return "ItemInformationResponse [circulationStatus="
				+ circulationStatus + ", securityMarker=" + securityMarker
				+ ", feeType=" + feeType + ", transactionDate="
				+ transactionDate + ", holdQueueLength=" + holdQueueLength
				+ ", dueDate=" + dueDate + ", recallDate=" + recallDate
				+ ", holdPickupDate=" + holdPickupDate + ", itemIdentifier="
				+ itemIdentifier + ", titleIdentifier=" + titleIdentifier
				+ ", owner=" + owner + ", currencyType=" + currencyType
				+ ", feeAmount=" + feeAmount + ", mediaType=" + mediaType
				+ ", permanentLocation=" + permanentLocation
				+ ", currentLocation=" + currentLocation + ", itemProperties="
				+ itemProperties + ", screenMessage=" + screenMessage
				+ ", printLine=" + printLine + ", item=" + item
				+ ", awaitingCollectionReservationTransaction="
				+ awaitingCollectionReservationTransaction
				+ ", finesTransactions=" + finesTransactions + "]";
	}
	

	
}
