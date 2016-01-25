package cmi.bdo.oauth.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/25/2016
 *         Time: 11:41 PM
 */

@Entity
@Table(schema = "bdo_oauth", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "user_first", nullable = false)
    private String firstName;

    @Column(name = "user_middle")
    private String middleName;

    @Column(name = "user_last", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_active")
    private Integer active;

    @Column(name = "user_ldap")
    private Integer ldap;

    @Column(name = "user_created")
    private String created;

    @Column(name = "user_updated")
    private String updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getLdap() {
        return ldap;
    }

    public void setLdap(Integer ldap) {
        this.ldap = ldap;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
