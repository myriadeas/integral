package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

import com.ceridwen.circulation.SIP.types.enumerations.Language;
import com.ceridwen.circulation.SIP.types.flagfields.Summary;


public class PatronInformationRequest {
	//6300120100115    130023          AOPTAR|AASYSADMIN|AD20092090|
	//63<language><transaction date><summary><institution id><patron identifier><terminal password><patron password><start item><end item>

	private Language language;
	private Date transactionDate;
	private Summary summary;
	private String institutionId;
	private String patronIdentifier;
	private String terminalPassword;
	private String patronPassword;
	private int startItem;
	private int endItem;
	
	public PatronInformationRequest(){
		
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
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
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
	public String getTerminalPassword() {
		return terminalPassword;
	}
	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}
	public String getPatronPassword() {
		return patronPassword;
	}
	public void setPatronPassword(String patronPassword) {
		this.patronPassword = patronPassword;
	}
	public int getStartItem() {
		return startItem;
	}
	public void setStartItem(int startItem) {
		this.startItem = startItem;
	}
	public int getEndItem() {
		return endItem;
	}
	public void setEndItem(int endItem) {
		this.endItem = endItem;
	}
	
	
}
