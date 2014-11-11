package my.com.myriadeas.integral.circulation2.infrastructure.jpa;

import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroupRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingGroupRepositoryImpl extends
		JpaRepository<HoldingGroup, Long>, HoldingGroupRepository {

}
