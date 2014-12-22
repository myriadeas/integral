package my.com.myriadeas.integral.assetmanagement.application.command;

import java.math.BigDecimal;

public class ReceiveItemCommand {
	
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

	public ReceiveItemCommand(String title, String author, String isbn,
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
	

}
