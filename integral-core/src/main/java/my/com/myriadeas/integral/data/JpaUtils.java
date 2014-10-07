package my.com.myriadeas.integral.data;

import my.com.myriadeas.integral.data.jpa.domain.Officer;

public interface JpaUtils {

	public Boolean isDatabaseEmpty();
	
	public Officer getSystemUser();
}
