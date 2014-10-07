package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.SMD;
import my.com.myriadeas.integral.data.jpa.repositories.impl.SMDRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "glsmdDatabasePopulator")
public class SmdDatabasePopulator extends AbstractDatabasePopulator implements SmdDatabasePopulatorIntf {

	@Autowired
	private SMDRepositoryImpl glsmdRepository;

	public void init() {
		List<SMD> smds = new ArrayList<SMD>();
		smds.add(BOOK);
		smds.add(VIDEO);
		glsmdRepository.save(smds);
	}
}
