package my.com.myriadeas.integral.data.jpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.data.jpa.repositories.impl.FinesTransactionRepositoryImpl;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the RETRXN database table.
 * 
 */
@Entity
@Configurable
public class FinesTransaction extends AbstractDomain {
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	private FinesTransactionRepositoryImpl finesTransactionRepo;

	// @GeneratedValue(strategy=GenerationType.SEQUENCE,
	// generator="retrxn.re01txno")
	@SequenceGenerator(name = "finesTransaction.transactionNumber", sequenceName = "finesTransaction.transactionNumber", initialValue = 1, allocationSize = 1)
	private int transactionNumber;

	private BigDecimal amount = new BigDecimal("0");

	private Long circulationTransactionCounter;

	@NotBlank
	private String transactionCode;

	@NotNull
	private Date date;

	@ManyToOne(optional = true)
	private Item item;

	@ManyToOne(optional = true)
	private Officer officer;

	@ManyToOne(optional = false)
	private Patron patron;

	private String paymentMode;

	private BigDecimal paidAmount;

	private String receivedFrom;

	private String refer;

	private String sent1;

	private String sent2;

	private String sent3;

	private String status;

	private String updatedReference;

	public FinesTransaction() {
	}
	
	
	@Autowired
	public void setFinesTransactionRepo(
			FinesTransactionRepositoryImpl finesTransactionRepo) {
		this.finesTransactionRepo = finesTransactionRepo;
	}

	public int getTransactionNumber() {
		return getId().intValue();
		// return re01txno;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount == null) {
			amount = new BigDecimal("0");
		}
		this.amount = amount;
	}

	public Long getCirculationTransactionCounter() {
		return circulationTransactionCounter;
	}

	public void setCirculationTransactionCounter(Long circulationTransactionCounter) {
		this.circulationTransactionCounter = circulationTransactionCounter;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getRe01rcvfrom() {
		return receivedFrom;
	}

	public void setRe01rcvfrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getSent1() {
		return sent1;
	}

	public void setSent1(String sent1) {
		this.sent1 = sent1;
	}

	public String getSent2() {
		return sent2;
	}

	public void setSent2(String sent2) {
		this.sent2 = sent2;
	}

	public String getSent3() {
		return sent3;
	}

	public void setSent3(String sent3) {
		this.sent3 = sent3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedReference() {
		return updatedReference;
	}

	public void setUpdatedReference(String updatedReference) {
		this.updatedReference = updatedReference;
	}


	@Override
	public String toString() {
		return "FinesTransaction [transactionNumber=" + transactionNumber
				+ ", amount=" + amount + ", circulationTransactionCounter="
				+ circulationTransactionCounter + ", transactionCode="
				+ transactionCode + ", date=" + date + ", item=" + item
				+ ", officer=" + officer + ", patron=" + patron
				+ ", paymentMode=" + paymentMode + ", paidAmount=" + paidAmount
				+ ", receivedFrom=" + receivedFrom + ", refer=" + refer
				+ ", sent1=" + sent1 + ", sent2=" + sent2 + ", sent3=" + sent3
				+ ", status=" + status + ", updatedReference="
				+ updatedReference + "]";
	}

	
	
}