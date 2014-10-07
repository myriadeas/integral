package my.com.myriadeas.integral.circulation.validation.impl;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.circulation.exception.BorrowerNotAllowedToRenewException;
import my.com.myriadeas.integral.circulation.exception.CirculationTransactionNotFoundException;
import my.com.myriadeas.integral.circulation.exception.DueDateIsAfterMembershipExpiredDateException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityNotAllowedOverdueException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityExceededMaximumFinesAllowedException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityExceededNumberOfRenewalException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityNotAllowedOverdueException;
import my.com.myriadeas.integral.circulation.request.RenewRequest;
import my.com.myriadeas.integral.circulation.service.impl.OverdueItem;
import my.com.myriadeas.integral.circulation.service.impl.OverdueLoanFactory;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;
import my.com.myriadeas.integral.eligibility.exception.EligibilityNotFoundException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "renewPatronItemValidator")
public class RenewPolicyPatronItemValidatorImpl extends
		AbstractCirculationPatronItemValidator implements
		RenewPolicyPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(RenewPolicyPatronItemValidatorImpl.class);

	private OverdueLoanFactory overdueLoanFactory;
	
	@Autowired
	private PatronEligibilityLookup patronEligibilityLookup;

	@Autowired
	private PatronItemEligibilityLookup patronItemEligibilityLookup;
		
	@Autowired
	public void setOverdueLoanFactory(OverdueLoanFactory overdueLoanFactory) {
		this.overdueLoanFactory = overdueLoanFactory;
	}

	@Override
	public void validate(RenewRequest renewRequest) {
		logger.debug("Entering validate(renewRequest={})", renewRequest);

		Item item = itemRepository.findByItemIdentifier(renewRequest
				.getItemIdentifier());
		Patron patron = item.getPatron();
		validate(patron, item);

		logger.debug("Leaving validate(). ");
	}

	@Override
	public void validateCustomPolicyPatronItemValidator(Patron patron, Item item) {

		logger.debug(
				"Entering validateCustomPolicyPatronItemValidator(patron={}, item={})",
				new Object[] { patron, item });

		Date renewalDate = new Date();
		validatePatronAllowRenewOnOverdueItem(patron, item, renewalDate);
		validatePatronItemAllowRenewOnOverdueItem(patron, item, renewalDate);
		validateExceededNumberOfRenewal(patron, item);
		validateDueDateIsAfterMembershipExpired(patron, item, renewalDate);
		validateMaximumFinesAllowed(patron, item, renewalDate);

		logger.debug("Leaving validateCustomPolicyPatronItemValidator(). ");
	}

	private void validateExceededNumberOfRenewal(Patron patron, Item item)
			throws EligibilityNotFoundException,
			CirculationTransactionNotFoundException,
			BorrowerNotAllowedToRenewException,
			PatronItemEligibilityExceededNumberOfRenewalException {
		logger.debug(
				"Entering validateExceededNumberOfRenewal(patron={}, item={})",
				new Object[] { patron, item });

		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		logger.debug("CirculationTransaction={}",
				circulationTransaction);
		if (circulationTransaction == null) {
			throw new CirculationTransactionNotFoundException(
					"Circulation transaction not found.");
		}

		validateExceededNumberOfRenewal(
				patronItemEligibility.getMaxRenewAllowed(),
				circulationTransaction.getNoOfRenew());

		logger.debug("Leaving validateExceededNumberOfRenewal(). ");
	}

	protected void validateExceededNumberOfRenewal(int maximumRenewalAllowed,
			int countRenew) throws BorrowerNotAllowedToRenewException,
			PatronItemEligibilityExceededNumberOfRenewalException {
		logger.debug(
				"Entering validateExceededNumberOfRenewal(maximumRenewalAllowed={}, countRenew={})",
				new Object[] { maximumRenewalAllowed, countRenew });

		if (maximumRenewalAllowed <= 0) {
			throw new BorrowerNotAllowedToRenewException(
					"The borrower is not eligible for renewal.");
		} else if (maximumRenewalAllowed <= countRenew) {
			throw new PatronItemEligibilityExceededNumberOfRenewalException(
					"Number of renewal allowed for this patron already exceeded.");
		}

		logger.debug("Leaving validateExceededNumberOfRenewal(). ");
	}

	private void validatePatronAllowRenewOnOverdueItem(Patron patron,
			Item item, Date renewalDate) throws EligibilityNotFoundException,
			PatronItemEligibilityNotAllowedOverdueException,
			PatronEligibilityNotAllowedOverdueException {
		logger.debug(
				"Entering validatePatronAllowRenewOnOverdueItem(patron={}, item={}, renewalDate={})",
				new Object[] { patron, item, renewalDate });
		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();

		PatronEligibility patronEligibility = this.patronEligibilityLookup
				.lookup(patron).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);
		Date dueDateTime = item.getDueDateTime();
		logger.info("Item due date time={}", dueDateTime.toString());
		
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		circulationTransaction.setDischargeDateTime(renewalDate);
		OverdueItem overdueItem = overdueLoanFactory
				.createOverdueItem(circulationTransaction);		
		int lateDays = overdueItem.getLateBy();
		logger.info("Late days={}", lateDays);
		if (lateDays > 0) {
			validatePatronAllowRenewOnOverdueItem(patronEligibility
					.isAllowOverdue());
		}

		logger.debug("Leaving validatePatronAllowRenewOnOverdueItem(). ");
	}

	protected void validatePatronAllowRenewOnOverdueItem(
			Boolean isAllowedOverdue)
			throws PatronEligibilityNotAllowedOverdueException {
		logger.debug(
				"Entering validatePatronAllowRenewOnOverdueItem(isAllowedOverdue={})",
				isAllowedOverdue);

		if (!isAllowedOverdue) {
			throw new PatronEligibilityNotAllowedOverdueException(
					"Patron eligibility is not allowed overdue.");
		}

		logger.debug("Leaving validatePatronAllowRenewOnOverdueItem(). ");
	}

	private void validatePatronItemAllowRenewOnOverdueItem(Patron patron,
			Item item, Date renewalDate) throws EligibilityNotFoundException,
			PatronItemEligibilityNotAllowedOverdueException {
		logger.debug(
				"Entering validatePatronItemAllowRenewOnOverdueItem(patron={}, item={}, renewalDate={})",
				new Object[] { patron, item, renewalDate });

		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);
		Date dueDateTime = item.getDueDateTime();
		logger.info("Item due date time={}", dueDateTime);
		
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		circulationTransaction.setDischargeDateTime(renewalDate);
		OverdueItem overdueItem = overdueLoanFactory
				.createOverdueItem(circulationTransaction);		
		int lateDays = overdueItem.getLateBy();		
		logger.info("Late days={}", lateDays);
		if (lateDays > 0) {
			validatePatronItemAllowRenewOnOverdueItem(patronItemEligibility
					.isALlowOverdue());
		}

		logger.debug("Leaving validatePatronItemAllowRenewOnOverdueItem(). ");
	}

	protected void validatePatronItemAllowRenewOnOverdueItem(
			Boolean isAllowedOverdue)
			throws PatronItemEligibilityNotAllowedOverdueException {
		logger.debug(
				"Entering validatePatronItemAllowRenewOnOverdueItem(isAllowedOverdue={})",
				isAllowedOverdue);
		if (!isAllowedOverdue) {
			throw new PatronItemEligibilityNotAllowedOverdueException(
					"Patron item eligibility is not allowed overdue.");
		}

		logger.debug("Leaving validatePatronItemAllowRenewOnOverdueItem(). ");
	}

	private void validateMaximumFinesAllowed(Patron patron, Item item,
			Date renewalDate) throws EligibilityNotFoundException,
			PatronItemEligibilityExceededMaximumFinesAllowedException {
		logger.debug(
				"Entering validateMaximumFinesAllowed(patron={}, item={}, renewalDate={})",
				new Object[] { patron, item, renewalDate });

		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);
	
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		circulationTransaction.setDischargeDateTime(renewalDate);
		OverdueItem overdueItem = overdueLoanFactory
				.createOverdueItem(circulationTransaction);		
		BigDecimal totalFines = overdueItem.getFines();
		logger.info("Total fines={}", totalFines);
		validateMaximumFinesAllowed(patronItemEligibility.getMaxFines(),
				totalFines);

		logger.debug("Leaving validateMaximumFinesAllowed(). ");
		
		
		
		

	}

	protected void validateMaximumFinesAllowed(BigDecimal maxFineAllowed,
			BigDecimal totalFines)
			throws PatronItemEligibilityExceededMaximumFinesAllowedException {
		logger.debug(
				"Entering validateMaximumFinesAllowed(maxFineAllowed={}, totalFines={})",
				new Object[] { maxFineAllowed, totalFines });

		if (maxFineAllowed.compareTo(totalFines) == 0
				|| maxFineAllowed.compareTo(totalFines) < 1) {

			throw new PatronItemEligibilityExceededMaximumFinesAllowedException(
					"Total fines already exceeded maximum fines allowed.",
					totalFines);
		}

		logger.debug("Leaving validateMaximumFinesAllowed(). ");
	}

	protected void validateDueDateIsAfterMembershipExpired(Patron patron,
			Item item, Date renewalDate)
			throws DueDateIsAfterMembershipExpiredDateException,
			EligibilityNotFoundException {
		logger.debug(
				"Entering validateDueDateIsAfterMembershipExpired(patron={}, item={}, renewalDate={})",
				new Object[] { patron, item, renewalDate });

		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);
		Date dueDateTime = this.dueDateCalculationService.calculateDueDateTime(
				item, patron, renewalDate);
		logger.info("Item due date time={}", dueDateTime.toString());
		Date membershipExpiredDate = patron.getMembershipExpiryDate();
		logger.info("Membership expired date={}",
				membershipExpiredDate.toString());
		boolean isDueAfterExpiry = circulationPolicyService.isDueAfterExpiry();
		logger.info("Circulation policy 'DueAfterExpiry'={}", isDueAfterExpiry);
		validateDueDateIsAfterMembershipExpired(dueDateTime,
				membershipExpiredDate, isDueAfterExpiry);

		logger.debug("Leaving validateDueDateIsAfterMembershipExpired(). ");
	}

	protected void validateDueDateIsAfterMembershipExpired(Date dueDate,
			Date membershipExpiredDate, boolean isDueAfterExpiry)
			throws DueDateIsAfterMembershipExpiredDateException {
		logger.debug(
				"Entering validateDueDateIsAfterMembershipExpired(dueDate={}, membershipExpiredDate={}, isDueAfterExpiry={})",
				new Object[] { dueDate, membershipExpiredDate, isDueAfterExpiry });

		DateTime dueDateTime = new DateTime(dueDate);
		DateTime expiredDateTime = new DateTime(membershipExpiredDate);
		logger.info("Item due date time={}", dueDateTime.toString());
		logger.info("Membership expired date time={}",
				expiredDateTime.toString());
		if ((dueDateTime.isAfter(expiredDateTime)) && (!isDueAfterExpiry)) {
			throw new DueDateIsAfterMembershipExpiredDateException(
					"Due date is after membership expired date.");
		}

		logger.debug("Leaving validateDueDateIsAfterMembershipExpired(). ");
	}

}
