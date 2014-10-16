package my.com.myriadeas.integral.index.domain.model;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.index.config.IndexConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IndexConfig.class })
public abstract class AbstractIndexIntegrationTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	@Test
	public void testMarker() {
		
	}

}
