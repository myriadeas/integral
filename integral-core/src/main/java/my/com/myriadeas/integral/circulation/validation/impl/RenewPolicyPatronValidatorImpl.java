package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.request.RenewRequest;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.ItemRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "renewPatronValidator")
public class RenewPolicyPatronValidatorImpl extends
		AbstractCirculationPatronValidator implements RenewPolicyPatronValidator {

	private static Logger logger = LoggerFactory.getLogger(RenewPolicyPatronValidatorImpl.class);

	@Autowired
	PatronRepository patronRepository;
	
	@Autowired
	protected ItemRepositoryImpl itemRepository;

	@Override
	public void validate(RenewRequest renewRequest) {
		logger.debug("Entering validate(renewRequest={})", renewRequest);
		
		Item item = itemRepository.findByItemIdentifier(renewRequest
				.getItemIdentifier());
		Patron patron = item.getPatron();
		validate(patron);

		logger.debug("Leaving validate(). ");

	}

	@Override
	protected void validateCustomPolicyPatronValidator(Patron patron)
			throws PatronEligibilityExceededMaximumLoanException {
		// TODO Auto-generated method stub

	}

}
