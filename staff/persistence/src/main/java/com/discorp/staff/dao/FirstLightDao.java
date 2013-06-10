package com.discorp.staff.dao;
import com.discorp.staff.model.Customer;
import com.discorp.staff.model.Dealer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/10/12
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FirstLightDao {
    public Dealer getDealerByUidUser(String uid);
    public void saveCustomer(Customer customer);
    public List<Customer> findAllCustomer();
	public int validateVerificationCode(String uid, String verificationCode);
}
