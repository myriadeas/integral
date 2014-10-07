package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.request.RecallRequest;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronValidator;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "recallPatronValidator")
public class RecallPolicyPatronValidatorImpl extends
		AbstractCirculationReservePatronValidator implements
		RecallPolicyPatronValidator {
	
	private static Logger logger = LoggerFactory.getLogger(RecallPolicyPatronValidatorImpl.class);
	
	@Override
	public void validate(RecallRequest recallRequest) {		
		logger.debug("Entering validate(recallRequest={})", recallRequest);
		
		Patron patron = patronRepository.findByUsername(recallRequest
				.getPatronIdentifier());
		validate(patron);

		logger.debug("Leaving validate(). ");
	}

}
