package my.com.myriadeas.integral.cataloguing.facade;

import my.com.myriadeas.integral.internalization.services.LocalizationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * A base class class for circulation process
 * 
 * @author hutingung
 * 
 */
public abstract class AbstractFacadeService {

	protected LocalizationServiceImpl localizationService;

	

	@Autowired
	protected void setLocalizationService(LocalizationServiceImpl localizationService) {
		this.localizationService = localizationService;
	}
	
	protected LocalizationServiceImpl getLocalizationService() {
		return localizationService;
	}


	public String getMessage(String code,  Object[] argument, String defaultMessage){
		return localizationService.getMessage(code, argument, defaultMessage);
	}
	
	public String getMessage(String code,  String defaultMessage){
		return getMessage(code, null, defaultMessage);
	}
	
}
