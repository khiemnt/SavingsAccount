package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/10/12
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "web_user")
public class Customer {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;

    @Column(nullable = true)
    private Integer version;

    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;

    @Column(nullable = true, length = 255)
    private String description;

    @Column(nullable = true, length = 255, name = "internal_id")
    private String internalId;

    @Column(nullable = true, name = "business_entity")
    private Integer businessEntity;

    @Column(nullable = true, name = "address_id")
    private Integer addressId;

    @Column(nullable = true, name = "role_id")
    private Integer roleId;

    @Column(nullable = true, length = 255)
    private String uid;

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public Integer getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(Integer businessEntity) {
        this.businessEntity = businessEntity;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}