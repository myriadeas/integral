package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumReservationAllowedException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityNotAllowReserveException;
import my.com.myriadeas.integral.circulation.validation.impl.AbstractCirculationReservePatronValidator;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractCirculationReservePatronValidatorTest {

	AbstractCirculationReservePatronValidator circulationReservePatronValidator;
	Patron patron;
	DateTime transactionDateTime;

	@Before
	public void setUp() throws Exception {
		circulationReservePatronValidator = EasyMock.createMockBuilder(
				AbstractCirculationReservePatronValidator.class).createMock();
		patron = new Patron();

	}

	@After
	public void tearDown() throws Exception {
		circulationReservePatronValidator = null;
		patron = null;
	}

	@Test
	public void testValidateExceededMaximumReservation() {

		int patronOnHoldItem = 0;
		int maximumReservationAllowed = 0;

		try {
			circulationReservePatronValidator
					.validateExceededMaximumReservation(patronOnHoldItem,
							maximumReservationAllowed);
		} catch (PatronEligibilityExceededMaximumReservationAllowedException e) {
			fail("Should thrown exception because patron's on hold item is 0");
		}

		patronOnHoldItem = 2;
		maximumReservationAllowed = 2;

		try {
			circulationReservePatronValidator
					.validateExceededMaximumReservation(patronOnHoldItem,
							maximumReservationAllowed);
			fail("Should thrown exception because patron's maximum allowed reservation is equals with patron's on hold item.");
		} catch (PatronEligibilityExceededMaximumReservationAllowedException e) {

		}

		patronOnHoldItem = 3;
		maximumReservationAllowed = 2;

		try {
			circulationReservePatronValidator
					.validateExceededMaximumReservation(patronOnHoldItem,
							maximumReservationAllowed);
			fail("Should thrown exception because patron's on hold item exceeded patron's maximum allowed reservation.");
		} catch (PatronEligibilityExceededMaximumReservationAllowedException e) {
		}

		patronOnHoldItem = 2;
		maximumReservationAllowed = 4;

		try {
			circulationReservePatronValidator
					.validateExceededMaximumReservation(patronOnHoldItem,
							maximumReservationAllowed);
		} catch (PatronEligibilityExceededMaximumReservationAllowedException e) {
			fail("Should not thrown exception because patron's maximum allowed reservation is greater than patron's on hold item.");

		}
	}

	@Test
	public void testValidatePatronAllowedReserve() {

		boolean isAllowedReserve = false;
		try {
			circulationReservePatronValidator
					.validatePatronAllowedReserve(isAllowedReserve);
			fail("Should thrown exception because patron is not allowed to make reservation");
		} catch (PatronEligibilityNotAllowReserveException e) {
		}

		isAllowedReserve = true;
		try {
			circulationReservePatronValidator
					.validatePatronAllowedReserve(isAllowedReserve);
		} catch (PatronEligibilityNotAllowReserveException e) {
			fail("Should not thrown exception because patron is allowed to make reservation");
		}
	}
}
