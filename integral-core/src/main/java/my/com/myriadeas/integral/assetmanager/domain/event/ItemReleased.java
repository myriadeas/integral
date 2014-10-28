package my.com.myriadeas.integral.assetmanager.domain.event;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;


public class ItemReleased implements DomainEvent{
	
	private String itemIdentifier;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public ItemReleased(String itemIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemIdentifier == null) ? 0 : itemIdentifier.hashCode());
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
		ItemReleased other = (ItemReleased) obj;
		if (itemIdentifier == null) {
			if (other.itemIdentifier != null)
				return false;
		} else if (!itemIdentifier.equals(other.itemIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemReleased [itemIdentifier=" + itemIdentifier + "]";
	}
	
	
}
