package my.com.myriadeas.integral;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DevProfile.class, TestProfile.class })
public class TestDevelopmentProfile {

	@Autowired
	private ProfileBean profileBean;
	
	@Test
	public void test() {
		assertNotNull(profileBean);
		assertEquals("development", profileBean.getProfile());
	}

}
