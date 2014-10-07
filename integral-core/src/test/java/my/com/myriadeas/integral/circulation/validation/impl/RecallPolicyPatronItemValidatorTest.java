package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.PatronHasBorrowedItemException;
import my.com.myriadeas.integral.circulation.exception.PatronHasReservedItemException;
import my.com.myriadeas.integral.circulation.validation.impl.RecallPolicyPatronItemValidatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecallPolicyPatronItemValidatorTest {

	RecallPolicyPatronItemValidatorImpl recallPolicyPatronItemValidator;

	@Before
	public void setUp() {
		recallPolicyPatronItemValidator = new RecallPolicyPatronItemValidatorImpl();
	}

	@After
	public void tearDown() {
		recallPolicyPatronItemValidator = null;
	}

	@Test
	public void testValidatePatronHasBorrowedItem() {
		String patronId = "patron_1";
		String borrowerId = "patron_2";

		try {
			recallPolicyPatronItemValidator.validatePatronHasBorrowedItem(patronId,
					borrowerId);

		} catch (PatronHasBorrowedItemException e) {
			fail("Should not throw exception because patron not borrowed item.");
		}

		patronId = "patron_1";
		borrowerId = "patron_1";

		try {
			recallPolicyPatronItemValidator.validatePatronHasBorrowedItem(patronId,
					borrowerId);
			fail("Should hrow exception because patron has borrowed item.");
		} catch (PatronHasBorrowedItemException e) {

		}
	}

	@Test
	public void testValidatePatronHasReservedItem() {

		String patronId = "patron_1";
		String reserverId = "patron_2";
		try {
			recallPolicyPatronItemValidator.validatePatronHasReservedItem(patronId,
					reserverId);

		} catch (PatronHasReservedItemException e) {
			fail("Should not throw exception because patron not reserved item.");
		}

		patronId = "patron_1";
		reserverId = "patron_1";
		try {
			recallPolicyPatronItemValidator.validatePatronHasReservedItem(patronId,
					reserverId);
			fail("Should throw exception because patron has reserved item.");
		} catch (PatronHasReservedItemException e) {

		}

	}
}
