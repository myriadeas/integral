package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReceiptingTransactionCodeRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "receiptingTransactionCodeDatabasePopulator")
public class ReceiptingTransactionCodeDatabasePopulator extends AbstractDatabasePopulator
		implements ReceiptingTransactionCodeDatabasePopulatorIntf {

	@Autowired
	private ReceiptingTransactionCodeRepositoryImpl receiptingTransactionCodeRepo;

	public void init() {
		List<ReceiptingTransactionCode> receiptingTransactionCodes = new ArrayList<ReceiptingTransactionCode>();
		receiptingTransactionCodes.add(OVERDUE_FINES);
		receiptingTransactionCodes.add(MEMBERSHIP_FEE);
		receiptingTransactionCodes.add(PROCESSING_FEE);
		receiptingTransactionCodeRepo.save(receiptingTransactionCodes);
	}

}
