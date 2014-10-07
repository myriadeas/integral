package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;
import my.com.myriadeas.integral.enumeration.ReceiptingTransactionCodeEnum;

public interface ReceiptingTransactionCodeData {
	ReceiptingTransactionCode OVERDUE_FINES = new ReceiptingTransactionCode(ReceiptingTransactionCodeEnum.OVERDUE_FINES, "Overdue fines");
	ReceiptingTransactionCode MEMBERSHIP_FEE = new ReceiptingTransactionCode(ReceiptingTransactionCodeEnum.MEMBERSHIP_FEE, "Membership fee charges");
	ReceiptingTransactionCode PROCESSING_FEE = new ReceiptingTransactionCode(ReceiptingTransactionCodeEnum.PROCESSING_FEE, "Processing fee charges");
	
}
