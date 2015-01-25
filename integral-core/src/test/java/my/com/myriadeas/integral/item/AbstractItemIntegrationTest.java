package my.com.myriadeas.integral.item;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.item.query.config.ItemConfigDev;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles(DEV)
@ContextConfiguration(classes = { ItemConfigDev.class })
public abstract class AbstractItemIntegrationTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testMarker() {

	}

}
