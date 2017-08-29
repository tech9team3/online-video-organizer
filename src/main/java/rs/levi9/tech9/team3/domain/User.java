package rs.levi9.tech9.team3.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import rs.levi9.tech9.team3.web.validation.custom.Password;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    @Password
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date registrationDate;

    @Column(nullable = true)
    private Date banExpirationDate;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
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

    public Date getBanExpirationDate() {
        return banExpirationDate;
    }

    public void setBanExpirationDate(Date banExpirationDate) {
        this.banExpirationDate = banExpirationDate;
    }

    public User() {
    }

}
