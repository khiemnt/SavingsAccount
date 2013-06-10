package com.discorp.staff.dao;

import com.discorp.staff.model.UserPayment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 8:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserPaymentDao {
    public List<UserPayment> getUserPaymentListByDealerId(Integer dealerId);
}
