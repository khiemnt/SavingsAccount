package com.discorp.staff.model;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/21/12
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "communication_detail")
public class CommunicationDetail {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true)
    private Integer version;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdated;

    @Column(nullable = false, name = "communication_method_id")
    private Integer communicationMethodId;
    @Column(nullable = true, length = 255)
    private String information;
    @Column(nullable = false)
    private Boolean main;
    @Column(nullable = true, length = 255)
    private String extension;
    @Column(nullable = true, name = "address_id")
    private Integer addressId;
    @Column(nullable = true, length = 255, name = "internal_id")
    private String internalId;

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

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

    public Integer getCommunicationMethodId() {
        return communicationMethodId;
    }

    public void setCommunicationMethodId(Integer communicationMethodId) {
        this.communicationMethodId = communicationMethodId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
