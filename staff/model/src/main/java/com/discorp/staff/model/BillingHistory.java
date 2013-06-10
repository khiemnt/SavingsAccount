package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 10/3/12
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "slstf_billing_history")
public class BillingHistory {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private Integer webId;
    @Column(nullable = true, name = "from_date")
    private String from;
    @Column(nullable = true, name = "to_date")
    private String to;
    @Column(nullable = true)
    private Double amount;
    @Column(nullable = true)
    private String note;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }
}
