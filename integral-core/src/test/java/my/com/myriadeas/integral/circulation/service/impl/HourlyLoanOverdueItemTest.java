package my.com.myriadeas.integral.circulation.service.impl;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.circulation.AbstractCirculationTest;
import my.com.myriadeas.integral.circulation.service.impl.HourlyLoanOverdueItem;
import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;

import org.easymock.IMockBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.junit.Test;

public class HourlyLoanOverdueItemTest extends AbstractCirculationTest {
	private HourlyLoanOverdueItem hourlyLoanOverdueItem = new HourlyLoanOverdueItem();

	@Test
	public void testCalculateLateBy_CheckInSameDay() {
		IMockBuilder<HourlyLoanOverdueItem> hourlyLoanOverdueItemMock = createMockBuilder(HourlyLoanOverdueItem.class);
		hourlyLoanOverdueItemMock.addMockedMethod("getDueDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("getCheckInDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("isNonWorkingDay");
		hourlyLoanOverdueItemMock.addMockedMethod("getTotalBusinessHourUntilGivenTime");
		hourlyLoanOverdueItem = hourlyLoanOverdueItemMock.createMock();
			
		WorkingDay glworkingday = new WorkingDay();
		glworkingday.setWorkingHours("09:00-13:30&14:30-18:00");
		
		List<LocalTime[]> slots = new ArrayList<LocalTime[]>();
		LocalTime[] slot = new LocalTime[] {new LocalTime(9, 0), new LocalTime(13, 30)};
		slots.add(slot);
		slot = new LocalTime[] {new LocalTime(14, 30), new LocalTime(18, 00)};
		slots.add(slot);
		
		Date dueDateTime = getDatetime("01/01/2001 09:00:31");
		Date checkInDateTime = getDatetime("01/01/2001 10:00:31");
		
		expect(hourlyLoanOverdueItem.getDueDateTime()).andReturn(dueDateTime).times(1);
		expect(hourlyLoanOverdueItem.getCheckInDateTime()).andReturn(checkInDateTime).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("01/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("01/01/2001 10:00:31")))).andReturn(1).times(1);		
		replay(hourlyLoanOverdueItem);
		
		int expectedLateBy = 1;
		int returnedLateBy = hourlyLoanOverdueItem.calculateLateBy();
		assertEquals(expectedLateBy, returnedLateBy);
		
		
	}
	
	@Test
	public void testCalculateLateBy_CheckInOneDayAfterDue() {
		IMockBuilder<HourlyLoanOverdueItem> hourlyLoanOverdueItemMock = createMockBuilder(HourlyLoanOverdueItem.class);
		hourlyLoanOverdueItemMock.addMockedMethod("getDueDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("getCheckInDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("isNonWorkingDay");
		hourlyLoanOverdueItemMock.addMockedMethod("getTotalBusinessHourUntilGivenTime");
		hourlyLoanOverdueItem = hourlyLoanOverdueItemMock.createMock();
			
		WorkingDay glworkingday = new WorkingDay();
		glworkingday.setWorkingHours("09:00-13:30&14:30-18:00");
		
		List<LocalTime[]> slots = new ArrayList<LocalTime[]>();
		LocalTime[] slot = new LocalTime[] {new LocalTime(9, 0), new LocalTime(13, 30)};
		slots.add(slot);
		slot = new LocalTime[] {new LocalTime(14, 30), new LocalTime(18, 00)};
		slots.add(slot);
		
		Date dueDateTime = getDatetime("01/01/2001 09:00:31");
		Date checkInDateTime = getDatetime("02/01/2001 10:00:31");
		
		expect(hourlyLoanOverdueItem.getDueDateTime()).andReturn(dueDateTime).times(1);
		expect(hourlyLoanOverdueItem.getCheckInDateTime()).andReturn(checkInDateTime).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("01/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("02/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("01/01/2001 23:59:59")))).andReturn(10).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("02/01/2001 10:00:31")))).andReturn(5).times(1);
		
		replay(hourlyLoanOverdueItem);
		
		int expectedLateBy = 15;
		int returnedLateBy = hourlyLoanOverdueItem.calculateLateBy();
		assertEquals(expectedLateBy, returnedLateBy);
		
		
	}
	
	@Test
	public void testCalculateLateBy_CheckInTwoDayAfterDue() {
		IMockBuilder<HourlyLoanOverdueItem> hourlyLoanOverdueItemMock = createMockBuilder(HourlyLoanOverdueItem.class);
		hourlyLoanOverdueItemMock.addMockedMethod("getDueDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("getCheckInDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("isNonWorkingDay");
		hourlyLoanOverdueItemMock.addMockedMethod("getTotalBusinessHourUntilGivenTime");
		hourlyLoanOverdueItem = hourlyLoanOverdueItemMock.createMock();
			
		WorkingDay glworkingday = new WorkingDay();
		glworkingday.setWorkingHours("09:00-13:30&14:30-18:00");
		
		List<LocalTime[]> slots = new ArrayList<LocalTime[]>();
		LocalTime[] slot = new LocalTime[] {new LocalTime(9, 0), new LocalTime(13, 30)};
		slots.add(slot);
		slot = new LocalTime[] {new LocalTime(14, 30), new LocalTime(18, 00)};
		slots.add(slot);
		
		Date dueDateTime = getDatetime("01/01/2001 09:00:31");
		Date checkInDateTime = getDatetime("03/01/2001 10:00:31");
		
		expect(hourlyLoanOverdueItem.getDueDateTime()).andReturn(dueDateTime).times(1);
		expect(hourlyLoanOverdueItem.getCheckInDateTime()).andReturn(checkInDateTime).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("01/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("02/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.isNonWorkingDay(new DateTime(getDatetime("03/01/2001 00:00:00")))).andReturn(false).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("01/01/2001 23:59:59")))).andReturn(10).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("02/01/2001 23:59:59")))).andReturn(10).times(1);
		expect(hourlyLoanOverdueItem.getTotalBusinessHourUntilGivenTime(new DateTime(getDatetime("03/01/2001 10:00:31")))).andReturn(5).times(1);
		
		replay(hourlyLoanOverdueItem);
		
		int expectedLateBy = 25;
		int returnedLateBy = hourlyLoanOverdueItem.calculateLateBy();
		assertEquals(expectedLateBy, returnedLateBy);
		
		
	}
	
	
	
	@Test
	public void test(){
		DateTime dueDate = new DateTime(getDatetime("01/01/2001 09:00:31"));
		DateTime returnDate = new DateTime(getDatetime("08/01/2001 09:00:31"));
		int minutes = 0;
		while(dueDate.isBefore(returnDate)){
			dueDate = dueDate.plusMinutes(1);
			if(isInBusinessHour(dueDate)) {
				minutes++;
			}
		}
		System.out.println(dueDate);
		System.out.println(minutes);
		LocalTime localTime = new LocalTime(getDatetime("01/01/2001 09:00:31"));
		LocalTime localTime2 = new LocalTime(getDatetime("01/01/2001 09:00:31"));
		System.out.println(localTime.isEqual(localTime2));
	}
	
	public boolean isInBusinessHour(DateTime time) {
		return true;
	}
	
	public List<LocalTime> getBusinessHours() {
		List<LocalTime> businessHours = new ArrayList<LocalTime>();
		LocalTime localTime = new LocalTime(getDatetime("01/01/2001 09:00:00"));
		LocalTime localTime2 = new LocalTime(getDatetime("01/01/2001 18:00:00"));
		businessHours.add(localTime);
		businessHours.add(localTime2);
		return businessHours;
	}
	
	/*
	@Test
	public void testCalculateLateBy() {
		List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
		BusinessHour businessHour = new BusinessHour(new LocalTime(9, 0), new LocalTime(13, 30));
		businessHours.add(businessHour);
		businessHour = new BusinessHour(new LocalTime(14, 30), new LocalTime(18, 00));
		businessHours.add(businessHour);
				
		DateTime time = new DateTime(getDatetime("02/01/2001 09:00:31"));
		int expectedLateBy = 0;
		int returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);		
		
		time = new DateTime(getDatetime("02/01/2001 11:30:31"));
		expectedLateBy = 150;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 13:30:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 13:35:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 14:30:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 14:31:31"));
		expectedLateBy = 270 + 1;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 17:59:31"));
		expectedLateBy = 270 + 209;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 18:00:31"));
		expectedLateBy = 270 + 210;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new DateTime(getDatetime("02/01/2001 18:01:31"));
		expectedLateBy = 270 + 210;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateBy(businessHours, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
	}
	
	
	
	@Test
	public void testCalculateLateByUsingSlots() {
		List<LocalTime[]> slots = new ArrayList<LocalTime[]>();
		LocalTime[] slot = new LocalTime[] {new LocalTime(9, 0), new LocalTime(13, 30)};
		slots.add(slot);
		slot = new LocalTime[] {new LocalTime(14, 30), new LocalTime(18, 00)};
		slots.add(slot);
		
		LocalTime time = new LocalTime(getDatetime("02/01/2001 09:00:31"));
		int expectedLateBy = 0;
		int returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);		
		
		time = new LocalTime(getDatetime("02/01/2001 11:30:31"));
		expectedLateBy = 150;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 13:30:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 13:35:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 14:30:31"));
		expectedLateBy = 270;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 14:31:31"));
		expectedLateBy = 270 + 1;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 17:59:31"));
		expectedLateBy = 270 + 209;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 18:00:31"));
		expectedLateBy = 270 + 210;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
		time = new LocalTime(getDatetime("02/01/2001 18:01:31"));
		expectedLateBy = 270 + 210;
		returnedLateBy = hourlyLoanOverdueItem.calculateLateByUsingSlots(slots, time);
		assertEquals(expectedLateBy, returnedLateBy);
		
	}
	
	
	@Test
	public void testIsOverdueOvernight(){
		IMockBuilder<HourlyLoanOverdueItem> hourlyLoanOverdueItemMock = createMockBuilder(HourlyLoanOverdueItem.class);
		hourlyLoanOverdueItemMock.addMockedMethod("getDueDateTime");
		hourlyLoanOverdueItemMock.addMockedMethod("getCheckInDateTime");
		hourlyLoanOverdueItem = hourlyLoanOverdueItemMock.createMock();
		
		Date dueDateTime = getDatetime("01/01/2001 09:00:31");
		Date checkInDateTime = getDatetime("02/01/2001 10:00:31");
				
		expect(hourlyLoanOverdueItem.getDueDateTime()).andReturn(dueDateTime).times(1);		
		expect(hourlyLoanOverdueItem.getCheckInDateTime()).andReturn(checkInDateTime).times(1);
		
		checkInDateTime = getDatetime("01/01/2001 10:00:31");		
		expect(hourlyLoanOverdueItem.getDueDateTime()).andReturn(dueDateTime).times(1);		
		expect(hourlyLoanOverdueItem.getCheckInDateTime()).andReturn(checkInDateTime).times(1);
		
		replay(hourlyLoanOverdueItem);
		
		assertEquals(true, hourlyLoanOverdueItem.isOverdueOvernight());
		assertEquals(false, hourlyLoanOverdueItem.isOverdueOvernight());
		
	}
	
	*/
}
