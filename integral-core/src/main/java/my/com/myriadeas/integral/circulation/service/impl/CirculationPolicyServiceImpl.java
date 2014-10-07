package my.com.myriadeas.integral.circulation.service.impl;

import java.math.BigDecimal;

import my.com.myriadeas.integral.circulation.service.CirculationPolicyService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("circulationPolicyService")
public class CirculationPolicyServiceImpl implements CirculationPolicyService {
	
	private static final String CIRCULATION = "circulation";
		
	@Value("${" + CIRCULATION + ".allowEditCirc}")
	private Boolean allowEditCirc; 
	@Value("${" + CIRCULATION + ".blockBranch}")
	private Boolean blockBranch; 
	@Value("${" + CIRCULATION + ".calfines}")
	private Boolean calfines; 
	@Value("${" + CIRCULATION + ".chargingSlip}")
	private Boolean chargingSlip; 
	@Value("${" + CIRCULATION + ".cirByLocation}")
	private Boolean cirByLocation; 
	@Value("${" + CIRCULATION + ".circMode}")
	private String circMode;
	@Value("${" + CIRCULATION + ".circPurgeDays}")
	private Integer circPurgeDays;
	@Value("${" + CIRCULATION + ".circPurgeGrace}")
	private Integer circPurgeGrace;
	@Value("${" + CIRCULATION + ".diffBrncFinePay}")
	private Boolean diffBrncFinePay; 
	@Value("${" + CIRCULATION + ".dischargingSlip}")	
	private Boolean dischargingSlip; 
	@Value("${" + CIRCULATION + ".dueAfterExpiry}")
	private Boolean dueAfterExpiry; 
	@Value("${" + CIRCULATION + ".dueBeforeExpiry}")
	private Integer dueBeforeExpiry; 
	@Value("${" + CIRCULATION + ".dueDateFormat}")
	private String dueDateFormat; 
	@Value("${" + CIRCULATION + ".incluseHour}")
	private Boolean incluseHour;
	@Value("${" + CIRCULATION + ".lostBookReplacementCost}")
	private BigDecimal lostBookReplacementCost;
	@Value("${" + CIRCULATION + ".patronSecurity}")
	private Boolean patronSecurity;
	@Value("${" + CIRCULATION + ".renewalSlip}")
	private Boolean renewalSlip;
	@Value("${" + CIRCULATION + ".reservAccNo}")
	private Boolean reservAccNo;
	@Value("${" + CIRCULATION + ".reservationTimeFrame}")
	private Integer reservationTimeFrame;
	@Value("${" + CIRCULATION + ".reservIcat}")
	private Boolean reservIcat;	
	@Value("${" + CIRCULATION + ".rsvByBranch}")
	private Boolean rsvByBranch;
	@Value("${" + CIRCULATION + ".satelliteCirc}")
	private Boolean satelliteCirc;
	@Value("${" + CIRCULATION + ".timesOfReplacementCost}")
	private Integer timesOfReplacementCost;
	@Value("${" + CIRCULATION + ".yearRenew}")
	private Integer yearRenew;
	
		
	@Override
	public Boolean getAllowEditCirc() {
		return allowEditCirc;
	}

	public void setAllowEditCirc(Boolean allowEditCirc) {
		if (allowEditCirc == null){
			allowEditCirc = new Boolean(true);
		}
		this.allowEditCirc = allowEditCirc;
	}

	@Override
	public Boolean getBlockBranch() {
		return blockBranch;
	}

	public void setBlockBranch(Boolean blockBranch) {
		if (blockBranch == null){
			blockBranch = new Boolean(false);
		}
		this.blockBranch = blockBranch;
	}

	@Override
	public Boolean getCalfines() {
		return calfines;
	}

	public void setCalfines(Boolean calfines) {
		if (calfines == null){
			calfines = new Boolean(true);
		}
		this.calfines = calfines;
	}

	@Override
	public Boolean getChargingSlip() {
		
		return chargingSlip;
	}

	public void setChargingSlip(Boolean chargingSlip) {
		if (chargingSlip == null){
			chargingSlip = new Boolean(true);
		}

		this.chargingSlip = chargingSlip;
	}

	@Override
	public Boolean getCirByLocation() {
		return cirByLocation;
	}

	public void setCirByLocation(Boolean cirByLocation) {
		if (cirByLocation == null){
			cirByLocation = new Boolean(false);
		}
		this.cirByLocation = cirByLocation;
	}

	@Override
	public String getCircMode() {
		return circMode;
	}

	public void setCircMode(String circMode) {
		if (circMode == null){
			circMode = "I";
		}
		this.circMode = circMode;
	}

	@Override
	public Integer getCircPurgeDays() {
		return circPurgeDays;
	}

	public void setCircPurgeDays(Integer circPurgeDays) {
		if (circPurgeDays == null){
			circPurgeDays = new Integer(0);
		}
		this.circPurgeDays = circPurgeDays;
	}

	@Override
	public Integer getCircPurgeGrace() {
		return circPurgeGrace;
	}

	public void setCircPurgeGrace(Integer circPurgeGrace) {
		if (circPurgeGrace == null){
			circPurgeGrace = new Integer(0);
		}
		this.circPurgeGrace = circPurgeGrace;
	}

	@Override
	public Boolean getDiffBrncFinePay() {
		return diffBrncFinePay;
	}

	public void setDiffBrncFinePay(Boolean diffBrncFinePay) {
		if (diffBrncFinePay == null){
			diffBrncFinePay = new Boolean(true);
		}
		this.diffBrncFinePay = diffBrncFinePay;
	}

	@Override
	public Boolean getDischargingSlip() {
		return dischargingSlip;
	}

	public void setDischargingSlip(Boolean dischargingSlip) {
		if (dischargingSlip == null){
			dischargingSlip = new Boolean(false);
		}
		this.dischargingSlip = dischargingSlip;
	}

	@Override
	public Boolean getDueAfterExpiry() {
		return dueAfterExpiry;
	}

	public void setDueAfterExpiry(Boolean dueAfterExpiry) {
		if (dueAfterExpiry == null){
			dueAfterExpiry = new Boolean(false);
		}
		this.dueAfterExpiry = dueAfterExpiry;
	}

	@Override
	public Integer getDueBeforeExpiry() {
		return dueBeforeExpiry;
	}

	public void setDueBeforeExpiry(Integer dueBeforeExpiry) {
		if (dueBeforeExpiry == null){
			dueBeforeExpiry = new Integer(1);
		}
		this.dueBeforeExpiry = dueBeforeExpiry;
	}

	@Override
	public String getDueDateFormat() {
		return dueDateFormat;
	}

	public void setDueDateFormat(String dueDateFormat) {
		this.dueDateFormat = dueDateFormat;
	}

	@Override
	public Boolean getIncluseHour() {
		return incluseHour;
	}

	public void setIncluseHour(Boolean incluseHour) {
		if (incluseHour == null){
			incluseHour = new Boolean(false);
		}
		this.incluseHour = incluseHour;
	}

	@Override
	public BigDecimal getLostBookReplacementCost() {
		return lostBookReplacementCost;
	}

	public void setLostBookReplacementCost(BigDecimal lostBookReplacementCost) {
		if (lostBookReplacementCost == null){
			lostBookReplacementCost = new BigDecimal("0");
		}
		this.lostBookReplacementCost = lostBookReplacementCost;
	}

	@Override
	public Boolean getPatronSecurity() {
		return patronSecurity;
	}

	public void setPatronSecurity(Boolean patronSecurity) {
		if (patronSecurity == null){
			patronSecurity = new Boolean(false);
		}
		this.patronSecurity = patronSecurity;
	}

	@Override
	public Boolean getRenewalSlip() {
		return renewalSlip;
	}

	public void setRenewalSlip(Boolean renewalSlip) {
		if (renewalSlip == null){
			renewalSlip = new Boolean(false);
		}
		this.renewalSlip = renewalSlip;
	}

	@Override
	public Boolean getReservAccNo() {
		return reservAccNo;
	}

	public void setReservAccNo(Boolean reservAccNo) {
		if (reservAccNo == null){
			reservAccNo = new Boolean(true);
		}
		this.reservAccNo = reservAccNo;
	}

	@Override
	public Integer getReservationTimeFrame() {
		return reservationTimeFrame;
	}

	public void setReservationTimeFrame(Integer reservationTimeFrame) {
		if (reservationTimeFrame == null){
			reservationTimeFrame = new Integer(0);
		}
		this.reservationTimeFrame = reservationTimeFrame;
	}

	

	@Override
	public Boolean getReservIcat() {
		return reservIcat;
	}

	public void setReservIcat(Boolean reservIcat) {
		if (reservIcat == null){
			reservIcat = new Boolean(false);
		}
		this.reservIcat = reservIcat;
	}

	@Override
	public Boolean getRsvByBranch() {
		return rsvByBranch;
	}

	public void setRsvByBranch(Boolean rsvByBranch) {
		if (rsvByBranch == null){
			rsvByBranch = new Boolean(true);
		}
		this.rsvByBranch = rsvByBranch;
	}

	@Override
	public Boolean getSatelliteCirc() {
		return satelliteCirc;
	}

	public void setSatelliteCirc(Boolean satelliteCirc) {
		if (satelliteCirc == null){
			satelliteCirc = new Boolean(true);
		}
		this.satelliteCirc = satelliteCirc;
	}

	@Override
	public Integer getTimesOfReplacementCost() {
		return timesOfReplacementCost;
	}

	public void setTimesOfReplacementCost(Integer timesOfReplacementCost) {
		if (timesOfReplacementCost == null){
			timesOfReplacementCost = new Integer(0);
		}
		this.timesOfReplacementCost = timesOfReplacementCost;
	}

	@Override
	public Integer getYearRenew() {
		return yearRenew;
	}

	public void setYearRenew(Integer yearRenew) {
		if (yearRenew == null){
			yearRenew = new Integer(0);
		}
		this.yearRenew = yearRenew;
	}


	@Override
	public Boolean isDueAfterExpiry() {
		//TODO LINA
		//return dueAfterExpiry;
		return false;
	}

			
}
