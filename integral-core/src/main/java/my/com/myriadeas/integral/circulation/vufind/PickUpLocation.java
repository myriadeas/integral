package my.com.myriadeas.integral.circulation.vufind;


public class PickUpLocation {
	private String locationId;
	private String locationDisplay;
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationDisplay() {
		return locationDisplay;
	}
	public void setLocationDisplay(String locationDisplay) {
		this.locationDisplay = locationDisplay;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((locationDisplay == null) ? 0 : locationDisplay.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
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
		PickUpLocation other = (PickUpLocation) obj;
		if (locationDisplay == null) {
			if (other.locationDisplay != null)
				return false;
		} else if (!locationDisplay.equals(other.locationDisplay))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PickUpLocation [locationId=" + locationId
				+ ", locationDisplay=" + locationDisplay + "]";
	}
	
		
}
