package my.com.myriadeas.integral.item.query.service;

import java.util.List;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;

public interface ItemReadService {
	
	public List<Item> getItemListByTitleAuthorIsbn(String title, String author, String isbn);
	
	public List<Item> getItemListByTitle(String title);
	
	public List<Item> getItemListByAuthor(String author);
	
	public List<Item> getItemListByIsbn(String isbn);

}
