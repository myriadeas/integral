package my.com.myriadeas.integral.assetmanagement.domain.event;

import java.math.BigDecimal;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class ItemReceived implements DomainEvent {
	
	private String title;
	
	private String author;
	
	private String isbn;
	
	private BigDecimal numberOfCopy;
	
	private BigDecimal foreignCost;
	
	private BigDecimal localCost;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getNumberOfCopy() {
		return numberOfCopy;
	}

	public void setNumberOfCopy(BigDecimal numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}

	public BigDecimal getForeignCost() {
		return foreignCost;
	}

	public void setForeignCost(BigDecimal foreignCost) {
		this.foreignCost = foreignCost;
	}

	public BigDecimal getLocalCost() {
		return localCost;
	}

	public void setLocalCost(BigDecimal localCost) {
		this.localCost = localCost;
	}

	public ItemReceived(String title, String author, String isbn,
			BigDecimal numberOfCopy, BigDecimal foreignCost,
			BigDecimal localCost) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.numberOfCopy = numberOfCopy;
		this.foreignCost = foreignCost;
		this.localCost = localCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((foreignCost == null) ? 0 : foreignCost.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result
				+ ((localCost == null) ? 0 : localCost.hashCode());
		result = prime * result
				+ ((numberOfCopy == null) ? 0 : numberOfCopy.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemReceived other = (ItemReceived) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (foreignCost == null) {
			if (other.foreignCost != null)
				return false;
		} else if (!foreignCost.equals(other.foreignCost))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (localCost == null) {
			if (other.localCost != null)
				return false;
		} else if (!localCost.equals(other.localCost))
			return false;
		if (numberOfCopy == null) {
			if (other.numberOfCopy != null)
				return false;
		} else if (!numberOfCopy.equals(other.numberOfCopy))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemReceived [title=" + title + ", author=" + author
				+ ", isbn=" + isbn + ", numberOfCopy=" + numberOfCopy
				+ ", foreignCost=" + foreignCost + ", localCost=" + localCost
				+ "]";
	}


}
