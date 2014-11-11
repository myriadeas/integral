package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
public class Holding extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemIdentifier;

	private HoldingGroup holdingGroup;

	public Holding(String itemIdentifier, HoldingGroup holdingGroup) {
		this.itemIdentifier = itemIdentifier;
		this.holdingGroup = holdingGroup;
	}

	public void assignHoldingGroup(HoldingGroup holdingGroup) {
		this.holdingGroup = holdingGroup;
	}

	public void removeHoldingGroup() {
		this.holdingGroup = null;
	}
}