 package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckOutPolicyItemValidatorTest {

	CheckOutPolicyItemValidatorImpl checkOutItemValidator;
	Item item;
	
	@Before
	public void setUp() {
		checkOutItemValidator = new CheckOutPolicyItemValidatorImpl();
		item = new Item();
		
	}

	@After
	public void tearDown() {
		checkOutItemValidator = null;
		item = null;
	}

	@Test
	public void testValidateIsAccessionStatusValid()
			throws AvailableItemStatusForCheckOutException {
		
		ItemStatusIsPotentiallyLate(item);
		ItemStatusIsReturned(item);
		ItemStatusIsAvailable(item);
		ItemStatusIsBindery(item);
		ItemStatusIsCirculated(item);
		ItemStatusIsFinalProcessing(item);
		ItemStatusIsFound(item);
		ItemStatusIsHold(item);
		ItemStatusIsLost(item);
		ItemStatusIsMisplaced(item);
		ItemStatusIsMissing(item);
		ItemStatusIsRecalled(item);
		ItemStatusIsSuspended(item);
		ItemStatusIsWeed(item);

	}

	private void ItemStatusIsWeed(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.WEED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is WEED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}

	}

	private void ItemStatusIsSuspended(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.SUSPENDED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is SUSPENDED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsRecalled(Item item)
			throws AvailableItemStatusForCheckOutException {

		try {
			item.setItemStatus(ItemStatus.RECALLED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RECALLED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsMissing(Item item)
			throws AvailableItemStatusForCheckOutException {

		try {
			item.setItemStatus(ItemStatus.MISSING);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISSING.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsMisplaced(Item item)
			throws AvailableItemStatusForCheckOutException {

		try {
			item.setItemStatus(ItemStatus.MISPLACED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISPLACED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsLost(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {

			item.setItemStatus(ItemStatus.LOST);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is LOST.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsHold(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.HOLD);
			checkOutItemValidator.validateItemStatus(item);
		} catch (ItemStatusNotAllowedForCheckInException e) {
			fail("Should not throw exception because accession status is HOLD.");
		}
	}

	private void ItemStatusIsFound(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.FOUND);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FOUND.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsFinalProcessing(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.FINAL_PROCESSING);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FINAL_PROCESSING.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsCirculated(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.CIRCULATED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is CIRCULATED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsBindery(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.BINDERY);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is BINDERY.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsAvailable(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus.AVAILABLE);
			checkOutItemValidator.validateItemStatus(item);
		} catch (ItemStatusNotAllowedForCheckInException e) {
			fail("Should not throw exception because accession status is AVAILABLE.");
		}
	}

	private void ItemStatusIsReturned(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus._RETURNED);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RETURNED.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

	private void ItemStatusIsPotentiallyLate(Item item)
			throws AvailableItemStatusForCheckOutException {
		try {
			item.setItemStatus(ItemStatus._POTENTIALLY_LATE);
			checkOutItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is POTENTIALLY_LATE.");
		} catch (ItemStatusNotAllowedForCheckInException e) {

		}
	}

}
