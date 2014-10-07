package my.com.myriadeas.integral.circulation.service.impl;

import my.com.myriadeas.integral.circulation.service.ReceiptingTransactionCodeService;
import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;
import my.com.myriadeas.integral.data.populator.ReceiptingTransactionCodeData;
import my.com.myriadeas.integral.enumeration.ReceiptingTransactionCodeEnum;
import my.com.myriadeas.integral.receipting.exception.UndefineReceiptingTransactionCodeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("receiptingTransactionCodeService")
public class ReceiptingTransactionCodeServiceImpl implements ReceiptingTransactionCodeService, ReceiptingTransactionCodeData {
	private static final Logger logger = LoggerFactory
			.getLogger(ReceiptingTransactionCodeServiceImpl.class);

	
	
	public ReceiptingTransactionCodeServiceImpl() {
	}


	@Override
	public ReceiptingTransactionCode getOverdueFines() {
		if (OVERDUE_FINES == null) {
			throwUndefineReceiptingTransactionCodeException(ReceiptingTransactionCodeEnum.OVERDUE_FINES);
		}
		return OVERDUE_FINES;
	}


	

	@Override
	public ReceiptingTransactionCode getMembershipFee() {
		if (MEMBERSHIP_FEE == null) {
			throwUndefineReceiptingTransactionCodeException(ReceiptingTransactionCodeEnum.MEMBERSHIP_FEE);
		}
		return MEMBERSHIP_FEE;
	}



	@Override
	public ReceiptingTransactionCode getProcessingFee() {
		if (PROCESSING_FEE == null) {
			throwUndefineReceiptingTransactionCodeException(ReceiptingTransactionCodeEnum.PROCESSING_FEE);
		}
		return PROCESSING_FEE;
	}
	
	
	private void throwUndefineReceiptingTransactionCodeException(String receiptingTransactionCode) {
		String msg = "Receipting Transaction Code '" + receiptingTransactionCode + "' not exists in ReceiptingTransactionCode.";
		logger.error(msg);
		throw new UndefineReceiptingTransactionCodeException(msg);

	}




	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}




}
