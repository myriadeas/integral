package my.com.myriadeas.integral.data.jpa.repositories.dod;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.data.jpa.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.LocationRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.populator.CheckinDatabasePopulator;
import my.com.myriadeas.integral.data.populator.config.DatabasePopulatorConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(SpringEnvironmentUtil.DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class,
		JpaInfrastructureConfigDev.class, DatabasePopulatorConfigDev.class })
public class DodTest {

	@Autowired
	PatronRepositoryImpl repo;

	@Autowired
	LocationRepositoryImpl gllocaRepo;

	@Autowired
	BranchRepositoryImpl glbrncRepository;

	@Autowired
	PatronCategoryRepositoryImpl glcateRepository;

	@Autowired
	CheckinDatabasePopulator checkinDatabasePopulator;

	@Test
	public void test() {
		assertNotNull(checkinDatabasePopulator);
		assertTrue(repo.count() > 0);

		System.out.println(glcateRepository.findAll());
	}

}
