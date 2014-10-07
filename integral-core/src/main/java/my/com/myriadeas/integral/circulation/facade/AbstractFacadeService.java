package my.com.myriadeas.integral.circulation.facade;

import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.request.AbstractCirculationRequest;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.internalization.services.LocalizationServiceImpl;
import my.com.myriadeas.integral.publisher.Publisher;
import my.com.myriadeas.integral.security.jpa.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

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
