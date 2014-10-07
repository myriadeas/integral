package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.validation.impl.AbstractCirculationPatronValidator;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractCirculationPatronValidatorTest {

	AbstractCirculationPatronValidator circulationPatronValidator;
	Patron patron;
	DateTime transactionDateTime;

	@Before
	public void setUp() {
		circulationPatronValidator = EasyMock.createMockBuilder(
				AbstractCirculationPatronValidator.class).createMock();
		patron = new Patron();
		transactionDateTime = new DateTime();

	}

	@After
	public void tearDown() {
		circulationPatronValidator = null;
		patron = null;
		transactionDateTime = null;
	}


	@Test
	public void testValidateExpiredMembership() {

		DateTime expiredDateTime = null;

		try {
			expiredDateTime = new DateTime(2013, 2, 3, 0, 0, 0);
			circulationPatronValidator.validateExpiredMembership(
					expiredDateTime, transactionDateTime);
			fail("Should thrown exception.");
		} catch (ExpiredMembershipException e) {

		}

		try {
			expiredDateTime = new DateTime(2020, 2, 3, 0, 0, 0);
			circulationPatronValidator.validateExpiredMembership(
					expiredDateTime, transactionDateTime);

		} catch (ExpiredMembershipException e) {
			fail("Should not thrown exception.");
		}
	}

	@Test
	public void testValidateFutureMembership() {
		DateTime membershipDateTime = null;

		try {
			membershipDateTime = new DateTime(2020, 2, 3, 0, 0, 0);
			circulationPatronValidator.validateFutureMembership(
					membershipDateTime, transactionDateTime);
			fail("Should thrown exception");
		} catch (FutureMembershipException e) {

		}

		try {
			membershipDateTime = new DateTime(2013, 2, 3, 0, 0, 0);
			circulationPatronValidator.validateFutureMembership(
					membershipDateTime, transactionDateTime);

		} catch (FutureMembershipException e) {
			fail("Should not thrown exception");
		}
	}


}
