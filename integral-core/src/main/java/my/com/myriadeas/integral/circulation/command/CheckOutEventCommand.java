package my.com.myriadeas.integral.circulation.command;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public class CheckOutEventCommand {

	private Item item;

	private CirculationTransaction circulationTransaction;

	public CheckOutEventCommand(Item item, CirculationTransaction circulationTransaction) {
		this.item = item;
		this.circulationTransaction = circulationTransaction;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((circulationTransaction == null) ? 0
						: circulationTransaction.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		CheckOutEventCommand other = (CheckOutEventCommand) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "CheckOutEventCommand [item=" + item
				+ ", circulationTransaction=" + circulationTransaction + "]";
	}

	
}
