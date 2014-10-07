package my.com.myriadeas.integral.circulation.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
	IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public abstract class IntegralAbstractCamelTestSupportTests extends
	CamelTestSupport {

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
	
	public List<Record> getRecordsFromFile(String marcFile) throws IOException {

		List<Record> records = new ArrayList<Record>();

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(marcFile);
		Assert.notNull(inputStream);

		MarcStreamReader reader = new MarcStreamReader(inputStream);
		while (reader.hasNext()) {
			Record record = reader.next();
			records.add(record);
		}

		inputStream.close();
		return records;
	}
	

}
