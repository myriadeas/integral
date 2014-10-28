package my.com.myriadeas.integral.assetmanager.domain.http;


public class ReleaseItemResponse implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isSuccessful;
	private String message;
	private String itemIdentifier;
	
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getItemIdentifier() {
		return itemIdentifier;
	}
	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isSuccessful ? 1231 : 1237);
		result = prime * result
				+ ((itemIdentifier == null) ? 0 : itemIdentifier.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		ReleaseItemResponse other = (ReleaseItemResponse) obj;
		if (isSuccessful != other.isSuccessful)
			return false;
		if (itemIdentifier == null) {
			if (other.itemIdentifier != null)
				return false;
		} else if (!itemIdentifier.equals(other.itemIdentifier))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReleaseItemResponse [isSuccessful=" + isSuccessful
				+ ", message=" + message + ", itemIdentifier=" + itemIdentifier
				+ "]";
	}


	
}
