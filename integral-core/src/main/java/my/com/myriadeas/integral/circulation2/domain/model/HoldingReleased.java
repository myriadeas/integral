package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class HoldingReleased implements DomainEvent {

	private Long holdingId;

	private String itemIdentifier;

	private Long holdingGroupId;

	public HoldingReleased(Long holdingId, String itemIdentifier,
			Long holdingGroupId) {
		super();
		this.holdingId = holdingId;
		this.itemIdentifier = itemIdentifier;
		this.holdingGroupId = holdingGroupId;
	}

	public Long getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Long holdingId) {
		this.holdingId = holdingId;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public Long getHoldingGroupId() {
		return holdingGroupId;
	}

	public void setHoldingGroupId(Long holdingGroupId) {
		this.holdingGroupId = holdingGroupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((holdingGroupId == null) ? 0 : holdingGroupId.hashCode());
		result = prime * result
				+ ((holdingId == null) ? 0 : holdingId.hashCode());
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
		HoldingReleased other = (HoldingReleased) obj;
		if (holdingGroupId == null) {
			if (other.holdingGroupId != null)
				return false;
		} else if (!holdingGroupId.equals(other.holdingGroupId))
			return false;
		if (holdingId == null) {
			if (other.holdingId != null)
				return false;
		} else if (!holdingId.equals(other.holdingId))
			return false;
		if (itemIdentifier == null) {
			if (other.itemIdentifier != null)
				return false;
		} else if (!itemIdentifier.equals(other.itemIdentifier))
			return false;
		return true;
	}

}
