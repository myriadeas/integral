package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

@Configurable
@javax.persistence.Entity
public class Holding extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String itemIdentifier;

	@ManyToOne(optional = true)
	private HoldingGroup holdingGroup;

	private HoldingStatus status;

	public Holding() {

	}

	public Holding(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
		this.status = HoldingStatus.NEW;
	}

	public Holding(String itemIdentifier, HoldingGroup holdingGroup) {
		Assert.notNull(holdingGroup);
		Assert.notNull(itemIdentifier);
		this.itemIdentifier = itemIdentifier;
		this.holdingGroup = holdingGroup;
		this.status = HoldingStatus.NEW;
	}

	public Map<String, DomainEvent> getNewHoldingCreatedEvent() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		NewHoldingCreated event = new NewHoldingCreated(this.getId(),
				this.itemIdentifier);
		events.put("newHoldingCreated", event);
		return events;
	}

	public Map<String, DomainEvent> release(ItemCategory itemCategory) {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(this.status.release(this, itemCategory, events));
		return events;
	}

	protected void setHoldingGroup(HoldingGroup holdingGroup) {
		Assert.notNull(holdingGroup);
		this.holdingGroup = holdingGroup;
	}

	protected void setStatus(HoldingStatus holdingStatus) {
		this.status = holdingStatus;
	}

	protected String getItemIdentifier() {
		return this.itemIdentifier;
	}
	
	protected HoldingStatus getStatus() {
		return this.status;
	}
}