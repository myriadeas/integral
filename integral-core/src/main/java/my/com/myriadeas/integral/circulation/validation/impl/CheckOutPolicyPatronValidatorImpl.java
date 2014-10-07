package my.com.myriadeas.integral.circulation.validation.impl;

import java.util.List;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyPatronValidator;
import my.com.myriadeas.integral.core.PatronNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("checkOutPatronValidator")
public class CheckOutPolicyPatronValidatorImpl extends
		AbstractCirculationPatronValidator implements
		CheckOutPolicyPatronValidator {

	private static Logger logger = LoggerFactory
			.getLogger(CheckOutPolicyPatronValidatorImpl.class);

	public void validate(String patronIdentifier) {
		Patron patron = patronRepository.findByUsername(patronIdentifier);
		validate(patron);
	}

	@Override
	public void validate(CheckOutRequest checkOutRequest)
			throws PatronNotFoundException, FutureMembershipException,
			ExpiredMembershipException,
			PatronEligibilityExceededMaximumLoanException {
		logger.debug("Entering validate(checkOutRequest={})", checkOutRequest);

		Patron patron = patronRepository.findByUsername(checkOutRequest
				.getPatronIdentifier());
		validate(patron);

		logger.debug("Leaving validate(). ");

	}

	@Override
	protected void validateCustomPolicyPatronValidator(Patron patron)
			throws PatronEligibilityExceededMaximumLoanException {
		logger.debug("Entering validateCustomPolicyPatronValidator(patron={})",
				patron);

		validateExceededMaxLoanInPatronEligibility(patron);

		logger.debug("Leaving validateCustomPolicyPatronValidator(). ");

	}

	private void validateExceededMaxLoanInPatronEligibility(Patron patron)
			throws PatronEligibilityExceededMaximumLoanException {
		logger.debug(
				"Entering validateExceededMaxLoanInPatronEligibility(patron={})",
				patron);

		PatronEligibility patronEligibility = this.patronEligibilityLookup
				.lookup(patron).getDomain();
		List<Item> itemList = patron.getOnLoanItems();
		validateExceededMaxLoanInPatronEligibility(
				patronEligibility.getMaxLoanAllowed(), itemList.size());

		logger.debug("Leaving validateExceededMaxLoanInPatronEligibility(). ");
	}

	protected void validateExceededMaxLoanInPatronEligibility(
			int maxLoanAllowed, int patronOnLoanItemCount)
			throws PatronEligibilityExceededMaximumLoanException {
		logger.debug(
				"Entering validateExceededMaxLoanInPatronEligibility(maxLoanAllowed={}, patronOnLoanItemCount={})",
				new Object[] { maxLoanAllowed, patronOnLoanItemCount });

		if (maxLoanAllowed > 0 && patronOnLoanItemCount >= maxLoanAllowed) {
			throw new PatronEligibilityExceededMaximumLoanException(
					"Maximum loan is already exceeded.", patronOnLoanItemCount);
		}

		logger.debug("Leaving validateExceededMaxLoanInPatronEligibility(). ");
	}

}
