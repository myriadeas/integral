package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.repositories.impl.LocationRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "locationDatabasePopulator")
public class LocationDatabasePopulator extends AbstractDatabasePopulator implements LocationDatabasePopulatorIntf {
	
	@Autowired
	private LocationRepositoryImpl locationRepository;
	
	@Autowired
	private BranchDatabasePopulator branchDatabasePopulator;

	public void init() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(LEVEL_ONE_PJ_BRANCH);
		locations.add(LEVEL_TWO_PJ_BRANCH);
		locations.add(LEVEL_ONE_MAIN_BRANCH);
		locations.add(LEVEL_ONE_KL_BRANCH);
		locationRepository.save(locations);
	}


}
