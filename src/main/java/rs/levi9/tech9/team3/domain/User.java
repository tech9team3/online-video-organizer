package rs.levi9.tech9.team3.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;


import rs.levi9.tech9.team3.web.validation.custom.Password;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5675226269896661922L;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Length(min = 5, max = 15)
	@Column(nullable = false, unique = true)
	private String username;

	@Password // sifra mora da sadrzi izmedju 8 i 20 karaktera, obavezan je jedan specijalan karakter i jedan broj
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Date registrationDate;

	@Column(nullable = false)
	@ManyToMany
	// @JsonManagedReference
	private Set<Role> roles;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
	}

}
