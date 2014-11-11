package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class NewHoldingCreated implements DomainEvent {

	private String itemIdentifier;

	private Long holdingGroupId;

	private String itemCategoryCode;

	public NewHoldingCreated(String itemIdentifier, Long holdingGroupId,
			String itemCategoryCode) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.holdingGroupId = holdingGroupId;
		this.itemCategoryCode = itemCategoryCode;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public Long getHoldingGroupId() {
		return holdingGroupId;
	}

	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

}
