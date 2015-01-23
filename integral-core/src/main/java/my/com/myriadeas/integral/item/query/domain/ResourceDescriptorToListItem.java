package my.com.myriadeas.integral.item.query.domain;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class ResourceDescriptorToListItem {

	@Id
    @Field
	private String id;

    @Field
	private String title;

    @Field
	private String author;

    @Field
	private int isbn;

	public ResourceDescriptorToListItem() {

	}

	public ResourceDescriptorToListItem(String id, String title, String author,
			int isbn) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
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

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

}
