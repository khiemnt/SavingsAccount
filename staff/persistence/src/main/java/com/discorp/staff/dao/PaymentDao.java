package com.discorp.staff.dao;
import com.discorp.staff.model.Payment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentDao {
    public void savePayment(Payment payment);
    public List<Integer> getPaymentIdList();
    public List<Payment> getPaymentListByDealerId(Integer dealerId);
}
