package my.com.myriadeas.integral.eligibility;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.eligibility.LookupTableFactoryBean;
import my.com.myriadeas.integral.eligibility.config.IntegralEligibilityConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(SpringEnvironmentUtil.DEV)
@ContextConfiguration(classes={IntegralEligibilityConfigDev.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LookupTableFactoryBeanTest {

	@Autowired
	@Qualifier("lookupTableFactoryBean")
	LookupTableFactoryBean lookupTableFactoryBean;
	
	@Autowired
	@Qualifier("lookupTableFactoryBean2")
	LookupTableFactoryBean lookupTableFactoryBean2;
	
	
	@Test
	public void test() throws Exception {
		assertNotNull(lookupTableFactoryBean);
	}

}
