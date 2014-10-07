package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Glbibs;
import my.com.myriadeas.integral.data.jpa.repositories.GlbibsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "glbibsDatabasePopulator")
public class GlbibsDatabasePopulator extends AbstractDatabasePopulator
		implements GlbibsDatabasePopulatorIntf {

	@Autowired
	private GlbibsRepository glbibsRepository;

	public void init() {
		List<Glbibs> glbibss = new ArrayList<Glbibs>();
		glbibss.add(BBI);
		glbibss.add(BIB);
		glbibss.add(BIP);
		glbibss.add(CAT);
		
		glbibsRepository.save(glbibss);
	}

}
