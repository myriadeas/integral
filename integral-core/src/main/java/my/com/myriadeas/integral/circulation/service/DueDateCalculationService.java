package my.com.myriadeas.integral.circulation.service;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;

public interface DueDateCalculationService {

	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup);

	/*
	public Date calculateDueDateTime(Date checkOutDateTime,
			Glpatritemelig glpatritemelig);
	*/
	
	public Date calculateDueDateTime(Item item, Patron patron,
			Date checkOutDateTime);
}
