package com.discorp.staff.dao;

import com.discorp.staff.model.Address;
import com.discorp.staff.model.BusinessEntityAddress;

import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 8:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AddressDao {
    public List<Address> findAllAddress();
    public List getAddressListById(Integer addressId);
    public List getAddressListActiveById(Integer addressId);
    public List getBusinessEntityAddressList(Integer businessEntity);
    public void saveAddress(Address address);
    public void updateAddress(Address address);
    public void deleteAddress(Address address);
    public void deleteBusinessEntityAddress(BusinessEntityAddress businessEntityAddress);
    public void saveBusinessEntityAddress(BusinessEntityAddress businessEntityAddress);
}
