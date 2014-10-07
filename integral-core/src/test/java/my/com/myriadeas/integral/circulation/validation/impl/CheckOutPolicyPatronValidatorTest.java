package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.*;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.validation.impl.CheckOutPolicyPatronValidatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckOutPolicyPatronValidatorTest {

	CheckOutPolicyPatronValidatorImpl checkOutPatronValidator;

	@Before
	public void setUp() {
		checkOutPatronValidator = new CheckOutPolicyPatronValidatorImpl();

	}

	@After
	public void tearDown() {
		checkOutPatronValidator = null;
	}

	@Test
	public void testValidateExceededMaxLoanInPatronEligibility() {

		try {
			int maxLoanAllowed = 5;
			int patronOnLoanItemCount = 0;

			checkOutPatronValidator.validateExceededMaxLoanInPatronEligibility(
					maxLoanAllowed, patronOnLoanItemCount);
		} catch (PatronEligibilityExceededMaximumLoanException e) {
			fail("Should not throw exception because patron's on loan item is less than maximum loan allowed.");
		}

		try {
			int maxLoanAllowed = 5;
			int patronOnLoanItemCount = 5;

			checkOutPatronValidator.validateExceededMaxLoanInPatronEligibility(
					maxLoanAllowed, patronOnLoanItemCount);
			fail("Should throw exception because patron's on loan item is equals maximum loan allowed.");
		} catch (PatronEligibilityExceededMaximumLoanException e) {

		}

		try {
			int maxLoanAllowed = 4;
			int patronOnLoanItemCount = 5;

			checkOutPatronValidator.validateExceededMaxLoanInPatronEligibility(
					maxLoanAllowed, patronOnLoanItemCount);
			fail("Should throw exception because patron's on loan item is greater than maximum loan allowed.");
		} catch (PatronEligibilityExceededMaximumLoanException e) {

		}

	}

}
