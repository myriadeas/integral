package my.com.myriadeas.integral.assetmanager.domain.model;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Configurable
@javax.persistence.Entity
public class Item extends AbstractPersistable<Long> implements Entity {

	private static final Logger logger = LoggerFactory.getLogger(Item.class);

	private static final long serialVersionUID = 1L;

	@Length(max = 12)
	@Column(unique = true)
	private String itemIdentifier;

	@Length(max = 20)
	@NotBlank
	private String resourceDescriptorIdentifier;

	private BigDecimal foreignCost = new BigDecimal("0");

	private BigDecimal localCost = new BigDecimal("0");

	@NotNull
	private ItemStatus itemStatus = ItemStatus.NEW;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getResourceDescriptorIdentifier() {
		return resourceDescriptorIdentifier;
	}

	public void setResourceDescriptorIdentifier(
			String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public BigDecimal getForeignPrice() {
		return foreignCost;
	}

	public void setForeignPrice(BigDecimal foreignCost) {
		this.foreignCost = foreignCost;
	}

	public BigDecimal getLocalPrice() {
		return localCost;
	}

	public void setLocalPrice(BigDecimal localCost) {
		this.localCost = localCost;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Item() {
		super();
	}

	public Item(String itemIdentifier, String resourceDescriptorIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public Item(String itemIdentifier, String resourceDescriptorIdentifier,
			BigDecimal foreignCost, BigDecimal localCost, ItemStatus itemStatus) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.foreignCost = foreignCost;
		this.localCost = localCost;
		this.itemStatus = itemStatus;
	}

	public Item(String resourceDescriptorIdentifier, BigDecimal foreignCost,
			BigDecimal localCost) {
		super();
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.foreignCost = foreignCost;
		this.localCost = localCost;
	}

	@PreUpdate
	@PostPersist
	public void postPersist() {
		this.itemIdentifier = String.format("%010d", this.getId());
	}

	public Map<String, DomainEvent> release() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.release(this, events));
		return events;
	}

	public Map<String, DomainEvent> unrelease() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.unrelease(this, events));
		return events;
	}

	public Map<String, DomainEvent> delete() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.delete(this, events));
		return events;
	}

}
