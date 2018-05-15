/*
 *
 */
package net.vivekkumar.spring.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class User extends ErrorMsg {

    /** The emp id. */
    @Id
    @Column(name = "EMP_ID")
    private Long empId;

    /** The first name. */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /** The last name. */
    @Column(name = "LAST_NAME")
    private String lastName;

    /** The email. */
    @Column(name = "EMAIL", unique = true)
    private String email;

    /** The creation date. */
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    /** The assets. */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Asset> assets;

    /**
     * Gets the assets.
     *
     * @return the assets
     */
    public Set<Asset> getAssets() {
        return assets;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the emp id.
     *
     * @return the emp id
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the assets.
     *
     * @param assets
     *            the new assets
     */
    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate
     *            the new creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the emp id.
     *
     * @param empId
     *            the new emp id
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * Sets the first name.
     *
     * @param firstName
     *            the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName
     *            the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}