package my.com.myriadeas.integral.data.jpa.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import my.com.myriadeas.integral.fines.Range;

/**
 * The persistent class for the GLFINE database table.
 * 
 */
@Entity
public class Fine extends AbstractDomain {

	private static final long serialVersionUID = 1L;

	private Integer start;

	private Integer stop;

	@Min(0)
	private BigDecimal rate = new BigDecimal("0");

	@Transient
	private transient Range range;

	@ManyToOne
	private PatronItemEligibility patronItemEligibility;

	public Fine() {
	}

	public Fine(Integer start, Integer stop, BigDecimal rate) {
		this.start = start;
		this.stop = stop;
		this.rate = rate;
		this.range = new Range(start, stop);
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Range getRange() {
		if (range == null) {
			range = new Range(this.start, this.stop);
		}
		return range;
	}

	public PatronItemEligibility getPatronItemEligibility() {
		return patronItemEligibility;
	}

	public void setPatronItemEligibility(
			PatronItemEligibility patronItemEligibility) {
		this.patronItemEligibility = patronItemEligibility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((stop == null) ? 0 : stop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fine other = (Fine) obj;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (stop == null) {
			if (other.stop != null)
				return false;
		} else if (!stop.equals(other.stop))
			return false;
		return true;
	}

}