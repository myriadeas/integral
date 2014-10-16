package my.com.myriadeas.integral.index.infrastructures.solr;

import my.com.myriadeas.integral.index.domain.model.VuFindMarc;
import my.com.myriadeas.integral.index.domain.model.VuFindMarcRepository;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface VuFindMarcRepositoryImpl extends
		SolrCrudRepository<VuFindMarc, String>, VuFindMarcRepository {
}
