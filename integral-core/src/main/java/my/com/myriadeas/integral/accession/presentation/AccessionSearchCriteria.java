package my.com.myriadeas.integral.accession.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;

public class AccessionSearchCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum AccessionSearchOrder {
		resourceDescriptorIdentifier, itemIdentifier, itemStatus;
	}

	// constraints
	private String containsText;
	private Collection<String> specificResourceDescriptorIds = new ArrayList<String>();
	private Collection<String> specificAccessionIds = new ArrayList<String>();
	private ItemStatus status;
	//
	private AccessionSearchOrder orderBy = AccessionSearchOrder.resourceDescriptorIdentifier;
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

	public Collection<String> getSpecificAccessionIds() {
		return specificAccessionIds;
	}

	public void setSpecificAccessionIds(Collection<String> specificAccessionIds) {
		this.specificAccessionIds = specificAccessionIds;
	}

	public boolean hasSpecificAccessionIdsFilter() {
		return specificAccessionIds != null && !specificAccessionIds.isEmpty();
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public AccessionSearchOrder getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(AccessionSearchOrder orderBy) {
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
