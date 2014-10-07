package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Religion;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReligionRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "religionDatabasePopulator")
public class ReligionDatabasePopulator extends AbstractDatabasePopulator implements ReligionDatabasePopulatorIntf {

	@Autowired
	private ReligionRepositoryImpl religionRepository;

	public void init() {
		List<Religion> religions = new ArrayList<Religion>();
		religions.add(CHRISTIAN);
		religions.add(ISLAM);
		religions.add(HINDU);
		religionRepository.save(religions);
	}

}
