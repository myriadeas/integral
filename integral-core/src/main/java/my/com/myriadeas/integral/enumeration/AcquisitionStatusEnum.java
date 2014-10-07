package my.com.myriadeas.integral.enumeration;

public interface AcquisitionStatusEnum {
	
	public final static String _NEW_REQUEST = "_NEW_REQUEST";
	public final static String USER_REQUEST = "00";
	public final static String REQUESTED = "01";
	public final static String AWAITING_REVIEW = "02";
	public final static String UNDER_REVIEW = "03";
	public final static String APPROVED_FOR_ORDER= "04";
	public final static String REQUESTOR_ADDED_VIA_ORDER_ENTRY= "05";
	public final static String REQUEST_REJECTED= "06";
	public final static String ORDER_RECEIVED= "07";
	public final static String NEW_ORDER= "08";
	public final static String REORDER= "09";
	public final static String READY_TO_ORDER= "10";
	public final static String PARTIAL_ORDER_CANCELLED= "14";
	public final static String ORDER_CANCELLED= "15";
	public final static String ORDER_PENDING_ON_BATCH_ORDER_PRINTING= "19";
	public final static String PURCHASE_ORDER_SENT= "20";
	public final static String LOCAL_ORDER_INVOICE_RECEIVED= "21";
	public final static String LOCAL_ORDER_PAYMENT_REQUESTED= "22";
	public final static String LOCAL_ORDER_PAYMENT_RECORDED= "23";
	public final static String LOCAL_ORDER_PARTIAL_RECEIPT= "28";
	public final static String LOCAL_ORDER_FULL_RECEIPT= "31";
	public final static String PROFORMA_PENDING_ON_BATCH_ORDER_PRINTING= "39";
	public final static String PROFORMA_INVOICE_REQUESTED= "40";
	public final static String PROFORMA_INVOICE_RECEIVED= "41";
	public final static String PROFORMA_PAYMENT_REQUESTED= "42";
	public final static String PROFORMA_PAYMENT_RECORDED= "43";
	public final static String PROFORMA_ORDER_SENT= "44";
	public final static String PROFORMA_ORDER_PARTIAL_RECEIPT= "46";
	public final static String PROFORMA_ORDER_FULL_RECEIPT= "49";
	public final static String REQUEST_FOR_GIFT_EXCHANGE= "60";
	public final static String GIFT_PARTIAL_RECEIPT= "61";
	public final static String GIFT_FULL_RECEIPT= "62";
	public final static String RECEIVING_GIFT= "70";
	public final static String BLANKET_ORDER_SENT= "80";
	public final static String BLANKET_ORDER_INVOICE_RECEIVED= "81";
	public final static String BLANKET_ORDER_PAYMENT_REQUESTED= "82";
	public final static String BLANKET_ORDER_PAYMENT_RECORDED= "83";
	public final static String BLANKET_ORDER_PARTIAL_RECEIPT= "88";
	public final static String BLANKET_ORDER_FULL_RECEIPT= "91";
	public final static String INVOICE_ENTERED = "N";
	public final static String PAYMENT_REQUESTED = "R";
	public final static String PAYMENT_RECORDED = "P";

}
