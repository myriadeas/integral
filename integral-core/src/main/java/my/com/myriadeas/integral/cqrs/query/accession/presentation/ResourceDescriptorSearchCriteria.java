package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorStatus;

public class ResourceDescriptorSearchCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ResourceDescriptorSearchOrder {
		RESOURCE_DESCRIPTOR_ID, MARC, STATUS;
	}

	// constraints
	private String containsText;
	private Collection<String> specificResourceDescriptorIds = new ArrayList<String>();
	private ResourceDescriptorStatus status;
	//
	private ResourceDescriptorSearchOrder orderBy = ResourceDescriptorSearchOrder.RESOURCE_DESCRIPTOR_ID;
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

	public void setSpecificResourceDescriptorIds(Collection<String> specificResourceDescriptorIds) {
		this.specificResourceDescriptorIds = specificResourceDescriptorIds;
	}

	public boolean hasSpecificResourceDescriptorIdsFilter() {
		return specificResourceDescriptorIds != null && !specificResourceDescriptorIds.isEmpty();
	}

	public ResourceDescriptorStatus getStatus() {
		return status;
	}

	public void setStatus(ResourceDescriptorStatus status) {
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
