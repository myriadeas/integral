package my.com.myriadeas.integral.mystic;

import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;
import my.com.myriadeas.integral.data.jpa.domain.Material;

public abstract class AbstractTransactionalCirculationTests extends
		IntegralAbstractTransactionalJUnit4SpringContextTests {
	

	protected Material getDefaultMaterial() {
		Material material = new Material();
		material.setMaterialNo("0000000001");
		material.setStatus("C");
		material.setType("C");
		String marc = "00471nam  2200157   45000200015000000500018000151000"
				+ "02000033245005100053250001200104260004900116300003300165"
				+ "50000200019850400300021865000310024865000340027900a053405"
				+ "658X00aH62b.B2 198600aBabbie, Earl R.00aThe practice of "
				+ "social research cEarl Babbie.00a4th ed.00aBelmont, Calif."
				+ " bWadsworth Pub. Co.cc1986.00axxii, 577 p. bill. c24 cm.00a"
				+ "Includes index.00aBibliography: p. 561-565.00aSocial science"
				+ "sxResearch.00aSocial sciencesxMethodology.";
		material.setMarc(marc);
		return material;
	}

	protected Material getDefaultMaterial(String materialNo) {
		Material material = getDefaultMaterial();
		material.setMaterialNo(materialNo);
		return material;
	}
}
