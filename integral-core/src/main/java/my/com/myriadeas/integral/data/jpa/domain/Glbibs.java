package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The persistent class for the GLBIBS database table.
 * 
 */
@Entity

public class Glbibs extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	@Length(max = 20)
	@NotBlank
	private String gl48source;

	@Column(name = "gl48desc")
	@Length(max = 50)
	private String description;

	public Glbibs() {

	}

	public Glbibs(String gl48source) {
		super();
		this.gl48source = gl48source;
	}

	public String getGl48source() {
		return gl48source;
	}

	public void setGl48source(String gl48source) {
		this.gl48source = gl48source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((gl48source == null) ? 0 : gl48source.hashCode());
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
		Glbibs other = (Glbibs) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gl48source == null) {
			if (other.gl48source != null)
				return false;
		} else if (!gl48source.equals(other.gl48source))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Glbibs [gl48source=" + gl48source + ", description="
				+ description + "]";
	}

}