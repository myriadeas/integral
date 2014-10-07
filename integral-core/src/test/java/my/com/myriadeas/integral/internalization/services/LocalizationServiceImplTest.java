package my.com.myriadeas.integral.internalization.services;

import static org.junit.Assert.assertEquals;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
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
public class LocalizationServiceImplTest {

	@Autowired
	private LocalizationServiceImpl localizationService;

	@Test
	public void test() {
		String message = localizationService.getMessage("address.created",
				"This is default");
		System.out.println(message);
		assertEquals("Successfully created Address", message);
		message = localizationService.getMessage("address.notExist",
				"This is default");
		assertEquals("This is default", message);
		message = localizationService
				.getMessage(
						"vufindServiceFacade.cancelHolds.exception",
						new Object[] { "Unexpected error occur during cancelling holds." },
						"This is default");
		assertEquals(
				"Failed to cancel holds. [Unexpected error occur during cancelling holds.]",
				message);
	}

}
