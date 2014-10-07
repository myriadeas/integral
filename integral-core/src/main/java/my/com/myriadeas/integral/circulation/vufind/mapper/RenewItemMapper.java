package my.com.myriadeas.integral.circulation.vufind.mapper;

import java.util.Map;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

public interface RenewItemMapper {
	public Map<String, Object> convertTo(CirculationTransaction circulationTransaction, boolean isSuccessful, String message);
}
