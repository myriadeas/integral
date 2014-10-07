package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Status;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface StatusMapper {
	public Status convertTo(Item item);
}
