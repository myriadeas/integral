package my.com.myriadeas.integral.item.query.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;


public class ResourceDescriptorSolr {

	@Id
	private String id;

	@Indexed
	private String title;

	@Indexed(value = "textProper")
	private String author;

	@Indexed(value= "isn")
	private String isbn;

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	
	

}