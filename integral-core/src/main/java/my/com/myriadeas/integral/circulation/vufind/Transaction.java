package my.com.myriadeas.integral.circulation.vufind;

public class Transaction {
	private String duedate;
	private String id;
	private String barcode;
	private int renew;
	private int renewLimit;
	private int request;
	private String volume;
	private String publication_year;
	private boolean renewable;
	private String message;
	private String title;
	private String item_id;
	private String institution_name;
	
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getRenew() {
		return renew;
	}
	public void setRenew(int renew) {
		this.renew = renew;
	}
	public int getRenewLimit() {
		return renewLimit;
	}
	public void setRenewLimit(int renewLimit) {
		this.renewLimit = renewLimit;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getPublication_year() {
		return publication_year;
	}
	public void setPublication_year(String publication_year) {
		this.publication_year = publication_year;
	}
	public boolean isRenewable() {
		return renewable;
	}
	public void setRenewable(boolean renewable) {
		this.renewable = renewable;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getInstitution_name() {
		return institution_name;
	}
	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((duedate == null) ? 0 : duedate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((institution_name == null) ? 0 : institution_name.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime
				* result
				+ ((publication_year == null) ? 0 : publication_year.hashCode());
		result = prime * result + renew;
		result = prime * result + renewLimit;
		result = prime * result + (renewable ? 1231 : 1237);
		result = prime * result + request;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Transaction other = (Transaction) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (duedate == null) {
			if (other.duedate != null)
				return false;
		} else if (!duedate.equals(other.duedate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (institution_name == null) {
			if (other.institution_name != null)
				return false;
		} else if (!institution_name.equals(other.institution_name))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (publication_year == null) {
			if (other.publication_year != null)
				return false;
		} else if (!publication_year.equals(other.publication_year))
			return false;
		if (renew != other.renew)
			return false;
		if (renewLimit != other.renewLimit)
			return false;
		if (renewable != other.renewable)
			return false;
		if (request != other.request)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [duedate=" + duedate + ", id=" + id + ", barcode="
				+ barcode + ", renew=" + renew + ", renewLimit=" + renewLimit
				+ ", request=" + request + ", volume=" + volume
				+ ", publication_year=" + publication_year + ", renewable="
				+ renewable + ", message=" + message + ", title=" + title
				+ ", item_id=" + item_id + ", institution_name="
				+ institution_name + "]";
	}
	
	
}
