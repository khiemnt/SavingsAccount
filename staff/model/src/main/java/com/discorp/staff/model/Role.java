package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true)
    private Integer version;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdated;
    @Column(nullable = true, length = 255, name = "internal_id")
    private String internalId;
    @Column(nullable = true, length = 255)
    private String description;
    @Column(nullable = true, name = "contact_role")
    private Boolean contactRole;
    @Column(nullable = true, name = "user_role")
    private Boolean userRole;

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

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getContactRole() {
        return contactRole;
    }

    public void setContactRole(Boolean contactRole) {
        this.contactRole = contactRole;
    }

    public Boolean getUserRole() {
        return userRole;
    }

    public void setUserRole(Boolean userRole) {
        this.userRole = userRole;
    }
}
