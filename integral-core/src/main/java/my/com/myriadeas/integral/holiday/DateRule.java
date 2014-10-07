package my.com.myriadeas.integral.holiday;

import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

public interface DateRule {

	public Set<LocalDate> getHoliday();
	
	public Map<Integer, Boolean> getWorkingDayOfWeek();
}
