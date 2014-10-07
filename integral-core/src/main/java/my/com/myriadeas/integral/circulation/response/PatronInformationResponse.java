package my.com.myriadeas.integral.circulation.response;

import java.util.Date;

import com.ceridwen.circulation.SIP.types.enumerations.CurrencyType;
import com.ceridwen.circulation.SIP.types.enumerations.Language;


public class PatronInformationResponse {
	//64 YYY          00120100115    130022000000000003000000000000AOPTAR|AA2009209082|AEEDZATUL SYAHIRAH BINTI MOHD ADNAN|BZ0000|CA0000|CB0000|BLY|CQY|BHMYR|BV0.00|AU0000467450|AU0000499377|AU0000831223|BD8684/2 PERPINDAHAN BARU FIKRI, 24000 CHUKAI,KEMAMAN,TERENGGANU 24000 CHUKAI TERENGGANU |BE2|BF|AFPatron status is Satisfactory|AGPatron status is Satisfactory|
	//64<patron status><language><transaction date><hold items count><overdue items count><charged items count><fine items count><recall items count><unavailable holds count><institution id><patron identifier><personal name><hold items limit><overdue items limit><charged items limit><valid patron><valid patron password><currency type><fee amount><fee limit><items><home address><e-mail address><home phone number><screen message><print line>
	
	private String patronStatus;
	private Language language;
	private Date transactionDate;
	private int holdItemsCount;
	private int overdueItemsCount;
	private int chargedItemsCount;
	private int fineItemsCount;
	private int recallItemsCount;
	private int unavailableHoldsCount;
	private String institutionId;
	private String patronIdentifier;
	private String personalName;
	private int holdItemsLimit;
	private int overdueItemsLimit;
	private int chargedItemsLimit;
	private boolean validPatron;
	private boolean validPatronPassword;
	private CurrencyType currencyType;
	private String feeAmount;
	private String feeLimit;
	private String[] holdItems;
	private String[] overdueItems;
	private String[] chargedItems;
	private String[] fineItems;
	private String[] recallItems;
	private String[] unavailableHoldItems;
	private String homeAddress;
	private String emailAddress;
	private String homePhoneNumber;
	private String screenMessage;
	private String printLine;
	
	public String getPatronStatus() {
		return patronStatus;
	}
	public void setPatronStatus(String patronStatus) {
		this.patronStatus = patronStatus;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
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
	public String getPersonalName() {
		return personalName;
	}
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	
	
	public int getHoldItemsLimit() {
		return holdItemsLimit;
	}
	public void setHoldItemsLimit(int holdItemsLimit) {
		this.holdItemsLimit = holdItemsLimit;
	}
	public int getOverdueItemsLimit() {
		return overdueItemsLimit;
	}
	public void setOverdueItemsLimit(int overdueItemsLimit) {
		this.overdueItemsLimit = overdueItemsLimit;
	}
	public int getChargedItemsLimit() {
		return chargedItemsLimit;
	}
	public void setChargedItemsLimit(int chargedItemsLimit) {
		this.chargedItemsLimit = chargedItemsLimit;
	}
	public boolean isValidPatron() {
		return validPatron;
	}
	public void setValidPatron(boolean validPatron) {
		this.validPatron = validPatron;
	}
	public boolean isValidPatronPassword() {
		return validPatronPassword;
	}
	public void setValidPatronPassword(boolean validPatronPassword) {
		this.validPatronPassword = validPatronPassword;
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
	public String getFeeLimit() {
		return feeLimit;
	}
	public void setFeeLimit(String feeLimit) {
		this.feeLimit = feeLimit;		
	}	
	public String[] getHoldItems() {
		return holdItems;
	}
	public void setHoldItems(String[] holdItems) {
		this.holdItems = holdItems;
	}
	public String[] getOverdueItems() {
		return overdueItems;
	}
	public void setOverdueItems(String[] overdueItems) {
		this.overdueItems = overdueItems;
	}
	public String[] getChargedItems() {
		return chargedItems;
	}
	public void setChargedItems(String[] chargedItems) {
		this.chargedItems = chargedItems;
	}
	public String[] getFineItems() {
		return fineItems;
	}
	public void setFineItems(String[] fineItems) {
		this.fineItems = fineItems;
	}
	public String[] getRecallItems() {
		return recallItems;
	}
	public void setRecallItems(String[] recallItems) {
		this.recallItems = recallItems;
	}
	public String[] getUnavailableHoldItems() {
		return unavailableHoldItems;
	}
	public void setUnavailableHoldItems(String[] unavailableHoldItems) {
		this.unavailableHoldItems = unavailableHoldItems;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
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
	public int getHoldItemsCount() {
		return holdItemsCount;
	}
	public void setHoldItemsCount(int holdItemsCount) {
		this.holdItemsCount = holdItemsCount;
	}
	public int getOverdueItemsCount() {
		return overdueItemsCount;
	}
	public void setOverdueItemsCount(int overdueItemsCount) {
		this.overdueItemsCount = overdueItemsCount;
	}
	public int getChargedItemsCount() {
		return chargedItemsCount;
	}
	public void setChargedItemsCount(int chargedItemsCount) {
		this.chargedItemsCount = chargedItemsCount;
	}
	public int getFineItemsCount() {
		return fineItemsCount;
	}
	public void setFineItemsCount(int fineItemsCount) {
		this.fineItemsCount = fineItemsCount;
	}
	public int getRecallItemsCount() {
		return recallItemsCount;
	}
	public void setRecallItemsCount(int recallItemsCount) {
		this.recallItemsCount = recallItemsCount;
	}
	public int getUnavailableHoldsCount() {
		return unavailableHoldsCount;
	}
	public void setUnavailableHoldsCount(int unavailableHoldsCount) {
		this.unavailableHoldsCount = unavailableHoldsCount;
	}
	
	
	
}
