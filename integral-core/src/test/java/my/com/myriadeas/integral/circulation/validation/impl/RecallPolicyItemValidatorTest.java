package my.com.myriadeas.integral.circulation.validation.impl;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecallPolicyItemValidatorTest {

	RecallPolicyItemValidatorImpl recallPolicyItemValidator; 
	Item item;
	
	@Before
	public void setUp() {
		recallPolicyItemValidator = new RecallPolicyItemValidatorImpl();
		item = new Item();
		
	}

	@After
	public void tearDown() {
		recallPolicyItemValidator = null;
		item = null;
	}
	
	@Test
	public void testValidateAccessionStatus() {


		try {
			item.setItemStatus(ItemStatus._RETURNED);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RETURNED.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
		
			item.setItemStatus(ItemStatus._POTENTIALLY_LATE);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is POTENTIALLY_LATE.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			
			item.setItemStatus(ItemStatus.AVAILABLE);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is AVAILABLE.");
		} catch (InvalidItemStatusException e) {
			
		}

		try {
			
			item.setItemStatus(ItemStatus.BINDERY);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is BINDERY.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
			
			item.setItemStatus(ItemStatus.CIRCULATED);		
			recallPolicyItemValidator.validateItemStatus(item);
			
		} catch (InvalidItemStatusException e) {
			fail("Should not throw exception because accession status is CIRCULATED.");
		}
		
		try {
			
			item.setItemStatus(ItemStatus.FINAL_PROCESSING);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FINAL_PROCESSING.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
			
			item.setItemStatus(ItemStatus.FOUND);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is FOUND.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
		
			item.setItemStatus(ItemStatus.HOLD);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is HOLD.");
		} catch (InvalidItemStatusException e) {
		
		}
		
		try {
	
			item.setItemStatus(ItemStatus.LOST);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is LOST.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
		
			item.setItemStatus(ItemStatus.MISPLACED);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISPLACED.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
			
			item.setItemStatus(ItemStatus.MISSING);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is MISSING.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
		
			item.setItemStatus(ItemStatus.RECALLED);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is RECALLED.");
		} catch (InvalidItemStatusException e) {
			
		}
		
		try {
		
			item.setItemStatus(ItemStatus.SUSPENDED);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is SUSPENDED.");
		} catch (InvalidItemStatusException e) {
		}
		
		try {
		
			item.setItemStatus(ItemStatus.WEED);		
			recallPolicyItemValidator.validateItemStatus(item);
			fail("Should throw exception because accession status is WEED.");
		} catch (InvalidItemStatusException e) {
			
		}
	}
}
