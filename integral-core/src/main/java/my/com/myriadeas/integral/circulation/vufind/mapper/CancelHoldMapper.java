package my.com.myriadeas.integral.circulation.vufind.mapper;

import java.util.Map;

public interface CancelHoldMapper {
	public Map<String, Object> convertTo(boolean isSuccessful, String message);
}
