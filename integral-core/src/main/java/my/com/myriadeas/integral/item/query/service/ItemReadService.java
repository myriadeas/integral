package my.com.myriadeas.integral.item.query.service;

import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.item.query.domain.ItemDisplay;

public interface ItemReadService {

	public List<ItemDisplay> getItemListByTitleAuthorIsbn(String title,
			String author, String isbn);

	public List<ItemDisplay> getItemListByTitle(String title);

	public List<ItemDisplay> getItemListByAuthor(String author);

	public List<ItemDisplay> getItemListByIsbn(String isbn);

}
