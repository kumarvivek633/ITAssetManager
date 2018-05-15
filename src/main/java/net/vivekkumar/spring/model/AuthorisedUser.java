/*
 *
 */
package net.vivekkumar.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class AuthorisedUser.
 */
@Entity
@Table(name = "AUTHORISED_USERS")
public class AuthorisedUser extends ErrorMsg {

    /** The email. */
    @Id
    @Column(name = "EMAIL")
    private String email;

    /** The password. */
    @Column(name = "PASSWORD")
    private String password;

    /** The activated. */
    @Column(name = "ACTIVATED")
    private boolean activated;

    /** The otp. */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "OTP")
    private Long otp;

    /** The user. */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMP_ID", referencedColumnName = "emp_id")
    private User user;

    /**
     * Gets the activated.
     *
     * @return the activated
     */
    public boolean getActivated() {
        return activated;
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
     * Gets the otp.
     *
     * @return the otp
     */
    public Long getOtp() {
        return otp;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the activated.
     *
     * @param activated
     *            the new activated
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
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
     * Sets the otp.
     *
     * @param otp
     *            the new otp
     */
    public void setOtp(Long otp) {
        this.otp = otp;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user.
     *
     * @param user
     *            the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
