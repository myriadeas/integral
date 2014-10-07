package my.com.myriadeas.integral.data.jpa.domain;


import static org.junit.Assert.assertEquals;

import java.util.Date;

import my.com.myriadeas.integral.circulation.AbstractCirculationTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest extends AbstractCirculationTest {
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testMarker() {

	}

	@Test
	public void testPopulateCheckOutItem() {
		String patronId = "lastborrower";
		
		Patron patron = new Patron();
		patron.setUsername(patronId);
		Date checkOutDateTime = getDate("01/01/2014");
		Date dueDateTime = getDate("01/02/2014");
		
		Item expectedItem = new Item();		
		expectedItem.setChargeDateTime(checkOutDateTime);
		expectedItem.setDueDateTime(dueDateTime);
		expectedItem.setItemStatus(ItemStatus.CIRCULATED);
		expectedItem.setPatron(patron);
		
		Item item = new Item();		
		item.setItemStatus(ItemStatus.AVAILABLE);
		item.populateCheckOutItem(checkOutDateTime, dueDateTime, patron);		
		assertEquals(expectedItem, item);
	}
	
	@Test
	public void testPopulateCheckInItem(){
		
		Patron patron = new Patron();
		patron.setUsername("lastborrower");
		Date dueDateTime = getDate("01/02/2014");
		Date checkInDateTime = getDate("01/01/2014");
		Date chargeDateTime = getDate("01/12/2013");
		Item expected = new Item();
		expected.setChargeDateTime(chargeDateTime);
		expected.setItemStatus(ItemStatus._RETURNED);
		expected.setDueDateTime(dueDateTime);
		expected.setPatron(patron);
		
		
		Item item = new Item();
		item.setChargeDateTime(chargeDateTime);
		item.setDueDateTime(dueDateTime);
		item.setPatron(patron);
		item.setItemStatus(ItemStatus.CIRCULATED);
		item.populateCheckInItem();		
		assertEquals(expected, item);
	}
	
	
	@Test
	public void testPopulateRenewItem(){
		String patronId = "PATRON";
		Patron patron = new Patron();
		patron.setUsername(patronId);
		Date dueDateTime = getDate("01/02/2014");
		Date renewDateTime = getDate("01/02/2014");		
				
		Item expectedItem = new Item();
		expectedItem.setDueDateTime(dueDateTime);
		expectedItem.setPatron(patron);
		expectedItem.setItemStatus(ItemStatus.RENEW);
		
		Item item = new Item();
		item.setPatron(patron);		
		item.populateRenewItem(dueDateTime);		
		assertEquals(expectedItem, item);
	}
	
	@Test
	public void testPopulateReleaseItem(){
	
		Item item = new Item();		
		
		Item expectedItem = new Item();
		expectedItem.setItemStatus(ItemStatus._RETURNED);
		
		Officer officer = new Officer();
		
		item.populateReleaseItem(officer, new Date());
		assertEquals(expectedItem, item);
	}
}
