package my.com.myriadeas.integral.item.query.domain;

import org.apache.solr.client.solrj.beans.Field;


public class ResourceDescriptorSolr {

	@Field("id")
	private String id;
	
	@Field("title")
	private String title;
	
	@Field("author")
	private String author;
	
	@Field("isbn")
	private java.util.LinkedHashSet<String> isbn;
		
	public ResourceDescriptorSolr() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}