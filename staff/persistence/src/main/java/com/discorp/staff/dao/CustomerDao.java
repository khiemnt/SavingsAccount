package com.discorp.staff.dao;

import com.discorp.staff.model.Customer;
import com.discorp.staff.model.FieldValue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerDao {
    public List<Customer> getCustomerListByUserId(Integer userId);
    public List getCustomerContactListByDealerId(Integer dealerId);
    public List getCustomerListByDealerId(Integer dealerId);
    public List<FieldValue> getCustomerFieldValueListByWebId(Integer webId);
    public List<FieldValue> getCustomerListFromCityOrImage(Integer webId);
    public List<Customer> getCustomerListByMemberId(String memberId);
    public List<Customer> findAllCustomer();
    public List getCustomerContactListByUserId(Integer userId);
    public void saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public List getListStaffReceiveSuggestion(Integer dealerId);
}
