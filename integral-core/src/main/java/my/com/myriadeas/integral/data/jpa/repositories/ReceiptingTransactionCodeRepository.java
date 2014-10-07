package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReceiptingTransactionCodeRepository extends
		JpaRepository<ReceiptingTransactionCode, Long> {
}