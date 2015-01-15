package my.com.myriadeas.integral.cqrs.query.bib.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;

public class ResourceDescriptorSearchCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ResourceDescriptorSearchOrder {
		id, resourceDescriptorId, marc, status;
	}

	// constraints
	private String containsText;
	private Collection<String> specificResourceDescriptorIds = new ArrayList<String>();
	private Collection<Long> specificIds = new ArrayList<Long>();
	private ItemStatus status;
	//
	private ResourceDescriptorSearchOrder orderBy = ResourceDescriptorSearchOrder.resourceDescriptorId;
	private boolean ascending = true;

	// pagination support
	private int pageNumber = 1;
	private int itemsPerPage = 50;

	public String getContainsText() {
		return containsText;
	}

	public void setContainsText(String containsText) {
		this.containsText = containsText;
	}

	public Collection<String> getSpecificResourceDescriptorIds() {
		return specificResourceDescriptorIds;
	}

	public void setSpecificResourceDescriptorIds(
			Collection<String> specificResourceDescriptorIds) {
		this.specificResourceDescriptorIds = specificResourceDescriptorIds;
	}

	public boolean hasSpecificResourceDescriptorIdsFilter() {
		return specificResourceDescriptorIds != null
				&& !specificResourceDescriptorIds.isEmpty();
	}

	public Collection<Long> getSpecificIds() {
		return specificIds;
	}

	public void setSpecificIds(Collection<Long> specificIds) {
		this.specificIds = specificIds;
	}

	public boolean hasSpecificIdsFilter() {
		return specificIds != null && !specificIds.isEmpty();
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public ResourceDescriptorSearchOrder getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(ResourceDescriptorSearchOrder orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			this.pageNumber = 1;
		} else {
			this.pageNumber = pageNumber;
		}
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		if (itemsPerPage < 1) {
			this.itemsPerPage = 1;
		} else {
			this.itemsPerPage = itemsPerPage;
		}
	}
}
