package my.com.myriadeas.integral.circulation.vufind;

import java.util.List;

public class Holding {
	private String id;
	private boolean availability;
	private String status;
	private String location;
	private String reserve;
	private String callnumber;
	private String duedate;
	private String returnDate;
	private String number;
	private String requests_placed;
	private String barcode;
	private List<String> notes;
	private List<String> summary;
	private List<String> supplements;
	private List<String> indexes;
	private boolean is_holdable;
	private String holdtype;
	//private boolean addLink;
	private String item_id;
	private String holdOverride;
	//private String addStorageRetrievalRequestLink ;
	//private String addILLRequestLink;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRequests_placed() {
		return requests_placed;
	}
	public void setRequests_placed(String requests_placed) {
		this.requests_placed = requests_placed;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public List<String> getNotes() {
		return notes;
	}
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	public List<String> getSummary() {
		return summary;
	}
	public void setSummary(List<String> summary) {
		this.summary = summary;
	}
	public List<String> getSupplements() {
		return supplements;
	}
	public void setSupplements(List<String> supplements) {
		this.supplements = supplements;
	}
	public List<String> getIndexes() {
		return indexes;
	}
	public void setIndexes(List<String> indexes) {
		this.indexes = indexes;
	}
	public boolean isIs_holdable() {
		return is_holdable;
	}
	public void setIs_holdable(boolean is_holdable) {
		this.is_holdable = is_holdable;
	}
	public String getHoldtype() {
		return holdtype;
	}
	public void setHoldtype(String holdtype) {
		this.holdtype = holdtype;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getHoldOverride () {
		return holdOverride;
	}
	public void setHoldOverride(String holds_mode) {
		this.holdOverride = holds_mode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (availability ? 1231 : 1237);
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result
				+ ((callnumber == null) ? 0 : callnumber.hashCode());
		result = prime * result + ((duedate == null) ? 0 : duedate.hashCode());
		result = prime * result
				+ ((holdOverride == null) ? 0 : holdOverride.hashCode());
		result = prime * result
				+ ((holdtype == null) ? 0 : holdtype.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indexes == null) ? 0 : indexes.hashCode());
		result = prime * result + (is_holdable ? 1231 : 1237);
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((requests_placed == null) ? 0 : requests_placed.hashCode());
		result = prime * result + ((reserve == null) ? 0 : reserve.hashCode());
		result = prime * result
				+ ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result
				+ ((supplements == null) ? 0 : supplements.hashCode());
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
		Holding other = (Holding) obj;
		if (availability != other.availability)
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (callnumber == null) {
			if (other.callnumber != null)
				return false;
		} else if (!callnumber.equals(other.callnumber))
			return false;
		if (duedate == null) {
			if (other.duedate != null)
				return false;
		} else if (!duedate.equals(other.duedate))
			return false;
		if (holdOverride == null) {
			if (other.holdOverride != null)
				return false;
		} else if (!holdOverride.equals(other.holdOverride))
			return false;
		if (holdtype == null) {
			if (other.holdtype != null)
				return false;
		} else if (!holdtype.equals(other.holdtype))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indexes == null) {
			if (other.indexes != null)
				return false;
		} else if (!indexes.equals(other.indexes))
			return false;
		if (is_holdable != other.is_holdable)
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (requests_placed == null) {
			if (other.requests_placed != null)
				return false;
		} else if (!requests_placed.equals(other.requests_placed))
			return false;
		if (reserve == null) {
			if (other.reserve != null)
				return false;
		} else if (!reserve.equals(other.reserve))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (supplements == null) {
			if (other.supplements != null)
				return false;
		} else if (!supplements.equals(other.supplements))
			return false;
		return true;
	}
	
	
}
