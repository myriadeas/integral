package my.com.myriadeas.integral.circulation.service.impl;

import my.com.myriadeas.integral.circulation.service.LibraryPolicyService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("libraryPolicyService")
public class LibraryPolicyServiceImpl implements LibraryPolicyService {
	

	private static final String LIBRARY = "library";
		
	@Value("${" + LIBRARY + ".satellite}")
	private Boolean satellite; 
	@Value("${" + LIBRARY + ".currencyFormat}")
	private String currencyFormat; 
	@Value("${" + LIBRARY + ".initForex}")
	private String initForex;
	
	public Boolean getSatellite() {
		return satellite;
	}
	public void setSatellite(Boolean satellite) {
		if (satellite == null){
			satellite = new Boolean(false);
		}
		this.satellite = satellite;
	}
	public String getCurrencyFormat() {
		return currencyFormat;
	}
	public void setCurrencyFormat(String currencyFormat) {
		this.currencyFormat = currencyFormat;
	}
	public String getInitForex() {
		return initForex;
	}
	public void setInitForex(String initForex) {
		this.initForex = initForex;
	} 
	
	
	
	
	
	
			
}
