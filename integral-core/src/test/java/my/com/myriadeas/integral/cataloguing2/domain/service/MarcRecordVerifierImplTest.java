package my.com.myriadeas.integral.cataloguing2.domain.service;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.cataloguing2.config.CataloguingConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CataloguingConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class MarcRecordVerifierImplTest {

	@Autowired
	VerifierImpl verifier;

	@Test
	public void testVerify() {
		Record record = this.getSummerlandRecord();
		String result = verifier.verify(record);
		assertNotNull(result);
		System.out.println(result);
	}

	private MarcFactory factory = MarcFactory.newInstance();;

	private Record makeSummerlandRecord() {
		Record sumland = factory.newRecord("00714cam a2200205 a 4500");
		sumland.addVariableField(factory.newControlField("001", "12883376"));
		sumland.addVariableField(factory.newControlField("005",
				"20030616111422.0"));
		sumland.addVariableField(factory.newControlField("008",
				"020805s2002    nyu    j      000 1 eng  "));
		sumland.addVariableField(factory.newDataField("020", ' ', ' ', "a",
				"0786808772"));
		sumland.addVariableField(factory.newDataField("020", ' ', ' ', "a",
				"0786816155 (pbk.)"));
		sumland.addVariableField(factory.newDataField("040", ' ', ' ', "a",
				"DLC", "c", "DLC", "d", "DLC"));
		sumland.addVariableField(factory.newDataField("100", '1', ' ', "a",
				"Chabon, Michael."));
		sumland.addVariableField(factory.newDataField("245", '1', '0', "a",
				"Summerland /", "c", "Michael Chabon."));
		sumland.addVariableField(factory.newDataField("245", '1', '3', "a",
				"Summerland /", "c", "Michael Chabon.", "a", "Summerland /",
				"c", "Michael Chabon."));
		sumland.addVariableField(factory.newDataField("250", ' ', ' ', "a",
				"1st ed."));
		sumland.addVariableField(factory.newDataField("260", ' ', ' ', "a",
				"New York :", "b",
				"Miramax Books/Hyperion Books for Children,", "c", "c2002."));
		sumland.addVariableField(factory.newDataField("300", ' ', ' ', "a",
				"500 p. ;", "c", "22 cm."));
		sumland.addVariableField(factory
				.newDataField(
						"520",
						' ',
						' ',
						"a",
						"Ethan Feld, the worst baseball player in the history of the game, finds himself recruited by a 100-year-old scout to help a band of fairies triumph over an ancient enemy."));
		sumland.addVariableField(factory.newDataField("650", ' ', '1', "a",
				"Fantasy."));
		sumland.addVariableField(factory.newDataField("650", ' ', '1', "a",
				"Baseball", "v", "Fiction."));
		sumland.addVariableField(factory.newDataField("650", ' ', '1', "a",
				"Magic", "v", "Fiction."));
		return sumland;
	}

	public Record getSummerlandRecord() {
		return makeSummerlandRecord();
	}

}
