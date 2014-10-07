package my.com.myriadeas.integral.holiday;

import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.InitializingBean;

public interface HolidayService<E> extends InitializingBean {

	/**
	 *
	 * @return Map of set of holidays
	 * @throws EmptyHolidayException - if holidays is empty
	 */
	public Map<E, Set<LocalDate>> getHolidays() throws EmptyHolidayException;

	/**
	 * Retrieve holiday by entity
	 * @param identity
	 * @return Set of holidays
	 * @throws HolidaysNotFoundException - if Holidays not found
	 */
	public Set<LocalDate> getHolidays(E identity) throws HolidaysNotFoundException, EmptyHolidayException;

	/**
	 * Set custom date rule for holiday initialization
	 * @param dateRule
	 */
	public void setDateRule(DateRule dateRule);
	
	public void setEarlyBoundary(LocalDate earlyBoundary);

	//public void setLateBoundary(LocalDate lateBoundary);
	
	public LocalDate getEarlyBoundary();

	public LocalDate getLateBoundary();

}
