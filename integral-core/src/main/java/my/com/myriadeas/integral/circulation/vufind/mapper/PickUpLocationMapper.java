package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.PickUpLocation;
import my.com.myriadeas.integral.data.jpa.domain.Branch;

public interface PickUpLocationMapper {
	public PickUpLocation convertTo(Branch branch);
}
