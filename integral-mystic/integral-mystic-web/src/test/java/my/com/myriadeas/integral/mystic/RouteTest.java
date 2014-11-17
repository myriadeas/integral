package my.com.myriadeas.integral.mystic;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.mystic.web.route.config.IntegralMysticRouteConfigDev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegralMysticRouteConfigDev.class)
public class RouteTest {
	
    @Test
	public void testInit() throws Exception {
    	
		
	}

}
