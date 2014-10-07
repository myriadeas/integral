package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.PatronHasBorrowedItemByMaterialException;
import my.com.myriadeas.integral.circulation.validation.impl.ReservePolicyPatronItemValidatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservePolicyPatronItemValidatorTest {

	ReservePolicyPatronItemValidatorImpl reservePolicyPatronItemValidator;

	@Before
	public void setUp() {
		reservePolicyPatronItemValidator = new ReservePolicyPatronItemValidatorImpl();
	}

	@After
	public void tearDown() {
		reservePolicyPatronItemValidator = null;
	}
	
	@Test
	public void testValidatePatronHasBorrowedItemByMaterial() {
		String patronId = "patron_1";
		String borrowerId = "patron_2";

		try {
			reservePolicyPatronItemValidator
					.validatePatronHasBorrowedItemByMaterial(patronId,
							borrowerId);

		} catch (PatronHasBorrowedItemByMaterialException e) {
			fail("Should not throw exception because patron not borrowed item by material.");
		}

		patronId = "patron_1";
		borrowerId = "patron_1";

		try {
			reservePolicyPatronItemValidator
					.validatePatronHasBorrowedItemByMaterial(patronId,
							borrowerId);
			fail("Should hrow exception because patron has borrowed item by material.");
		} catch (PatronHasBorrowedItemByMaterialException e) {

		}
	}



}
