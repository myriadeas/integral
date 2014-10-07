package my.com.myriadeas.integral.circulation.service;

import my.com.myriadeas.integral.data.jpa.domain.ReceiptingTransactionCode;

import org.springframework.beans.factory.InitializingBean;

public interface ReceiptingTransactionCodeService extends InitializingBean{
	
	public ReceiptingTransactionCode getOverdueFines();
	public ReceiptingTransactionCode getMembershipFee(); 
	public ReceiptingTransactionCode getProcessingFee();
	
	
}
