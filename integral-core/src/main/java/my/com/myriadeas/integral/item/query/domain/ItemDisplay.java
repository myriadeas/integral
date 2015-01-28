package my.com.myriadeas.integral.item.query.domain;

import java.util.LinkedHashSet;

public class ItemDisplay {
	
	private String itemIdentifier;

	private String resourceDescriptorIdentifier;

	private String title;

	private String author;

	private java.util.LinkedHashSet<String> isbn;

	public ItemDisplay() {
		super();
	}
		
	public ItemDisplay(String itemIdentifier,
			String resourceDescriptorIdentifier, String title, String author,
			LinkedHashSet<String> isbn) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

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

	public java.util.LinkedHashSet<String> getIsbn() {
		return isbn;
	}

	public void setIsbn(java.util.LinkedHashSet<String> isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "ItemDisplay [itemIdentifier=" + itemIdentifier
				+ ", resourceDescriptorIdentifier="
				+ resourceDescriptorIdentifier + ", title=" + title
				+ ", author=" + author + ", isbn=" + isbn + "]";
	}

	

}
