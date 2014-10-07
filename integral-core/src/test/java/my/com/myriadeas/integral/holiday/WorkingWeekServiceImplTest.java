package my.com.myriadeas.integral.holiday;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;
import my.com.myriadeas.integral.holiday.FullWorkingWeek;
import my.com.myriadeas.integral.holiday.WorkingWeekServiceImpl;
import net.objectlab.kit.datecalc.common.WorkingWeek;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

import org.junit.Test;

public class WorkingWeekServiceImplTest {

	@Test
	public void testGetWorkingWeek() {
		WorkingWeekServiceImpl workingWeekService = new WorkingWeekServiceImpl();
		JodaWorkingWeek workingWeek = new FullWorkingWeek();
		WorkingDay workingday = new WorkingDay();
		workingday.setDayOfWeek(Calendar.SUNDAY);
		workingday.setWorking(false);
		JodaWorkingWeek actual = workingWeekService.populateWorkingWeek(
				workingWeek, workingday);
		
		
		
		JodaWorkingWeek expected = new FullWorkingWeek()
				.withWorkingDayFromDateTimeConstant(false, Calendar.SUNDAY);
		assertEquals("Sunday is not working day", expected.getWorkingDays(),
				actual.getWorkingDays());
	}

	@Test
	public void testSetupWorkingWeeks() {
		WorkingWeekServiceImpl workingWeekService = new WorkingWeekServiceImpl();
		List<WorkingDay> workingdays = new ArrayList<WorkingDay>();
		WorkingDay workingday = new WorkingDay();
		Branch gl80brnc = new Branch();
		workingday.setBranch(gl80brnc);
		workingday.setDayOfWeek(Calendar.SUNDAY);
		workingday.setWorking(false);
		workingdays.add(workingday);
		workingday = new WorkingDay();
		workingday.setBranch(gl80brnc);
		workingday.setDayOfWeek(Calendar.SATURDAY);
		workingday.setWorking(false);
		workingdays.add(workingday);
		workingWeekService.setupWorkingWeeks(workingdays);
		JodaWorkingWeek actual = workingWeekService.getWorkingWeek(gl80brnc);
		JodaWorkingWeek expected = new FullWorkingWeek()
				.withWorkingDayFromDateTimeConstant(false, Calendar.SUNDAY)
				.withWorkingDayFromDateTimeConstant(false, Calendar.SATURDAY);
		assertEquals("Sunday is not working day", expected.getWorkingDays(),
				actual.getWorkingDays());
						
	}

}
