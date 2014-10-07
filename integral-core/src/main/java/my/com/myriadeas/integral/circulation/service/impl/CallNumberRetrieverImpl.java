package my.com.myriadeas.integral.circulation.service.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation.service.CallNumberRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.springframework.stereotype.Service;

@Service("callNumberRetriever")
public class CallNumberRetrieverImpl implements CallNumberRetrieverIntf {

	private Map<String, String> callNumbers;

	public CallNumberRetrieverImpl() {
	}

	@Override
	public String getCallNumber(Item item) {
		if (callNumbers == null) {
			init();
		}
		String callNumber = "";

		if (item.getMaterial() != null
				&& item.getMaterial().getMaterialNo() != null) {
			callNumber = callNumbers.get(item.getMaterial().getMaterialNo());
		}
		if (callNumber == null || callNumber.length() == 0) {
			callNumber = "A12.25.123";
		}
		return callNumber;
	}

	protected void init() {
		callNumbers = new HashMap<String, String>();
		callNumbers.put("0000000001", "A12.25.123");
		callNumbers.put("0000000002", "A38.245.D");
		callNumbers.put("0000000003", "B78.25.23");
		callNumbers.put("0000000004", "Z5.24.153.2");
		callNumbers.put("0000000005", "B5.12.51");
		callNumbers.put("0000000006", "K4.51.5");
		callNumbers.put("0000000007", "M46.156.145");
		callNumbers.put("0000000008", "X2.1.2");
		callNumbers.put("0000000009", "J2.124.1.6");
		callNumbers.put("0000000010", "S.51.51");
	}

}
