package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import my.com.myriadeas.integral.security.model.SecurityRole;
import my.com.myriadeas.integral.security.model.SecurityUser;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the PATRON database table.
 * 
 */
// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
// @Entity
@MappedSuperclass
public class AbstractUser extends AbstractDomain implements SecurityUser {

	private static final long serialVersionUID = 1L;
	
	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	@Length(min = 6, max = 50)
	@NotBlank
	@Column(unique = true)
	private String username;

	@NotBlank
	@Length(max = 40)
	private String firstname;

	@Length(max = 40)
	private String lastname;

	@NotBlank
	@Length(max = 60)
	private String password;

	@Length(max = 14)
	private String mobilePhone;

	@Email
	private String email;

	@ManyToOne(optional = true)
	private Branch branch;

	@ManyToOne(optional = true)
	private Designation designation;

	@Past
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@ManyToOne(optional = true)
	private Title title;

	@Length(max = 12)
	private String newIC;

	@ManyToOne(optional = true)
	private Race race;

	@ManyToOne(optional = true)
	private Religion religion;

	private Gender gender;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	private Address homeAddress;

	@Length(max = 14)
	private String homePhone;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	private Address officeAddress;

	@Length(max = 14)
	private String officePhone;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Role> roles = new HashSet<Role>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();

	public AbstractUser() {
	}

	public AbstractUser(String username, String password, String firstname,
			Branch branch) {
		this.username = username;
		this.branch = branch;
		this.firstname = firstname;
		this.password = password;
	}

	@PreUpdate
	@PostPersist
	public void postPersist() {
		if (!BCRYPT_PATTERN.matcher(this.getPassword()).matches()) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(this.getPassword());
			this.setPassword(encodedPassword);
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getNewIC() {
		return newIC;
	}

	public void setNewIC(String newIC) {
		this.newIC = newIC;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Override
	@JsonIgnore
	public Set<SecurityRole> getSecurityRoles() {
		Set<SecurityRole> securityRoles = new HashSet<SecurityRole>();
		securityRoles.addAll(getRoles());
		if (this.getUserGroups() != null) {
			for (UserGroup userGroup : this.getUserGroups()) {
				securityRoles.addAll(userGroup.getRoles());
			}
		}
		return securityRoles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;

	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}