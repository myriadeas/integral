package my.com.myriadeas.integral.circulation2.infrastructure.jpa;

import my.com.myriadeas.integral.circulation2.domain.model.Borrower;
import my.com.myriadeas.integral.circulation2.domain.model.BorrowerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepositoryImpl extends
		JpaRepository<Borrower, Long>, BorrowerRepository {

}
