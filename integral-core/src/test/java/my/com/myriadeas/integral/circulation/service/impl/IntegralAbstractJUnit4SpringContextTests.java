package my.com.myriadeas.integral.circulation.service.impl;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;

import java.util.Date;

import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles(DEV)
@ContextConfiguration(classes = IntegralCirculationConfigDev.class)
public abstract class IntegralAbstractJUnit4SpringContextTests extends
		AbstractJUnit4SpringContextTests {

	private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormat
			.forPattern("dd/MM/yyyy");
	
	private static final DateTimeFormatter STANDARD_DATE_FORMAT_WITHTIME = DateTimeFormat
			.forPattern("dd/MM/yyyy HH:mm:ss");

	
	
	@Before
	public void setUp() {
	}
	
	
	public Date getDate(String input) {
		return STANDARD_DATE_FORMAT.parseDateTime(input).toDate();
	}

	public Date getDatetime(String input) {
		return STANDARD_DATE_FORMAT_WITHTIME.parseDateTime(input).toDate();
	}

}
