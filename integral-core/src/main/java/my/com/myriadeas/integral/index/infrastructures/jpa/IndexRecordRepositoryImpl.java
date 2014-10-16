package my.com.myriadeas.integral.index.infrastructures.jpa;

import my.com.myriadeas.integral.index.domain.model.IndexRecord;
import my.com.myriadeas.integral.index.domain.model.IndexRecordRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IndexRecordRepositoryImpl extends
		JpaRepository<IndexRecord, Long>, IndexRecordRepository {

}
