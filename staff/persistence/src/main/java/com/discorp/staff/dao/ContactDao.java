package com.discorp.staff.dao;

import java.util.List;

import com.discorp.staff.model.Contact;
/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ContactDao {
    public List<Contact> findAllContact();
    public void saveContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(Contact contact);
    public List getContactListByUserId(Integer userId);
    public List getContactAddressListByAddressId(Integer addressId);
}
