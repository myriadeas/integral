package my.com.myriadeas.integral.assetmanager.domain.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Item {

	private static final Logger logger = LoggerFactory.getLogger(Item.class);

	private static final long serialVersionUID = 1L;
	
	@Length(max = 12)
	@NotBlank
	@Column(unique = true)
	private String itemIdentifier;
	
	@Length(max = 20)
	@NotBlank
	private String resourceDescriptorIdentifier;
	
	private BigDecimal foreignPrice = new BigDecimal("0");

	private BigDecimal localPrice = new BigDecimal("0");
	
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

	public void setResourceDescriptorIdentifier(String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public BigDecimal getForeignPrice() {
		return foreignPrice;
	}

	public void setForeignPrice(BigDecimal foreignPrice) {
		this.foreignPrice = foreignPrice;
	}

	public BigDecimal getLocalPrice() {
		return localPrice;
	}

	public void setLocalPrice(BigDecimal localPrice) {
		this.localPrice = localPrice;
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
			BigDecimal foreignPrice, BigDecimal localPrice,
			ItemStatus itemStatus) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.foreignPrice = foreignPrice;
		this.localPrice = localPrice;
		this.itemStatus = itemStatus;
	}
	
	public Map<String, DomainEvent> release() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.release(this, events));
		return events;
	}
	
	public Map<String, DomainEvent> unrelease(){
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.unrelease(this, events));
		return events;
	}
	
	public Map<String, DomainEvent> delete(){
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setItemStatus(itemStatus.delete(this, events));
		return events;
	}
	

}
