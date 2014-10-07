package my.com.myriadeas.integral.marc.service;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.cataloguing.facade.impl.MarcServiceFacadeImpl;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class CataloguingTest {

	@Autowired
	private MarcServiceFacadeImpl marcService;

	@Test
	public void testMarker() {
	}

	@Test
	public void testCheckDuplicate() {
		MarcFactory factory = MarcFactory.newInstance();

		// create a record with leader
		Record record = factory.newRecord("00000cam a2200000 a 4500");

		// add a control field
		record.addVariableField(factory.newControlField("001", "12883376"));

		// add a data field
		DataField df = factory.newDataField("245", '1', '0');
		df.addSubfield(factory.newSubfield('a', "Summerland/"));
		df.addSubfield(factory.newSubfield('c', "Michael Chabon."));
		record.addVariableField(df);
		System.out.println("testCheckDuplicate : "
				+ marcService.checkDuplicate("data", "library", "020", 'a',
						record));
	}

	@Test
	public void testVerify() {
		// System.out.println("testCheckDuplicate : " +
		// marcService.checkDuplicate("data", "library", "020", 'a',
		// "{\"leader\":\"00072nam  2200049Ia 4500\",\"fields\":[{\"001\":\"1\"},{\"245\":{\"subfields\":[{\"a\":\"Time Management\"}],\"ind1\":\"0\",\"ind2\":\"0\"}}]}"));
		System.out
				.println("testVerify : "
						+ marcService
								.verify("data",
										"library",
										"{\"leader\":\"00313cam a2200061 a 4500\",\"fields\":[{\"001\":\"3\"},{\"020\":{\"subfields\":[{\"a\":\"9644224035 (set)\"}],\"ind1\":\" \",\"ind2\":\" \"}},{\"245\":{\"subfields\":[{\"6\":\"880-01\"},{\"a\":\"Asna\u0304di\u0304 az anjuman\u02b9ha\u0304-yi baladi\u0304, tujja\u0304r va as\u0323na\u0304f, 1300-1320 H. Sh. /\"},{\"c\":\"[tahi\u0304yah va tanz\u0323i\u0304m-i] Mu\u02bba\u0304vanat-i Khadama\u0304t-i Mudi\u0304ri\u0304yat va It\u0323t\u0323ila\u0304\u02bb\u02b9rasa\u0304ni\u0304-i Daftar-i Ra\u02bci\u0304s-i Jumhu\u0304r.\"}],\"ind1\":\"0\",\"ind2\":\"0\"}}]}"));
	}

}
