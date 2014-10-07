package my.com.myriadeas.integral.holiday;

import java.util.Map;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import net.objectlab.kit.datecalc.common.DateCalculator;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.InitializingBean;

public interface LibraryCalendarService<E> extends InitializingBean {

	public Map<Branch, DateCalculator<LocalDate>> getDateCalculator();

	/**
	 * Retrieve {@link DateCalculator} based on {@link E} identity. 
	 * @param identity
	 * @return 
	 */
	public BusinessCalendarCalculator getDateCalculator(E identity);
	
	public BusinessCalendarCalculator getDateCalculator(Branch brnch,
			LocalDate startDate) throws DateCalculatorNotFoundException;
	
	/**
	 * Implement holiday service in order to setup holidays for library calendar
	 * @param holidayService
	 */
	public void setHolidayService(HolidayService<E> holidayService);

	/**
	 * Implement working week service in order to setup working week for library calendar
	 * @param workingWeekService
	 */
	public void setWorkingWeekService(WorkingWeekService<E> workingWeekService);
		
}
