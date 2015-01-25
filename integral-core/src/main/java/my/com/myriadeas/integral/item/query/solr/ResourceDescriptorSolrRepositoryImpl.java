package my.com.myriadeas.integral.item.query.solr;

import java.util.List;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;

import org.springframework.data.solr.repository.Query;

public interface ResourceDescriptorSolrRepositoryImpl extends ResourceDescriptorSolrRepository{
		
	public List<ResourceDescriptorSolr> findByTitle(String title);
	
	public List<ResourceDescriptorSolr> findByAuthor(String author);
	
	public List<ResourceDescriptorSolr> findByIsbn(String isbn);
	
	@Query("title:?0 AND author:?1 AND isbn:?2")
	public List<ResourceDescriptorSolr> findByQueryAnnotation(String title, String author, String isbn);
}


