package my.com.myriadeas.integral.item.query.solr;

import java.util.List;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorToListItem;

public interface ResourceDescriptorSolrRepositoryImpl extends
		ResourceDescriptorSolrRepository {

	public List<ResourceDescriptorToListItem> findByTitle(String title);

	// public List<ResourceDescriptorToListItem> findByAuthor(String author);

	// public List<ResourceDescriptorToListItem> findByIsbn(String isbn);

	// @Query("title:?0 AND author:?1 AND isbn:?2")
	// ResourceDescriptorToListItem findByTitleAuthorIsbn(String title,
	// String author, int isbn);
}
