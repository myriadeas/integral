package my.com.myriadeas.integral.circulation.service;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface ReturnHandlerIntf {
	
	public void handleReturn(Item item, Date checkInDateTime);
	
}
