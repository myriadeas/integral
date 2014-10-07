package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Profile;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

public interface ProfileMapper {
	public Profile convertTo(Patron patron);
}
