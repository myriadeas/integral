package my.com.myriadeas.integral.circulation.command;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public class RenewEventCommand {

	private Item item;

	private CirculationTransaction circulationTransaction;
	
	private CirculationTransaction newCirculationTransaction;

	public RenewEventCommand(Item item, CirculationTransaction circulationTransaction, CirculationTransaction newCirculationTransaction) {
		this.item = item;
		this.circulationTransaction = circulationTransaction;
		this.newCirculationTransaction = newCirculationTransaction;
	}

	

	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}



	public CirculationTransaction getCirculationTransaction() {
		return circulationTransaction;
	}

	public void setCirculationTransaction(CirculationTransaction circulationTransaction) {
		this.circulationTransaction = circulationTransaction;
	}
	
	

	public CirculationTransaction getNewCirculationTransaction() {
		return newCirculationTransaction;
	}

	public void setNewCirculationTransaction(CirculationTransaction newCirculationTransaction) {
		this.newCirculationTransaction = newCirculationTransaction;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((circulationTransaction == null) ? 0
						: circulationTransaction.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime
				* result
				+ ((newCirculationTransaction == null) ? 0
						: newCirculationTransaction.hashCode());
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
		RenewEventCommand other = (RenewEventCommand) obj;
		if (circulationTransaction == null) {
			if (other.circulationTransaction != null)
				return false;
		} else if (!circulationTransaction.equals(other.circulationTransaction))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (newCirculationTransaction == null) {
			if (other.newCirculationTransaction != null)
				return false;
		} else if (!newCirculationTransaction
				.equals(other.newCirculationTransaction))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RenewEventCommand [item=" + item + ", circulationTransaction="
				+ circulationTransaction + ", newCirculationTransaction="
				+ newCirculationTransaction + "]";
	}

	
}
