package my.com.myriadeas.integral.data.populator;

import java.math.BigDecimal;

import my.com.myriadeas.integral.data.jpa.domain.Fine;

public interface FinesData extends PatronData {
	
	Fine FLAT_RATE_ONE_DOLLAR = new Fine(0, 999, new BigDecimal("1.00"));
}
