package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/24/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "business_entity")
public class BusinessEntity {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true)
    private Integer version;
    @Column(nullable = true, name = "dealerId")
    private Integer dealerId;
    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdated;
    @Column(nullable = true, name = "branch_id")
    private Integer branchId;
    @Column(nullable = true, name = "companyId")
    private Integer companyId;
    @Column(nullable = true, name = "address_id")
    private Integer addressId;
    @Column(nullable = true, name = "language_id")
    private Integer languageId;
    @Column(nullable = true, name = "currency_id")
    private Integer currencyId;
    @Column(nullable = true)
    private Boolean active;
    @Column(nullable = true, name = "verification_code")
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


}
