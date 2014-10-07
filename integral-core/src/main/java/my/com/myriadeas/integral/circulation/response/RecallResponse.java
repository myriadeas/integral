package my.com.myriadeas.integral.circulation.response;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;


public class RecallResponse {
	private AbstractUser user;
	private Item item;
	private Branch branch;
	private Date recallDateTime;
	private Officer loginUser;
	private ReservationTransaction reservationDetail;
	
	
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser recaller) {
		this.user = recaller;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch recalledBranch) {
		this.branch = recalledBranch;
	}
	public Date getRecallDateTime() {
		return recallDateTime;
	}
	public void setRecallDateTime(Date recallDateTime) {
		this.recallDateTime = recallDateTime;
	}
	public Officer getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(Officer officer) {
		this.loginUser = officer;
	}
	public ReservationTransaction getReservationDetail() {
		return reservationDetail;
	}
	public void setReservationDetail(ReservationTransaction reservation) {
		this.reservationDetail = reservation;
	}
	
	
	
}
