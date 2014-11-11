package my.com.myriadeas.integral.circulation2.infrastructure.jpa;

import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingRepositoryImpl extends
		JpaRepository<Holding, Long>, HoldingRepository {

}
