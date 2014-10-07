package my.com.myriadeas.integral.circulation.command;

import my.com.myriadeas.integral.circulation.service.impl.OverdueItem;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;

public class GenerateFinesEventCommand {

	private OverdueItem overdueItem;

	private FinesTransaction finesTransaction;

	public GenerateFinesEventCommand(OverdueItem overdueItem, FinesTransaction finesTransaction) {
		this.overdueItem = overdueItem;
		this.finesTransaction = finesTransaction;
	}

	public OverdueItem getOverdueItem() {
		return overdueItem;
	}

	public void setOverdueItem(OverdueItem overdueItem) {
		this.overdueItem = overdueItem;
	}

	public FinesTransaction getFinesTransaction() {
		return finesTransaction;
	}

	public void setFinesTransaction(FinesTransaction finesTransaction) {
		this.finesTransaction = finesTransaction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((finesTransaction == null) ? 0 : finesTransaction.hashCode());
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
		GenerateFinesEventCommand other = (GenerateFinesEventCommand) obj;
		if (finesTransaction == null) {
			if (other.finesTransaction != null)
				return false;
		} else if (!finesTransaction.equals(other.finesTransaction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenerateFinesEventCommand [overdueItem=" + overdueItem
				+ ", finesTransaction=" + finesTransaction + "]";
	}

	
	

}
