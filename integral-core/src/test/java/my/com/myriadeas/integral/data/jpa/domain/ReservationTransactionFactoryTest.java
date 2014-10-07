package my.com.myriadeas.integral.data.jpa.domain;


import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import my.com.myriadeas.integral.circulation.AbstractCirculationTest;
import my.com.myriadeas.integral.circulation.ReservationStatus;

import org.easymock.IMockBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservationTransactionFactoryTest extends AbstractCirculationTest {
	
	
	private ReservationTransactionFactory reservationTransactionFactory = new ReservationTransactionFactory();
	
	@Before
	public void setup() {
				
	}
	
	@After
	public void tearDown() {
		reservationTransactionFactory = null;
	}
	
	@Test
	public void testMarker() {

	}
	
	
	@Test
	public void testPopulateReservationTransactionByItem() {
		Patron patron = new Patron();
		Officer officer = new Officer();
		Material material = new Material();
		
		Branch pickUpBranch = new Branch();
		Date reserveDateTime = getDate("01/01/2014");

		Item item = new Item();
		
		ReservationTransaction expectedReservationTransaction = new ReservationTransaction();
		expectedReservationTransaction.setPatron(patron);
		expectedReservationTransaction.setStatus(ReservationStatus.RESERVE);
		expectedReservationTransaction.setMaterial(material);
		expectedReservationTransaction.setItem(item);
		expectedReservationTransaction.setBranch(pickUpBranch);
		expectedReservationTransaction.setReserveDateTime(reserveDateTime);
		expectedReservationTransaction.setOfficer(officer);

		IMockBuilder<ReservationTransactionFactory> reservationTransactionFactoryMock = createMockBuilder(ReservationTransactionFactory.class);
		reservationTransactionFactoryMock.addMockedMethod("getPriority");
		reservationTransactionFactory = reservationTransactionFactoryMock.createMock();

		item.setMaterial(material);
		item.setReserver(patron);
		item.setReserveDateTime(reserveDateTime);
		item.setReserverPickUpBranch(pickUpBranch);
		item.setCirculationOfficer(officer);
		
		expect(reservationTransactionFactory.getPriority(material)).andReturn(1000).times(1);
		expect(reservationTransactionFactory.getPriority(material)).andReturn(2000).times(1);
		replay(reservationTransactionFactory);

		setNoReservationInList(expectedReservationTransaction);
		ReservationTransaction returnedReservationTransaction = reservationTransactionFactory.populateReservationTransaction(item);
		assertEquals(expectedReservationTransaction, returnedReservationTransaction);

		setOneReservationInList(expectedReservationTransaction);
		returnedReservationTransaction = reservationTransactionFactory.populateReservationTransaction(item);
		assertEquals(expectedReservationTransaction, returnedReservationTransaction);
	}
	
	private void setNoReservationInList(ReservationTransaction expectedReservationTransaction) {
		expectedReservationTransaction.setPriorityWeight((Integer) 1000);
	}

	private void setOneReservationInList(ReservationTransaction expectedReservationTransaction) {
		expectedReservationTransaction.setPriorityWeight((Integer) 2000);
	}
	
	@Test
	public void testPopulateReservationTransactionByMaterial() {
		Patron patron = new Patron();
		Officer officer = new Officer();
		Material material = new Material();
		Branch pickUpBranch = new Branch();
		Date reserveDateTime = getDate("01/01/2014");

		ReservationTransaction expectedReservationTransaction = new ReservationTransaction();
		expectedReservationTransaction.setPatron(patron);
		expectedReservationTransaction.setStatus(ReservationStatus.RESERVE);
		expectedReservationTransaction.setMaterial(material);
		expectedReservationTransaction.setBranch(pickUpBranch);
		expectedReservationTransaction.setReserveDateTime(reserveDateTime);
		expectedReservationTransaction.setOfficer(officer);

		IMockBuilder<ReservationTransactionFactory> reservationTransactionFactoryMock = createMockBuilder(ReservationTransactionFactory.class);
		reservationTransactionFactoryMock.addMockedMethod("getPriority");
		reservationTransactionFactory = reservationTransactionFactoryMock.createMock();

		material.setReserver(patron);
		material.setReserveDateTime(reserveDateTime);
		material.setReserverPickUpBranch(pickUpBranch);
		material.setReserveOfficer(officer);
		
		expect(reservationTransactionFactory.getPriority(material)).andReturn(1000).times(1);
		expect(reservationTransactionFactory.getPriority(material)).andReturn(2000).times(1);
		replay(reservationTransactionFactory);

		setNoReservationInList(expectedReservationTransaction);
		ReservationTransaction returnedReservationTransaction = reservationTransactionFactory.populateReservationTransaction(material);
		assertEquals(expectedReservationTransaction, returnedReservationTransaction);

		setOneReservationInList(expectedReservationTransaction);
		returnedReservationTransaction = reservationTransactionFactory.populateReservationTransaction(material);
		assertEquals(expectedReservationTransaction, returnedReservationTransaction);
	}
	
	@Test
	public void testGetPriority() {
		IMockBuilder<ReservationTransactionFactory> reservationTransactionFactoryMock = createMockBuilder(ReservationTransactionFactory.class);
		reservationTransactionFactoryMock.addMockedMethod("getMaxWeight");
		reservationTransactionFactory = reservationTransactionFactoryMock.createMock();

		Material ctmatm = new Material();
		expect(reservationTransactionFactory.getMaxWeight(ctmatm)).andReturn(0).times(1);
		expect(reservationTransactionFactory.getMaxWeight(ctmatm)).andReturn(1000).times(1);
		replay(reservationTransactionFactory);

		int expectedPriority = 1000;
		int returnedPriority = reservationTransactionFactory.getPriority(ctmatm);
		assertEquals(expectedPriority, returnedPriority);

		expectedPriority = 2000;
		returnedPriority = reservationTransactionFactory.getPriority(ctmatm);
		assertEquals(expectedPriority, returnedPriority);

	}
}
