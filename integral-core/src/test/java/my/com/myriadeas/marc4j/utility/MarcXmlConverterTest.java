package my.com.myriadeas.marc4j.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.MarcXmlConverter;

import org.junit.Test;
import org.marc4j.marc.Record;

public class MarcXmlConverterTest {

	@Test
	public void testConvertMarcToMarcXml() {
		MarcXmlConverter marcXmlConverter = new MarcXmlConverter();
		String marc = "00471nam  2200157   450002000150000005000180001510000200003324500510005325000120010426000490011630000330016550000200019850400300021865000310024865000340027900a053405658X00aH62b.B2 198600aBabbie, Earl R.00aThe practice of social research cEarl Babbie.00a4th ed.00aBelmont, Calif. bWadsworth Pub. Co.cc1986.00axxii, 577 p. bill. c24 cm.00aIncludes index.00aBibliography: p. 561-565.00aSocial sciencesxResearch.00aSocial sciencesxMethodology.";
		// String marc =
		// "00164cas  2200073   4500022001400000245002300014260003500037090001800072  a3245-432X 0aiFeel ضثص ضث ضصثضص2 aKuala LumpurbArenabukuc1983.00aQA213.4342342";
		ByteArrayOutputStream marcXml;
		InputStream input = new ByteArrayInputStream(marc.getBytes());
		marcXml = marcXmlConverter.convertMarcToMarcXml(input);
		System.out.println("marcXml : " + marcXml.toString());

	}

	@Test
	public void testConvertMarcToListOfRecords() {
		MarcXmlConverter marcXmlConverter = new MarcXmlConverter();
		String marc = "00471nam  2200157   450002000150000005000180001510000200003324500510005325000120010426000490011630000330016550000200019850400300021865000310024865000340027900a053405658X00aH62b.B2 198600aBabbie, Earl R.00aThe practice of social research cEarl Babbie.00a4th ed.00aBelmont, Calif. bWadsworth Pub. Co.cc1986.00axxii, 577 p. bill. c24 cm.00aIncludes index.00aBibliography: p. 561-565.00aSocial sciencesxResearch.00aSocial sciencesxMethodology.";
		
		List<Record> records = marcXmlConverter
				.convertMarcToListOfRecords(marc);
		System.out.println("records : " + records.toString());

	}
}
