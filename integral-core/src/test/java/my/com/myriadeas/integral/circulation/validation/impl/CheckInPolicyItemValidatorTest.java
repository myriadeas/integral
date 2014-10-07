package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckInPolicyItemValidatorTest {

	CheckInPolicyItemValidatorImpl checkInPolicyItemValidator;

	@Before
	public void setUp() {
		checkInPolicyItemValidator = new CheckInPolicyItemValidatorImpl();

	}

	@After
	public void tearDown() {
		checkInPolicyItemValidator = null;
	}

	@Test
	public void testValidateIsAccessionStatusValid()
			throws ItemStatusNotAllowedForCheckInException {
		Item item = new Item();
		
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

	private void expectToThrowExceptionBecauseInvalidStatus(Item item) {
		try {
			checkInPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is "
					+ item.getItemStatus());
		} catch (ItemStatusNotAllowedForCheckInException e) {
		} catch (AvailableItemStatusForCheckOutException e) {
		}
	}

	private void ItemStatusIsWeed(Item item) {
		item.setItemStatus(ItemStatus.WEED);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsSuspended(Item item) {
		item.setItemStatus(ItemStatus.SUSPENDED);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsRecalled(Item item) {
		try {
			item.setItemStatus(ItemStatus.RECALLED);
		} catch (ItemStatusNotAllowedForCheckInException e) {
			fail("Should not throw exception because accession status is RECALLED.");
		} catch (AvailableItemStatusForCheckOutException e) {
			fail("Should not throw exception because accession status is RECALLED.");
		}

	}

	private void ItemStatusIsMissing(Item item) {
		item.setItemStatus(ItemStatus.MISSING);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsMisplaced(Item item) {

		item.setItemStatus(ItemStatus.MISPLACED);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsLost(Item item) {
		item.setItemStatus(ItemStatus.LOST);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsHold(Item item) {
		item.setItemStatus(ItemStatus.HOLD);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsFound(Item item) {
		item.setItemStatus(ItemStatus.FOUND);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsFinalProcessing(Item item) {
		item.setItemStatus(ItemStatus.FINAL_PROCESSING);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsCirculated(Item item){
		try {
			item.setItemStatus(ItemStatus.CIRCULATED);
			checkInPolicyItemValidator.validateItemStatus(item);
		} catch (ItemStatusNotAllowedForCheckInException e) {
			fail("Should not throw exception because accession status is CIRCULATED.");
		} catch (AvailableItemStatusForCheckOutException e) {

		}
	}

	private void ItemStatusIsBindery(Item item) {
		item.setItemStatus(ItemStatus.BINDERY);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsAvailable(Item item) {
		item.setItemStatus(ItemStatus.AVAILABLE);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsReturned(Item item) {
		item.setItemStatus(ItemStatus._RETURNED);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

	private void ItemStatusIsPotentiallyLate(Item item)
			throws ItemStatusNotAllowedForCheckInException {
		item.setItemStatus(ItemStatus._POTENTIALLY_LATE);
		expectToThrowExceptionBecauseInvalidStatus(item);
	}

}
