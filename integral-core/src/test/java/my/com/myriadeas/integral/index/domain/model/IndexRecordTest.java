package my.com.myriadeas.integral.index.domain.model;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.*;

import my.com.myriadeas.integral.index.config.IndexConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IndexConfig.class })
public class IndexRecordTest {

	@Test
	public void testGetIndexer() {
		IndexRecord indexRecord = new IndexRecord("", "");
		assertNotNull(indexRecord.getIndexer());
	}

}
