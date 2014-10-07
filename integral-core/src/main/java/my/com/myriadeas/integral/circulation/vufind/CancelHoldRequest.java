package my.com.myriadeas.integral.circulation.vufind;

import java.util.ArrayList;
import java.util.List;

public class CancelHoldRequest {
	private String username;
	private List<String> reservationTransactionIds = new ArrayList<String>();
	
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getReservationTransactionIds() {
		return reservationTransactionIds;
	}
	public void setReservationTransactionIds(List<String> reservationTransactionIds) {
		this.reservationTransactionIds = reservationTransactionIds;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((reservationTransactionIds == null) ? 0 : reservationTransactionIds.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CancelHoldRequest other = (CancelHoldRequest) obj;
		if (reservationTransactionIds == null) {
			if (other.reservationTransactionIds != null)
				return false;
		} else if (!reservationTransactionIds.equals(other.reservationTransactionIds))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CancelHoldRequest [username=" + username + ", reservationTransactionIds="
				+ reservationTransactionIds + "]";
	}
	
	
}
