package my.com.myriadeas.dao.rest.config;

import java.util.Date;

import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON Properties to ignore during deserialization because auditable fields
 * should auto generate by system rather from input
 * @author hutingung
 *
 */
public abstract class AbstractAuditableJsonProperties {
	@JsonIgnore
	private AbstractUser createdBy;
	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private AbstractUser lastModifiedBy;

	@JsonIgnore
	private Date lastModifiedDate;

	@JsonProperty("createdBy")
	public abstract AbstractUser getCreatedBy();

	@JsonIgnore
	public abstract void setCreatedBy(Patron patron);

	@JsonProperty("createdDate")
	public abstract DateTime getCreatedDate();

	@JsonIgnore
	public abstract void setCreatedDate(DateTime createdDate);

	@JsonProperty("lastModifiedBy")
	public abstract AbstractUser getLastModifiedBy();

	@JsonIgnore
	public abstract void setLastModifiedBy(Patron patron);

	@JsonProperty("lastModifiedDate")
	public abstract DateTime getLastModifiedDate();

	@JsonIgnore
	public abstract void setLastModifiedDate(DateTime lastModifiedDate);
}
