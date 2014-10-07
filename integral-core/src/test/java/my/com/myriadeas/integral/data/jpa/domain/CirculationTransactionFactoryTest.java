package my.com.myriadeas.integral.data.jpa.domain;


import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import my.com.myriadeas.integral.circulation.AbstractCirculationTest;
import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;

import org.easymock.IMockBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CirculationTransactionFactoryTest extends AbstractCirculationTest {
	
	
	private CirculationTransactionFactory circulationTransactionFactory = new CirculationTransactionFactory();
	
	@Before
	public void setup() {
				
	}
	
	@After
	public void tearDown() {
		circulationTransactionFactory = null;
	}
	
	@Test
	public void testMarker() {

	}
	
	@Test
	public void testPopulateCheckOutCirculationTransaction() {
		String patronId = "lastborrower";
		Material material = new Material();
		
		Patron patron = new Patron();
		patron.setUsername(patronId);
		Date chargeDateTime = getDate("01/01/2014");
		Date dueDateTime = getDate("01/02/2014");
		Officer officer = new Officer();
		officer.setUsername("OFFICER");
		
		Item item = new Item();
		item.setMaterial(material);
		item.setPatron(patron);
		item.setCirculationOfficer(officer);
		item.setChargeDateTime(chargeDateTime);
		item.setDueDateTime(dueDateTime);
		
		CirculationTransaction expectedCirculationTransaction = new CirculationTransaction();		
		expectedCirculationTransaction.setPatron(patron);
		expectedCirculationTransaction.setItem(item);
		expectedCirculationTransaction.setDueDateTime(dueDateTime);
		expectedCirculationTransaction.setChargeDateTime(chargeDateTime);
		expectedCirculationTransaction.setChargeOfficer(officer);
		expectedCirculationTransaction.setFlag(CirculationTransactionFlag.CHARGED);
		expectedCirculationTransaction.setNoOfRenew(0);
				
		CirculationTransaction circulationTransaction = circulationTransactionFactory.populateCheckOutCirculationTransaction(item);
		assertEquals(expectedCirculationTransaction, circulationTransaction);
		
	}
	
	@Test
	public void testPopulateCheckInCirculationTransaction() {
		String patronId = "lastborrower";
		Patron patron = new Patron();
		patron.setUsername(patronId);
		Date checkInDateTime = getDate("01/01/2014");
		Officer officer = new Officer();
		officer.setUsername("OFFICER");
		
		CirculationTransaction expectedCicirc = new CirculationTransaction();	
		expectedCicirc.setFlag(CirculationTransactionFlag.DISCHARGED);
		expectedCicirc.setDischargeDateTime(checkInDateTime);
		expectedCicirc.setDischargeOfficer(officer);
				
		IMockBuilder<Item> itemMock = createMockBuilder(Item.class);
		itemMock.addMockedMethod("getCirculationTransactionWithFlagCharged");
		Item item = itemMock.createMock();
		item.setPatron(patron);
		item.setCirculationOfficer(officer);
		item.setDischargeDateTime(checkInDateTime);
				
		expect(item.getCirculationTransactionWithFlagCharged()).andReturn(
				new CirculationTransaction()).times(1);
		replay(item);		
		
		CirculationTransaction circulationTransaction = circulationTransactionFactory.populateCheckInCirculationTransaction(item);
		assertEquals(expectedCicirc, circulationTransaction);
	}
	
	
	@Test
	public void testPopulateRenewCirculationTransaction() {
		Date renewDateTime = getDate("01/02/2014");
		Officer officer = new Officer();
		officer.setUsername("OFFICER");
		
		CirculationTransaction expectedCicirc = new CirculationTransaction();	
		expectedCicirc.setFlag(CirculationTransactionFlag.DISCHARGED);
		expectedCicirc.setDischargeDateTime(renewDateTime);
		expectedCicirc.setDischargeOfficer(officer);
		
		
		IMockBuilder<Item> itemMock = createMockBuilder(Item.class);
		itemMock.addMockedMethod("getCirculationTransactionWithFlagCharged");
		Item item = itemMock.createMock();
		item.setCirculationOfficer(officer);
		item.setRenewDateTime(renewDateTime);
		
		expect(item.getCirculationTransactionWithFlagCharged()).andReturn(
				new CirculationTransaction()).times(1);
		replay(item);
		
		CirculationTransaction circulationTransaction = circulationTransactionFactory.populateRenewCirculationTransaction(item);
		assertEquals(expectedCicirc, circulationTransaction);
		
		
	}
	
	@Test
	public void testPopulateRenewNewCirculationTransaction() {
		String patronId = "lastborrower";		
		Patron patron = new Patron();
		patron.setUsername(patronId);
		Date checkOutDateTime = getDate("01/01/2014");
		Date dueDateTime = getDate("01/02/2014");
		Date renewDateTime = getDate("15/01/2014");
		Officer officer = new Officer();
		officer.setUsername("OFFICER");
		CirculationTransaction parentCirculationTransaction = new CirculationTransaction();
		
		Item item = new Item();
		item.setPatron(patron);
		item.setCirculationOfficer(officer);
		item.setRenewDateTime(renewDateTime);
		item.setChargeDateTime(checkOutDateTime);
		item.setDueDateTime(dueDateTime);
		
		CirculationTransaction expectedCirculationTransaction = new CirculationTransaction();		
		expectedCirculationTransaction.setPatron(patron);
		expectedCirculationTransaction.setItem(item);
		expectedCirculationTransaction.setDueDateTime(dueDateTime);
		expectedCirculationTransaction.setChargeDateTime(renewDateTime);
		expectedCirculationTransaction.setChargeOfficer(officer);
		expectedCirculationTransaction.setFlag(CirculationTransactionFlag.CHARGED);
		expectedCirculationTransaction.setNoOfRenew(1);
		expectedCirculationTransaction.setCirculationTransactionParentForRenew(parentCirculationTransaction);
		
		
		
		setNullForParentCirculationTransactionAndNoOfRenewal(parentCirculationTransaction, expectedCirculationTransaction);		
		CirculationTransaction returnedCirculationTransaction = circulationTransactionFactory.populateRenewNewCirculationTransaction(item, parentCirculationTransaction);
		assertEquals(expectedCirculationTransaction, returnedCirculationTransaction);
		
		setZeroForParentCirculationTransactionAndNoOfRenewal(parentCirculationTransaction, expectedCirculationTransaction);		
		returnedCirculationTransaction = circulationTransactionFactory.populateRenewNewCirculationTransaction(item, parentCirculationTransaction);
		assertEquals(expectedCirculationTransaction, returnedCirculationTransaction);
		
		setOneForParentCirculationTransactionAndNoOfRenewal(parentCirculationTransaction, expectedCirculationTransaction);		
		returnedCirculationTransaction = circulationTransactionFactory.populateRenewNewCirculationTransaction(item, parentCirculationTransaction);
		assertEquals(expectedCirculationTransaction, returnedCirculationTransaction);
		
	}

	private void setNullForParentCirculationTransactionAndNoOfRenewal(CirculationTransaction parentCirculationTransaction,
			CirculationTransaction expectedCirculationTransaction) {
		parentCirculationTransaction.setNoOfRenew(null);
		expectedCirculationTransaction.setNoOfRenew(1);
		
	}
	
	private void setZeroForParentCirculationTransactionAndNoOfRenewal(CirculationTransaction parentCirculationTransaction,
			CirculationTransaction expectedCirculationTransaction) {
		parentCirculationTransaction.setNoOfRenew(0);
		expectedCirculationTransaction.setNoOfRenew(1);
		
	}
	
	private void setOneForParentCirculationTransactionAndNoOfRenewal(CirculationTransaction parentCirculationTransaction,
			CirculationTransaction expectedCirculationTransaction) {
		parentCirculationTransaction.setNoOfRenew(1);
		expectedCirculationTransaction.setNoOfRenew(2);
		
	}


}
