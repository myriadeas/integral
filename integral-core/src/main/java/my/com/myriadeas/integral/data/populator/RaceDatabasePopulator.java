package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Race;
import my.com.myriadeas.integral.data.jpa.repositories.impl.RaceRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "raceDatabasePopulator")
public class RaceDatabasePopulator extends AbstractDatabasePopulator implements RaceDatabasePopulatorIntf {

	@Autowired
	private RaceRepositoryImpl glraceRepository;

	public void init() {
		List<Race> races = new ArrayList<Race>();
		races.add(MALAY);
		races.add(CHINESE);
		races.add(INDIAN);
		races.add(MALAYSIAN);
		glraceRepository.save(races);
	}


}
