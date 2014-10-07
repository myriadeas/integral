package my.com.myriadeas.integral.circulation.service;

import java.math.BigDecimal;

public interface CirculationPolicyService {
	public Boolean getAllowEditCirc();
	public void setAllowEditCirc(Boolean allowEditCirc);
	public Boolean getBlockBranch();
	public void setBlockBranch(Boolean blockBranch);
	public Boolean getCalfines();
	public void setCalfines(Boolean calfines);
	public Boolean getChargingSlip();
	public void setChargingSlip(Boolean chargingSlip);
	public Boolean getCirByLocation();
	public void setCirByLocation(Boolean cirByLocation);
	public String getCircMode();
	public void setCircMode(String circMode);
	public Integer getCircPurgeDays();
	public void setCircPurgeDays(Integer circPurgeDays);
	public Integer getCircPurgeGrace();
	public void setCircPurgeGrace(Integer circPurgeGrace);
	public Boolean getDiffBrncFinePay();
	public void setDiffBrncFinePay(Boolean diffBrncFinePay);
	public Boolean getDischargingSlip();
	public void setDischargingSlip(Boolean dischargingSlip);
	public Boolean getDueAfterExpiry();
	public void setDueAfterExpiry(Boolean dueAfterExpiry);
	public Integer getDueBeforeExpiry();
	public void setDueBeforeExpiry(Integer dueBeforeExpiry);
	public String getDueDateFormat();
	public void setDueDateFormat(String dueDateFormat);
	public Boolean getIncluseHour();
	public void setIncluseHour(Boolean incluseHour);
	public Boolean isDueAfterExpiry();	
	public BigDecimal getLostBookReplacementCost();
	public void setLostBookReplacementCost(BigDecimal lostBookReplacementCost);
	public Boolean getPatronSecurity();
	public void setPatronSecurity(Boolean patronSecurity);
	public Boolean getRenewalSlip();
	public void setRenewalSlip(Boolean renewalSlip);
	public Integer getReservationTimeFrame();
	public void setReservationTimeFrame(Integer reservationTimeFrame);
	public Boolean getReservAccNo();
	public void setReservAccNo(Boolean reservAccNo);
	public Boolean getReservIcat();
	public void setReservIcat(Boolean reservIcat);
	public Boolean getRsvByBranch();
	public void setRsvByBranch(Boolean rsvByBranch);
	public Boolean getSatelliteCirc();	
	public void setSatelliteCirc(Boolean satelliteCirc);
	public Integer getTimesOfReplacementCost();
	public void setTimesOfReplacementCost(Integer timesOfReplacementCost);
	public Integer getYearRenew();
	public void setYearRenew(Integer yearRenew);
	
	
	
	
	
}
