package my.com.myriadeas.integral.circulation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.impl.FinesTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("finesTransactionFactory")
public class FinesTransactionFactory {
	private static final Logger logger = LoggerFactory
			.getLogger(FinesTransactionFactory.class);

	
	private FinesTransactionRepositoryImpl finesTransactionRepo;
	
	
	public FinesTransactionFactory(){
		
	}
	
	@Autowired
	public void setFinesTransactionRepo(
			FinesTransactionRepositoryImpl finesTransactionRepo) {
		this.finesTransactionRepo = finesTransactionRepo;
	}




	public FinesTransaction createFinesTransaction(Date checkInDateTime, BigDecimal finesAmount, Patron patron, Item item, int lateBy, Officer checkInOfficer, Long circulationTransactionCounter){
		logger.debug("Entering createFinesTransaction(). checkInDateTime={}, finesAmount={}, patron={}, item={}, lateBy={}, checkInOfficer={}, circulationTransactionCounter={}",
				new Object[] {
				checkInDateTime, finesAmount, patron, item, lateBy, checkInOfficer, circulationTransactionCounter });
		Date date = checkInDateTime;
		String transactionCode = "100";
		BigDecimal amount = finesAmount;
		BigDecimal paidAmount = new BigDecimal(0);
		String updatedReference = item.getItemIdentifier();
		String status = "0";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String refer = simpleDateFormat.format(item.getDueDateTime())
				+ ":" + lateBy;
						
		FinesTransaction finesTransaction = finesTransactionRepo
				.findByTransactionCodeAndUpdatedReferenceAndRefer(transactionCode,
						updatedReference, refer.substring(0, 14));

		if (finesTransaction == null) {
			// create new
			logger.debug("Creating new fines transaction");
			finesTransaction = new FinesTransaction();
			populateFinesTransaction(finesTransaction, transactionCode, date, amount,
					paidAmount, status, updatedReference, patron, refer,
					checkInOfficer, item, circulationTransactionCounter);
			
		} else {	
			logger.debug("Updating existing fines transaction");
			// TODO - for the reason to control maximum overdue fines
			// allowed.
			// It is very heavy calculate on demand for fines.
			// update when overdue notification
			populateFinesTransaction(finesTransaction, date, amount, refer,
					checkInOfficer, circulationTransactionCounter);
		}
		finesTransactionRepo.save(finesTransaction);
		logger.debug("Leaving createFinesTransaction(). finesTransaction={}",
				finesTransaction);
		return finesTransaction;
	}
	
	protected void populateFinesTransaction(FinesTransaction finesTransaction,
			String transactionCode, Date date, BigDecimal amount,
			BigDecimal paidAmount, String status, String updatedReference,
			Patron patron, String refer, Officer officer, Item item,
			Long circulationTransactionCounter) {
		finesTransaction.setTransactionCode(transactionCode);
		finesTransaction.setDate(date);
		finesTransaction.setAmount(amount);
		finesTransaction.setPaidAmount(paidAmount);
		finesTransaction.setStatus(status);
		finesTransaction.setUpdatedReference(updatedReference);
		finesTransaction.setPatron(patron);
		finesTransaction.setRefer(refer);
		finesTransaction.setOfficer(officer);
		finesTransaction.setItem(item);
		finesTransaction
				.setCirculationTransactionCounter(circulationTransactionCounter);
	}

	protected void populateFinesTransaction(FinesTransaction finesTransaction, Date date,
			BigDecimal amount, String refer, Officer officer,
			Long circulationTransactionCounter) {
		finesTransaction.setDate(date);
		finesTransaction.setAmount(amount);
		finesTransaction.setRefer(refer);
		finesTransaction.setOfficer(officer);
		finesTransaction
				.setCirculationTransactionCounter(circulationTransactionCounter);
	}
	
}
