package my.com.myriadeas.integral.cataloguing2.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.cataloguing2.marc.domain.service.MarcRecordConverter;
import my.com.myriadeas.integral.cataloguing2.marc.domain.service.MarcRecordConverterImpl;
import my.com.myriadeas.integral.cataloguing2.marc.model.RecordType;

import org.junit.Test;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;

public class MarcRecordConverterImplTest {

	@Test
	public void testConvert() {
		MarcRecordConverter converter = new MarcRecordConverterImpl();
		Record record = this.getSummerlandRecord();
		RecordType recordType = converter.convert(record);
		assertNotNull(recordType);
		assertEquals("00714cam a2200205 a 4500", recordType.getLeader()
				.getValue());
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
