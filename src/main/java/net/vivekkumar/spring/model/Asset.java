/*
 *
 */
package net.vivekkumar.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The Class Asset.
 */
@Entity
@Table(name = "ASSET_ALLOCATION")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Asset extends ErrorMsg {

    /** The id. */
    @Id
    @GeneratedValue
    long id;

    /** The asset id. */
    @Column(name = "ASSET_ID")
    private String assetId;

    /** The asset type. */
    @Column(name = "ASSET_TYPE")
    private String assetType;

    /** The allocated on. */
    @Column(name = "ALLOCATED_ON")
    private Date allocatedOn;

    /** The returned on. */
    @Column(name = "RETURNED_ON")
    private Date returnedOn;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "EMP_ID", nullable = false)
    private User user;

    /** The emp id. */
    @Transient
    private Long empId;

    /**
     * Gets the allocated on.
     *
     * @return the allocated on
     */
    public Date getAllocatedOn() {
        return allocatedOn;
    }

    /**
     * Gets the asset id.
     *
     * @return the asset id
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Gets the asset type.
     *
     * @return the asset type
     */
    public String getAssetType() {
        return assetType;
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
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the returned on.
     *
     * @return the returned on
     */
    public Date getReturnedOn() {
        return returnedOn;
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
     * Sets the allocated on.
     *
     * @param allocatedOn
     *            the new allocated on
     */
    public void setAllocatedOn(Date allocatedOn) {
        this.allocatedOn = allocatedOn;
    }

    /**
     * Sets the asset id.
     *
     * @param assetId
     *            the new asset id
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * Sets the asset type.
     *
     * @param assetType
     *            the new asset type
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
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
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the returned on.
     *
     * @param returnedOn
     *            the new returned on
     */
    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
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
