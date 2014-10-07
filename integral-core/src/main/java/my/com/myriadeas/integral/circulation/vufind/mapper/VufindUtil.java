package my.com.myriadeas.integral.circulation.vufind.mapper;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class VufindUtil {
	private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormat
			.forPattern("dd/MM/yyyy");
	
	private static final DateTimeFormatter STANDARD_TIME_FORMAT = DateTimeFormat
			.forPattern("HH:mm:ss");

	private static final DateTimeFormatter STANDARD_DATE_FORMAT_WITHTIME = DateTimeFormat
			.forPattern("dd/MM/yyyy HH:mm:ss");

	
	public Date getDate(String input) {
		return STANDARD_DATE_FORMAT.parseDateTime(input).toDate();
	}

	
	public Date getDateTime(String input) {
		return STANDARD_DATE_FORMAT_WITHTIME.parseDateTime(input).toDate();
	}
	
	public String getDateString(Date date){
		return STANDARD_DATE_FORMAT.print(new DateTime(date));
	}
	
	public String getTimeString(Date date){
		return STANDARD_TIME_FORMAT.print(new DateTime(date));
	}
	
	public String getDateTimeString(Date dateTime){
		return STANDARD_DATE_FORMAT_WITHTIME.print(new DateTime(dateTime));
	}
	
	public int convertToPenny(BigDecimal amount){
		return amount.multiply(new BigDecimal(100)).intValue();		 
	}
	
}
