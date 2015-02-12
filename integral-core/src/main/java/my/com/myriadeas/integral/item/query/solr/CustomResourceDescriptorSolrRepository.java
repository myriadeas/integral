package my.com.myriadeas.integral.item.query.solr;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;

public interface CustomResourceDescriptorSolrRepository {
	
	public List<ResourceDescriptorSolr> searchByAvailableInput(String title,
			String author, String isbn);
	
	public List<ResourceDescriptorSolr> searchByTitle(String title);

	
	public List<ResourceDescriptorSolr> searchByAuthor(String author);

	
	public List<ResourceDescriptorSolr> searchByIsbn(String isbn);


	public List<ResourceDescriptorSolr> searchByTitleAuthorIsbn(
			String title, String author, String isbn);
	
	public ResourceDescriptorSolr searchById(String id);


}
