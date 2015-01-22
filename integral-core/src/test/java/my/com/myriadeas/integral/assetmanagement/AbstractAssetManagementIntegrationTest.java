package my.com.myriadeas.integral.assetmanagement;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigDev;
import my.com.myriadeas.integral.circulation2.config.CirculationConfigDev;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles(DEV)
@ContextConfiguration(classes = { AssetManagementConfigDev.class })
public abstract class AbstractAssetManagementIntegrationTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testMarker() {

	}

}
