package my.com.myriadeas.integral.circulation.validation.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.core.PatronNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class AbstractCirculationPatronValidator {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractCirculationPatronValidator.class);

	@Autowired
	protected ReservationTransactionRepositoryImpl reservationTransactionRepository;

	@Autowired
	protected PatronEligibilityLookup patronEligibilityLookup;

	@Autowired
	protected PatronRepository patronRepository;

	public void validate(Patron patron) throws PatronNotFoundException,
			FutureMembershipException, ExpiredMembershipException,
			PatronEligibilityExceededMaximumLoanException {
		logger.debug("Entering validate(patron={})", patron);

		Assert.notNull(patron);
		Date transactionDate = new Date();

		validateFutureMembership(patron, transactionDate);
		validateExpiredMembership(patron, transactionDate);
		validateCustomPolicyPatronValidator(patron);

		logger.debug("Leaving validate(). ");
	}

	protected abstract void validateCustomPolicyPatronValidator(Patron patron)
			throws PatronEligibilityExceededMaximumLoanException;

	private void validateExpiredMembership(Patron patron, Date transactionDate)
			throws ExpiredMembershipException {
		logger.debug(
				"Entering validateIsExpiredMembership(patron={}, transactionDate={})",
				new Object[] { patron, transactionDate });

		validateExpiredMembership(
				new DateTime(patron.getMembershipExpiryDate()), new DateTime(
						transactionDate));

		logger.debug("Leaving validateIsExpiredMembership(). ");
	}

	protected void validateExpiredMembership(DateTime expiredDateTime,
			DateTime transactionDateTime) throws ExpiredMembershipException {
		logger.debug(
				"Entering validateIsExpiredMembership(expiredDateTime={}, transactionDateTime={})",
				new Object[] { expiredDateTime, transactionDateTime });

		if (expiredDateTime.isEqual(transactionDateTime)
				|| expiredDateTime.isBefore(transactionDateTime)) {

			throw new ExpiredMembershipException(
					"This patron membership already expired.",
					expiredDateTime.toString());
		}

		logger.debug("Leaving validateIsExpiredMembership(). ");
	}

	private void validateFutureMembership(Patron patron, Date transactionDate)
			throws FutureMembershipException {
		logger.debug(
				"Entering validateIsFutureMembership(patron={}, transactionDate={})",
				new Object[] { patron, transactionDate });

		validateFutureMembership(new DateTime(patron.getMembershipDate()),
				new DateTime(transactionDate));

		logger.debug("Leaving validateIsFutureMembership(). ");
	}

	protected void validateFutureMembership(DateTime membershipDateTime,
			DateTime transactionDateTime) throws FutureMembershipException {
		logger.debug(
				"Entering validateIsFutureMembership(membershipDateTime={}, transactionDateTime={})",
				new Object[] { membershipDateTime, transactionDateTime });

		if (membershipDateTime.isAfter(transactionDateTime)) {
			throw new FutureMembershipException(
					"This patron membership date is not active yet.",
					membershipDateTime.toString());
		}

		logger.debug("Leaving validateIsFutureMembership(). ");
	}

}
