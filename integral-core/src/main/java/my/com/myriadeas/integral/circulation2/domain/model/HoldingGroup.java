package my.com.myriadeas.integral.circulation2.domain.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

@javax.persistence.Entity
public class HoldingGroup extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(optional = false)
	private ItemCategory itemCategory;
	
	public HoldingGroup() {
		
	}

	public HoldingGroup(ItemCategory itemCategory) {
		Assert.notNull(itemCategory);
		this.itemCategory = itemCategory;
	}

	public ItemCategory getItemCategory() {
		return this.itemCategory;
	}
}