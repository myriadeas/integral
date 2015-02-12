package my.com.myriadeas.integral.item.query.service;

import java.util.List;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;

public interface ResourceDescriptorSolrReadService {
	
	public ResourceDescriptorSolr getResourceDescriptorSolrById(String id);

	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByTitle(String title);

	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByAuthor(String author);

	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByIsbn(String isbn);

}
