package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

@javax.persistence.Entity
public class Holding extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String itemIdentifier;

	@NotNull
	@ManyToOne(optional = false)
	private HoldingGroup holdingGroup;

	public Holding(String itemIdentifier, HoldingGroup holdingGroup) {
		Assert.notNull(holdingGroup);
		Assert.notNull(itemIdentifier);
		this.itemIdentifier = itemIdentifier;
		this.holdingGroup = holdingGroup;
	}

	public Map<String, DomainEvent> getNewHoldingCreatedEvent() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		NewHoldingCreated event = new NewHoldingCreated(this.itemIdentifier,
				this.holdingGroup.getId(), this.holdingGroup.getItemCategory()
						.getCode());
		events.put("newHoldingCreated", event);
		return events;
	}

	public void assignHoldingGroup(HoldingGroup holdingGroup) {
		Assert.notNull(holdingGroup);
		this.holdingGroup = holdingGroup;
	}

	public void removeHoldingGroup() {
		this.holdingGroup = null;
	}
}