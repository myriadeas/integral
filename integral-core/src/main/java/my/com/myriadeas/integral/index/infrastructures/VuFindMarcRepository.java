package my.com.myriadeas.integral.index.infrastructures;


import my.com.myriadeas.integral.index.domain.model.VuFindMarc;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface VuFindMarcRepository extends SolrCrudRepository<VuFindMarc, String> {
}
