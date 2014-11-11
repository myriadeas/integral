package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class HoldingGroup extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ItemCategory itemCategory;

	public HoldingGroup(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
}