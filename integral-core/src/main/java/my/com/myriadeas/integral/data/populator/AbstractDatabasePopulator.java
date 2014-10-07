package my.com.myriadeas.integral.data.populator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import my.com.myriadeas.integral.data.JpaUtils;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDatabasePopulator implements
		DatabaseInitializingBean {

	@Autowired
	private JpaUtils jpaUtilsImpl;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (jpaUtilsImpl.isDatabaseEmpty()) {
			//SecurityUtils.authenticateAsSystemUser();
			init();
			//SecurityUtils.logoutSystemUser();
		}
	}

	/**
	 * Return date with ddMMyyyy format. If format is wrong, return current date
	 * 
	 * @param ddMMyyyy
	 * @return
	 */
	public Date getStandardDate(String ddMMyyyy) {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		try {
			return formatter.parse(ddMMyyyy);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public Date getCurrentDatePlusDays(int noOfDays) {
		DateTime dt = new DateTime();
		dt.plusDays(noOfDays);
		return dt.toDate();
	}
	
	public Date getCurrentDateMinusDays(int noOfDays) {
		DateTime dt = new DateTime();
		dt.minusDays(noOfDays);
		return dt.toDate();
	}

	@Override
	public abstract void init();
}
