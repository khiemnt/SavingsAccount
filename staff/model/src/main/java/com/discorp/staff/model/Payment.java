package com.discorp.staff.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 6/29/12
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "slstf_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, name = "start_date")
    private Date startDate;
    @Column(nullable = true, name = "base_rate")
    private Double baseRate;
    @Column(nullable = true, name = "member_rate")
    private Double memberRate;
    @Column(nullable = true, name = "billing_delay")
    private Integer billingDelay;

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(Double baseRate) {
        this.baseRate = baseRate;
    }

    public Double getMemberRate() {
        return memberRate;
    }

    public void setMemberRate(Double memberRate) {
        this.memberRate = memberRate;
    }

    public Integer getBillingDelay() {
        return billingDelay;
    }

    public void setBillingDelay(Integer billingDelay) {
        this.billingDelay = billingDelay;
    }
}
