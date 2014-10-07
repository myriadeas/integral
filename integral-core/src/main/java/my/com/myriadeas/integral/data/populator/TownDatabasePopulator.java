package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Town;
import my.com.myriadeas.integral.data.jpa.repositories.impl.TownRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "townDatabasePopulator")
public class TownDatabasePopulator extends AbstractDatabasePopulator implements TownDatabasePopulatorIntf {

	@Autowired
	private TownRepositoryImpl townRepository;

	public void init() {
		List<Town> towns = new ArrayList<Town>();
		towns.add(PJ);
		towns.add(KL);
		towns.add(PG);
		townRepository.save(towns);
	}

}
