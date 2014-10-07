package my.com.myriadeas.integral.holiday;

import my.com.myriadeas.BusinessHour;
import my.com.myriadeas.WeekBusinessHour;

import org.joda.time.DateTimeConstants;

public class DefaultWeekBusinessHour extends WeekBusinessHour{
	
	public DefaultWeekBusinessHour(){		
		String businessHourSlot = "00:00-23:59";
		BusinessHour businessHour = new BusinessHour(businessHourSlot);
		
		this.addBusinessHour(DateTimeConstants.MONDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.TUESDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.WEDNESDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.THURSDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.FRIDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.SATURDAY, businessHour);
		this.addBusinessHour(DateTimeConstants.SUNDAY, businessHour);
	}
	
}
