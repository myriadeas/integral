package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservePolicyItemValidatorTest {

	ReservePolicyItemValidatorImpl reservePolicyItemValidator;	
	Item item;
	
	@Before
	public void setUp() {
		reservePolicyItemValidator = new ReservePolicyItemValidatorImpl();
		item = new Item();
	}

	@After
	public void tearDown() {
		reservePolicyItemValidator = null;
		item = null;
	}
	
	@Test
	public void testValidateAccessionStatus() {
		

		try {
			item.setItemStatus(ItemStatus._RETURNED);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RETURNED.");
		} catch (InvalidItemStatusException e) {
		
		}

		try {
			item.setItemStatus(ItemStatus._POTENTIALLY_LATE);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is POTENTIALLY_LATE.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.AVAILABLE);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is AVAILABLE.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.BINDERY);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is BINDERY.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.CIRCULATED);
			reservePolicyItemValidator.validateItemStatus(item);
		} catch (InvalidItemStatusException e) {
			fail("Should not throw exception because accession status is CIRCULATED.");
		}

		try {
			item.setItemStatus(ItemStatus.FINAL_PROCESSING);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FINAL_PROCESSING.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.FOUND);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FOUND.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.HOLD);
			reservePolicyItemValidator.validateItemStatus(item);
		
		} catch (InvalidItemStatusException e) {
			fail("Should not throw exception because accession status is HOLD.");
		}

		try {
			item.setItemStatus(ItemStatus.LOST);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is LOST.");
		} catch (InvalidItemStatusException e) {
		}

		try {
			item.setItemStatus(ItemStatus.MISPLACED);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISPLACED.");
		} catch (InvalidItemStatusException e) {
	
		}

		try {
			item.setItemStatus(ItemStatus.MISSING);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISSING.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.RECALLED);
			reservePolicyItemValidator.validateItemStatus(item);			
		} catch (InvalidItemStatusException e) {
			fail("Should not throw exception because accession status is RECALLED.");	
		}

		try {
			item.setItemStatus(ItemStatus.SUSPENDED);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is SUSPENDED.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.WEED);
			reservePolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is WEED.");
		} catch (InvalidItemStatusException e) {

		}
	
	}

}
