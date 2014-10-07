package my.com.myriadeas.integral.circulation.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;

public interface OverdueItem {
	public Date getCreationDateTime();
	public Date getCheckOutDateTime();
	public Date getCheckInDateTime();
	public Date getDueDateTime();
	public LoanUnit getLoanUnit();
	public int getLateBy();
	public BigDecimal getFines();
	public BigDecimal getBalance();
	public FinesTransaction createFinesTransaction();
}
