package my.com.myriadeas.integral.index.infrastructures;

import my.com.myriadeas.integral.index.domain.model.IndexRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IndexRecordRepository extends JpaRepository<IndexRecord, Long> {

	public IndexRecord findByResourceDescriptorId(String resourceDescriptorId);
}
