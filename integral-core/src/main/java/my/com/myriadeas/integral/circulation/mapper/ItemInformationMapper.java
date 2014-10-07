package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.BigDecimalScale;
import my.com.myriadeas.integral.circulation.response.ItemInformationResponse;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface ItemInformationMapper extends BigDecimalScale{

	public abstract ItemInformationResponse convertTo(Item item);
	public abstract ItemInformationResponse convertTo(Item item, CirculationTransaction circulationTransaction);

}