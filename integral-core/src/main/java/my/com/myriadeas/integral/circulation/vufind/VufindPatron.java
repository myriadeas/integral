package my.com.myriadeas.integral.circulation.vufind;

public class VufindPatron {
	private String id;
	private String firstname;
	private String lastname;
	private String cat_username;
	private String cat_password;
	private String email;
	private String major;
	private String college;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCat_username() {
		return cat_username;
	}
	public void setCat_username(String cat_username) {
		this.cat_username = cat_username;
	}
	public String getCat_password() {
		return cat_password;
	}
	public void setCat_password(String cat_password) {
		this.cat_password = cat_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cat_password == null) ? 0 : cat_password.hashCode());
		result = prime * result
				+ ((cat_username == null) ? 0 : cat_username.hashCode());
		result = prime * result + ((college == null) ? 0 : college.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
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
		VufindPatron other = (VufindPatron) obj;
		if (cat_password == null) {
			if (other.cat_password != null)
				return false;
		} else if (!cat_password.equals(other.cat_password))
			return false;
		if (cat_username == null) {
			if (other.cat_username != null)
				return false;
		} else if (!cat_username.equals(other.cat_username))
			return false;
		if (college == null) {
			if (other.college != null)
				return false;
		} else if (!college.equals(other.college))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VufindPatron [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", cat_username=" + cat_username
				+ ", cat_password=" + cat_password + ", email=" + email
				+ ", major=" + major + ", college=" + college + "]";
	}
	
}