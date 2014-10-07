package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Holding;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

public interface HoldingMapper {
	public Holding convertTo(Patron patron, Item item);
}
