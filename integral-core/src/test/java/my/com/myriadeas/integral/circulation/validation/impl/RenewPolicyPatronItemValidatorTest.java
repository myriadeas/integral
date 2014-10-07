package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import my.com.myriadeas.integral.circulation.exception.BorrowerNotAllowedToRenewException;
import my.com.myriadeas.integral.circulation.exception.DueDateIsAfterMembershipExpiredDateException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityNotAllowedOverdueException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityExceededMaximumFinesAllowedException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityExceededNumberOfRenewalException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityNotAllowedOverdueException;
import my.com.myriadeas.integral.circulation.validation.impl.RenewPolicyPatronItemValidatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RenewPolicyPatronItemValidatorTest {

	RenewPolicyPatronItemValidatorImpl renewPolicyPatronItemValidator;

	@Before
	public void setUp() {
		renewPolicyPatronItemValidator = new RenewPolicyPatronItemValidatorImpl();
	}

	@After
	public void tearDown() {
		renewPolicyPatronItemValidator = null;
	}

	@Test
	public void testValidatePatronItemAllowRenewOnOverdueItem() {

		boolean isAllowedOverdue = true;
		try {
			renewPolicyPatronItemValidator
					.validatePatronItemAllowRenewOnOverdueItem(isAllowedOverdue);

		} catch (PatronItemEligibilityNotAllowedOverdueException e) {
			fail("Should not throw exception because item is allowed overdue.");
		}

		isAllowedOverdue = false;
		try {
			renewPolicyPatronItemValidator
					.validatePatronItemAllowRenewOnOverdueItem(isAllowedOverdue);
			fail("Should throw exception because item is not allowed overdue.");
		} catch (PatronItemEligibilityNotAllowedOverdueException e) {

		}
	}

	@Test
	public void testValidatePatronAllowRenewOnOverdueItem() {

		boolean isAllowedOverdue = true;
		try {
			renewPolicyPatronItemValidator
					.validatePatronAllowRenewOnOverdueItem(isAllowedOverdue);

		} catch (PatronEligibilityNotAllowedOverdueException e) {
			fail("Should not throw exception because item is allowed overdue.");
		}

		isAllowedOverdue = false;
		try {
			renewPolicyPatronItemValidator
					.validatePatronAllowRenewOnOverdueItem(isAllowedOverdue);
			fail("Should throw exception because item is not allowed overdue.");
		} catch (PatronEligibilityNotAllowedOverdueException e) {

		}
	}

	@Test
	public void testValidateExceededNumberOfRenewal()
			throws BorrowerNotAllowedToRenewException,
			PatronItemEligibilityExceededNumberOfRenewalException {

		int maximumRenewalAllowed = 3;
		int countRenew = 2;
		try {
			renewPolicyPatronItemValidator.validateExceededNumberOfRenewal(
					maximumRenewalAllowed, countRenew);
		} catch (PatronItemEligibilityExceededNumberOfRenewalException e) {
			fail("Should not throw exception because renewal count is less than maximum renewal allowed.");
		}

		maximumRenewalAllowed = 3;
		countRenew = 3;
		try {
			renewPolicyPatronItemValidator.validateExceededNumberOfRenewal(
					maximumRenewalAllowed, countRenew);
			fail("Should throw exception because renewal count is equal maximum renewal allowed.");
		} catch (PatronItemEligibilityExceededNumberOfRenewalException e) {

		}

		maximumRenewalAllowed = 3;
		countRenew = 4;
		try {
			renewPolicyPatronItemValidator.validateExceededNumberOfRenewal(
					maximumRenewalAllowed, countRenew);
			fail("Should throw exception because renewal count is greater maximum renewal allowed.");
		} catch (PatronItemEligibilityExceededNumberOfRenewalException e) {

		}

		maximumRenewalAllowed = -1;
		countRenew = 2;
		try {
			renewPolicyPatronItemValidator.validateExceededNumberOfRenewal(
					maximumRenewalAllowed, countRenew);
			fail("Should throw exception because maximum renewal allowed is less than equal 0.");
		} catch (BorrowerNotAllowedToRenewException e) {

		}
	}

	@Test
	public void testValidateMaximumFinesAllowed()
			throws PatronItemEligibilityExceededMaximumFinesAllowedException {

		BigDecimal maxFineAllowed = new BigDecimal("10");
		BigDecimal totalFines = new BigDecimal("5");
		try {
			renewPolicyPatronItemValidator.validateMaximumFinesAllowed(
					maxFineAllowed, totalFines);
		} catch (PatronItemEligibilityExceededMaximumFinesAllowedException e) {
			fail("Should not throw exception because max fines allowed is less total fines.");
		}

		maxFineAllowed = new BigDecimal("10");
		totalFines = new BigDecimal("10");
		try {
			renewPolicyPatronItemValidator.validateMaximumFinesAllowed(
					maxFineAllowed, totalFines);
			fail("Should throw exception because max fines allowed is equal total fines.");
		} catch (PatronItemEligibilityExceededMaximumFinesAllowedException e) {

		}

		maxFineAllowed = new BigDecimal("10");
		totalFines = new BigDecimal("15");
		try {
			renewPolicyPatronItemValidator.validateMaximumFinesAllowed(
					maxFineAllowed, totalFines);
			fail("Should throw exception because max fines allowed is greater total fines.");
		} catch (PatronItemEligibilityExceededMaximumFinesAllowedException e) {

		}
	}

	@Test
	public void testValidateDueDateIsAfterMembershipExpired() {
		Calendar calendar = Calendar.getInstance();

		try {

			calendar.set(2014, Calendar.JANUARY, 30);
			Date membershipExpiredDate = calendar.getTime();
			calendar.set(2013, Calendar.DECEMBER, 30);
			Date dueDate = calendar.getTime();
			boolean isDueAfterExpiry = false;

			renewPolicyPatronItemValidator
					.validateDueDateIsAfterMembershipExpired(dueDate,
							membershipExpiredDate, isDueAfterExpiry);
		} catch (DueDateIsAfterMembershipExpiredDateException e) {

			fail("Should not throw exception because due date is before membership expired date.");
		}

		try {

			calendar.set(2013, Calendar.JANUARY, 30);
			Date membershipExpiredDate = calendar.getTime();
			calendar.set(2013, Calendar.FEBRUARY, 30);
			Date dueDate = calendar.getTime();
			boolean isDueAfterExpiry = false;

			renewPolicyPatronItemValidator
					.validateDueDateIsAfterMembershipExpired(dueDate,
							membershipExpiredDate, isDueAfterExpiry);
			fail("Should throw exception because membership expired and not allowed to due after membership expired");
		} catch (DueDateIsAfterMembershipExpiredDateException e) {

		}

		try {

			calendar.set(2013, Calendar.JANUARY, 30);
			Date membershipExpiredDate = calendar.getTime();
			calendar.set(2013, Calendar.FEBRUARY, 30);
			Date dueDate = calendar.getTime();
			boolean isDueAfterExpiry = true;

			renewPolicyPatronItemValidator
					.validateDueDateIsAfterMembershipExpired(dueDate,
							membershipExpiredDate, isDueAfterExpiry);

		} catch (DueDateIsAfterMembershipExpiredDateException e) {
			fail("Should throw exception because membership expired but is allowed to due after membership expired.");
		}
	}

}
