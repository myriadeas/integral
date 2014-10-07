package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiptingTransactionCodeRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.ReceiptingTransactionCodeRepository{
	
	@Query("SELECT b FROM ReceiptingTransactionCode b WHERE b.code=(:code)")
    public ReceiptingTransactionCode findByCode(@Param("code") String code);
}