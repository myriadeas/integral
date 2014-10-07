package my.com.myriadeas.integral.circulation.vufind;

import java.util.ArrayList;
import java.util.List;

public class RenewMyItemsRequest {
	private String username;
	private List<Long> circulationTransactionIds = new ArrayList<Long>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Long> getCirculationTransactionIds() {
		return circulationTransactionIds;
	}
	public void setCirculationTransactionIds(List<Long> circulationTransactionIds) {
		this.circulationTransactionIds = circulationTransactionIds;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((circulationTransactionIds == null) ? 0 : circulationTransactionIds.hashCode());
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
		RenewMyItemsRequest other = (RenewMyItemsRequest) obj;
		if (circulationTransactionIds == null) {
			if (other.circulationTransactionIds != null)
				return false;
		} else if (!circulationTransactionIds.equals(other.circulationTransactionIds))
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
		return "RenewMyItemsInput [username=" + username + ", circulationTransactionIds="
				+ circulationTransactionIds + "]";
	}
	
	
}
