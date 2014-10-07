package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.springframework.stereotype.Service;

@Service(value = "checkinDatabasePopulator")
public class CheckinDatabasePopulator extends AbstractDatabasePopulator
		implements CheckinDatabasePopulatorIntf {

	public void init() {
	}
	
	protected void setMembershipExpired(Patron patron) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();

	}

}
