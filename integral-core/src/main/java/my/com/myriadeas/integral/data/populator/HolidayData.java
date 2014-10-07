package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Holiday;

public interface HolidayData {
	Holiday NEW_YEAR = new Holiday("NY", "New Year", 1, 1, 1, 1);
	Holiday CHINESE_NEW_YEAR = new Holiday("CNY", "Chinese New Year", 5, 6, 1, 1);
	
}
