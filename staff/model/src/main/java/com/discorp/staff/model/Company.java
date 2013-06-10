package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 8/18/12
 * Time: 8:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = false)
    private Integer version;
    @Column(nullable = false, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdated;
    @Column(nullable = false, length = 255, name = "internal_id")
    private String internalId;
    @Column(nullable = true, name = "main_branch_id")
    private Integer mainBranchId;
    @Column(nullable = true, name = "fiscal_year_start_month")
    private Integer fiscalYearStartMonth;
    @Column(nullable = true, length = 255)
    private String description;

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

    public Integer getMainBranchId() {
        return mainBranchId;
    }

    public void setMainBranchId(Integer mainBranchId) {
        this.mainBranchId = mainBranchId;
    }

    public Integer getFiscalYearStartMonth() {
        return fiscalYearStartMonth;
    }

    public void setFiscalYearStartMonth(Integer fiscalYearStartMonth) {
        this.fiscalYearStartMonth = fiscalYearStartMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
