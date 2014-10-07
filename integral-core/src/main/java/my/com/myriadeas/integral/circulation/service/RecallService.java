package my.com.myriadeas.integral.circulation.service;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

public interface RecallService{

	public ReservationTransaction recall(Patron patron, Item item, Branch recallBranch, Date recallDateTime);
}
