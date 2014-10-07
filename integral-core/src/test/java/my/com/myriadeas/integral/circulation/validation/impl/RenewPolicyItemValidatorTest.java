package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RenewPolicyItemValidatorTest {

	RenewPolicyItemValidatorImpl renewPolicyItemValidator;
	Item item;
	
	@Before
	public void setUp() {
		renewPolicyItemValidator = new RenewPolicyItemValidatorImpl();
		item = new Item();
		
	}

	@After
	public void tearDown() {
		renewPolicyItemValidator = null;
		item = null;
	}
	
	@Test
	public void testvalidateIsAccessionStatusValid() {

		try {
			item.setItemStatus(ItemStatus._RETURNED);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RETURNED.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus._POTENTIALLY_LATE);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is POTENTIALLY_LATE.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.AVAILABLE);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is AVAILABLE.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.BINDERY);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is BINDERY.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.CIRCULATED);
			renewPolicyItemValidator.validateItemStatus(item);

		} catch (InvalidItemStatusException e) {
			fail("Should not throw exception because accession status is CIRCULATED.");
		}

		try {
			item.setItemStatus(ItemStatus.FINAL_PROCESSING);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FINAL_PROCESSING.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.FOUND);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FOUND.");
		} catch (InvalidItemStatusException e) {
		
		}

		try {
			item.setItemStatus(ItemStatus.HOLD);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is HOLD.");
		} catch (InvalidItemStatusException e) {
		}

		try {
			item.setItemStatus(ItemStatus.LOST);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is LOST.");
		} catch (InvalidItemStatusException e) {
		}

		try {
			item.setItemStatus(ItemStatus.MISPLACED);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISPLACED.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.MISSING);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISSING.");
		} catch (InvalidItemStatusException e) {

		}

		try {
			item.setItemStatus(ItemStatus.RECALLED);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RECALLED.");		
		} catch (InvalidItemStatusException e) {
			
		}

		try {			
			item.setItemStatus(ItemStatus.SUSPENDED);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is SUSPENDED.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			item.setItemStatus(ItemStatus.WEED);
			renewPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is WEED.");
		} catch (InvalidItemStatusException e) {

		}
	}
}