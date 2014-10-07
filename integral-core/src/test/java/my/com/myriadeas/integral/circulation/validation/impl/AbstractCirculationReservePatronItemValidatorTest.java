package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.PatronBranchNotMatchedItemLocationException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityNotAllowReserveException;
import my.com.myriadeas.integral.circulation.validation.impl.AbstractCirculationReservePatronItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractCirculationReservePatronItemValidatorTest {

	AbstractCirculationReservePatronItemValidator circulationReservePatronItemValidator;
	Patron patron;
	Item item;
	DateTime transactionDateTime;

	@Before
	public void setUp() {
		circulationReservePatronItemValidator = EasyMock.createMockBuilder(
				AbstractCirculationReservePatronItemValidator.class)
				.createMock();
		patron = new Patron();
		item = new Item();
		transactionDateTime = new DateTime();

	}

	@After
	public void tearDown() {
		circulationReservePatronItemValidator = null;
		patron = null;
		item = null;
		transactionDateTime = null;
	}

	@Test
	public void testValidatePatronBranchMatchedItemLocation() {

		String patronBranchCode = "branch_1";
		String accessionBranchCode = "branch_2";

		try {
			circulationReservePatronItemValidator
					.validatePatronBranchMatchedItemLocation(patronBranchCode,
							accessionBranchCode);
			fail("Should throw exception because patron branch not matched wirh accession branch.");
		} catch (PatronBranchNotMatchedItemLocationException e) {

		}

		patronBranchCode = "branch_1";
		accessionBranchCode = "branch_1";

		try {
			circulationReservePatronItemValidator
					.validatePatronBranchMatchedItemLocation(patronBranchCode,
							accessionBranchCode);

		} catch (PatronBranchNotMatchedItemLocationException e) {
			fail("Should not throw exception because patron branch not matched wirh accession branch.");
		}
	}

	@Test
	public void testValidatePatronItemAllowedReserve() {
		boolean isAllowedReserve = false;
		try {
			circulationReservePatronItemValidator
					.validatePatronItemAllowedReserve(isAllowedReserve);
			fail("Should throw exception because patron not allowed to make reservation.");
		} catch (PatronItemEligibilityNotAllowReserveException e) {

		}
		
		isAllowedReserve = true;
		try {
			circulationReservePatronItemValidator.validatePatronItemAllowedReserve(isAllowedReserve);
		} catch (PatronItemEligibilityNotAllowReserveException e) {
			fail("Should not throw exception because patron is allowed to make reservation.");
		}
	}
	
}
