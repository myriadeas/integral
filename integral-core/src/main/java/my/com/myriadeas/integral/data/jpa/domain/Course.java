package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the GLCOUR database table.
 * 
 */
@Entity
public class Course extends AbstractLookupDomain {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = true)
	private Patron tutor;

	public Course() {
	}

	public Course(String code, String description) {
		super(code, description);
	}

	public Patron getTutor() {
		return tutor;
	}

	public void setTutor(Patron tutor) {
		this.tutor = tutor;
	}
}