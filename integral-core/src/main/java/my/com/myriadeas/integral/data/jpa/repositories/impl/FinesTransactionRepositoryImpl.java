package my.com.myriadeas.integral.data.jpa.repositories.impl;


import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FinesTransactionRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.FinesTransactionRepository{
	@Query("SELECT b FROM FinesTransaction b WHERE b.transactionCode=(:transactionCode) AND b.updatedReference=(:updatedReference) AND substring(b.refer,1,14)=(:refer)")
    public FinesTransaction findByTransactionCodeAndUpdatedReferenceAndRefer(@Param("transactionCode") String transactionCode, @Param("updatedReference") String updatedReference, @Param("refer") String refer);
	
	@Query("SELECT b FROM FinesTransaction b WHERE b.circulationTransactionCounter=(:circulationTransactionCounter)")
    public List<FinesTransaction> findByCirculationTransactionCounter(@Param("circulationTransactionCounter") Long circulationTransactionCounter);
	
	
			
}