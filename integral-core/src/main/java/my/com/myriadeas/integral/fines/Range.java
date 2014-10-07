package my.com.myriadeas.integral.fines;

public class Range {
	private Integer start;
	private Integer end;

	public Range() {
		super();
	}

	public Range(Integer start) {
		super();
		this.start = start;
	}

	public Range(Integer start, Integer end) {
		super();
		if (end == null) {
			end = new Integer(-1);
		}
		this.start = start;
		this.end = end;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		if (end == null) {
			end = new Integer(-1);
		}
		this.end = end;
	}

	public boolean isInRange(int input) {
		if (this.start <= input) {
			if (this.end == -1 || input <= this.end) {
				return true;
			}
		}

		return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Range [start=" + start + ", end=" + end + "]";
	}

}
