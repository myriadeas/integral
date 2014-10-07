package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Title;
import my.com.myriadeas.integral.data.jpa.repositories.impl.TitleRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "titleDatabasePopulator")
public class TitleDatabasePopulator extends AbstractDatabasePopulator implements TitleDatabasePopulatorIntf {

	@Autowired
	private TitleRepositoryImpl titleRepository;

	public void init() {
		List<Title> titles = new ArrayList<Title>();
		titles.add(MISTER);
		titles.add(MADAM);
		titles.add(MISS);
		titleRepository.save(titles);
	}


}
