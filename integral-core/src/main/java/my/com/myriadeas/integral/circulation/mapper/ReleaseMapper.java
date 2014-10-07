package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.ReleaseResponse;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface ReleaseMapper {

	public abstract ReleaseResponse convertTo(Item item, boolean isSuccessful, String message);

}