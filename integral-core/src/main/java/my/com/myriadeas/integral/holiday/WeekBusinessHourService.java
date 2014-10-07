package my.com.myriadeas.integral.holiday;

import java.util.Map;

import my.com.myriadeas.WeekBusinessHour;

import org.springframework.beans.factory.InitializingBean;

public interface WeekBusinessHourService<E> extends InitializingBean {

	public Map<E, WeekBusinessHour> getWeekBusinessHour()
			throws WeekBusinessHourEmptyException;

	public WeekBusinessHour getWeekBusinessHour(E identity)
			throws WeekBusinessHourNotFoundException;

	public void setDefaultWeekBusinessHour(WeekBusinessHour WeekBusinessHour);
}
