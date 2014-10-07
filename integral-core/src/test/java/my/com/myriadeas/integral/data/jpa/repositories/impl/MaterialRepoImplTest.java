package my.com.myriadeas.integral.data.jpa.repositories.impl;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
public class MaterialRepoImplTest {

	@Autowired
	private MaterialRepositoryImpl repo;
	
	@Test
	public void testMarker() {
	}

	
	@Test
	public void test_autoCt02matno() {
		String materialNo = "0000000100";
		String marc = "00471nam  2200157   450002000150000005000180001510000200003324500510005325000120010426000490011630000330016550000200019850400300021865000310024865000340027900a053405658X00aH62b.B2 198600aBabbie, Earl R.00aThe practice of social research cEarl Babbie.00a4th ed.00aBelmont, Calif. bWadsworth Pub. Co.cc1986.00axxii, 577 p. bill. c24 cm.00aIncludes index.00aBibliography: p. 561-565.00aSocial sciencesxResearch.00aSocial sciencesxMethodology.";
		String status = "T";
		String type = "T";
		Material material = new Material.MaterialBuilder(marc).materialNo(materialNo)
				.type(status).status(type).build();
		repo.save(material);

		assertEquals(String.format("%010d", material.getId()),
				material.getMaterialNo());
		assertNotNull(material.getTitle());
		assertNotNull(repo.findByMaterialNo(String.format("%010d",
				material.getId())));

	}
	
	
}
