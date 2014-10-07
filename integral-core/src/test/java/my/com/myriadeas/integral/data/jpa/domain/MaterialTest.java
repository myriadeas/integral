package my.com.myriadeas.integral.data.jpa.domain;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.data.jpa.domain.Material;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
public class MaterialTest {

	String titleASubfield = "Summerland/";

	MarcFactory factory = MarcFactory.newInstance();

	@Test
	public void testMarker(){
		
	}
	
	@Test
	public void test() {

		String marc = "00471nam  2200157   450002000150000005000180001510000200003324500510005325000120010426000490011630000330016550000200019850400300021865000310024865000340027900a053405658X00aH62b.B2 198600aBabbie, Earl R.00aThe practice of social research cEarl Babbie.00a4th ed.00aBelmont, Calif. bWadsworth Pub. Co.cc1986.00axxii, 577 p. bill. c24 cm.00aIncludes index.00aBibliography: p. 561-565.00aSocial sciencesxResearch.00aSocial sciencesxMethodology.";
		String materialNo = "1";
		String type = "A";
		String status = "D";
		Material material = new Material.MaterialBuilder(marc)
				.materialNo(materialNo).type(type).status(status).build();

		assertEquals(materialNo, material.getMaterialNo());
		assertEquals(type, material.getType());
		assertEquals(status, material.getStatus());

	}

	@Test
	public void testTitleFromVufindMarc() {

		Material material = new Material.MaterialBuilder(
				generateDefaultRecord()).build();
		assertEquals("Summerland/ Michael Chabon.", material.getTitleFromVufindMarc());
	}

	@Test
	public void testValid() {

		Record record = generateDefaultRecord();
		Material material = new Material.MaterialBuilder(record).build();
		assertFalse(material.isValid());

		DataField df = factory.newDataField("260", ' ', ' ');
		df.addSubfield(factory.newSubfield('a', "Dewan Bahasa & Pustaka"));
		df.addSubfield(factory.newSubfield('c', "2014"));
		record.addVariableField(df);
		material = new Material.MaterialBuilder(record).build();
		assertTrue(material.isValid());
	}

	@Test
	public void testReconstructMarcRecord() {
		Record record = generateDefaultRecord();
		Material material = new Material.MaterialBuilder(record).materialNo(
				"0000000001").build();

		// pre
		assertEquals(material.getMarcString(record), material.getMarc());
		material = new Material.MaterialBuilder(
				material.reconstructMarcRecord()).materialNo("0000000001")
				.build();
		// post
		Record expectedRecord = factory.newRecord("00000cam a2200000 a 4500");
		expectedRecord.addVariableField(factory.newControlField("001",
				"0000000001"));
		DataField df = factory.newDataField("245", '1', '0');
		df.addSubfield(factory.newSubfield('a', titleASubfield));
		df.addSubfield(factory.newSubfield('c', "Michael Chabon."));
		expectedRecord.addVariableField(df);
		df = factory.newDataField("010", ' ', ' ');
		df.addSubfield(factory.newSubfield('a', "12883376"));
		expectedRecord.addVariableField(df);

		assertEquals(material.getMarcString(expectedRecord), material.getMarc());

		material = new Material.MaterialBuilder(
				material.reconstructMarcRecord()).materialNo("0000000001")
				.build();

		// unchange result expected
		assertEquals(material.getMarcString(expectedRecord), material.getMarc());

	}

	private Record generateDefaultRecord() {

		// create a record with leader
		Record record = factory.newRecord("00000cam a2200000 a 4500");
		// add a control field
		record.addVariableField(factory.newControlField("001", "12883376"));
		// add a data field
		DataField df = factory.newDataField("245", '1', '0');
		df.addSubfield(factory.newSubfield('a', titleASubfield));
		df.addSubfield(factory.newSubfield('c', "Michael Chabon."));
		record.addVariableField(df);
		return record;
	}

}
