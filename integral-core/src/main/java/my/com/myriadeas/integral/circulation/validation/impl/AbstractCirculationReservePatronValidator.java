package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumReservationAllowedException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityNotAllowReserveException;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCirculationReservePatronValidator extends
		AbstractCirculationPatronValidator {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractCirculationReservePatronValidator.class);

	@Override
	protected void validateCustomPolicyPatronValidator(Patron patron)
			throws PatronEligibilityExceededMaximumLoanException {
		logger.debug("Entering validateCustomPolicyPatronValidator(patron={})",
				patron);

		validatePatronAllowedReserve(patron);
		validateExceededMaximumReservation(patron);

		logger.debug("Leaving validateCustomPolicyPatronValidator(). ");

	}

	private void validatePatronAllowedReserve(Patron patron)
			throws PatronEligibilityNotAllowReserveException {
		logger.debug("Entering validatePatronAllowedReserve(patron={})", patron);

		PatronEligibility patronEligibility = this.patronEligibilityLookup
				.lookup(patron).getDomain();
		logger.debug("patronEligibility={}", patronEligibility);

		validatePatronAllowedReserve(patronEligibility.isAllowReserve());

		logger.debug("Leaving validatePatronAllowedReserve(). ");
	}

	protected void validatePatronAllowedReserve(boolean isAllowedReserve)
			throws PatronEligibilityNotAllowReserveException {
		logger.debug(
				"Entering validatePatronAllowedReserve(isAllowedReserve={})",
				isAllowedReserve);

		if (!isAllowedReserve) {
			throw new PatronEligibilityNotAllowReserveException(
					"Patron eligibility not allowed reserve.");
		}

		logger.debug("Leaving validatePatronAllowedReserve(). ");
	}

	private void validateExceededMaximumReservation(Patron patron)
			throws PatronEligibilityExceededMaximumReservationAllowedException {
		logger.debug("Entering validateExceededMaximumReservation(patron={})",
				patron);

		PatronEligibility patronEligibility = this.patronEligibilityLookup
				.lookup(patron).getDomain();
		logger.debug("PatronEligibility={}", patronEligibility);
		int patronOnHoldItemCount = this.reservationTransactionRepository
				.findByPatron(patron).size();
		logger.info("Patron on hold item count={}", patronOnHoldItemCount);
		int maximumReservationAllowed = patronEligibility
				.getMaxReservationAllowed();
		logger.info("Patron eligibility maximum reservation allowed={}",
				maximumReservationAllowed);
		validateExceededMaximumReservation(patronOnHoldItemCount,
				maximumReservationAllowed);

		logger.debug("Leaving validateExceededMaximumReservation(). ");
	}

	protected void validateExceededMaximumReservation(int patronOnHoldItem,
			int maximumReservationAllowed)
			throws PatronEligibilityExceededMaximumReservationAllowedException {
		logger.debug(
				"Entering validateExceededMaximumReservation(patronOnHoldItem={}, maximumReservationAllowed={})",
				new Object[] { patronOnHoldItem, maximumReservationAllowed });

		if ((maximumReservationAllowed > 0)
				&& (maximumReservationAllowed <= patronOnHoldItem)) {

			throw new PatronEligibilityExceededMaximumReservationAllowedException(
					"Exceeded maximum reservation allowed");
		}

		logger.debug("validateExceededMaximumReservation(). ");
	}

}
