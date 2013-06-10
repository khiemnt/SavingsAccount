package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 5/28/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "slstf_user_payment")
public class UserPayment {

    @Id
    @Column(unique = true, nullable = false, name = "customer_id")
    private String customerId;
    @Column(nullable = true, length = 255, name = "payment_id")
    private String paymentId;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;


    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
