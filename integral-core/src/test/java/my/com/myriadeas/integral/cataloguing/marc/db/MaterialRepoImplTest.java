package my.com.myriadeas.integral.cataloguing.marc.db;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.cataloguing.marc.db.MaterialRepoImpl;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class MaterialRepoImplTest {

	@Autowired
	private MaterialRepoImpl materialRepoImpl;

	@Test
	public void testMarker() {
	}

	@Test
	public void testGetAllRecords() {
		assertNotNull(materialRepoImpl.getAllRecords("data", "library"));
	}

	@Test
	public void testGetRecord() {
		assertNotNull(materialRepoImpl.getRecord("data", "library",
				"0000000001"));
	}

}
