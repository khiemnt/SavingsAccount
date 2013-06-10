package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.ContactDao;
import com.discorp.staff.model.Contact;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContactDaoImpl implements ContactDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void saveContact(Contact contact) {
        hibernateTemplate.save(contact);
    }

    public void updateContact(Contact contact) {
        hibernateTemplate.update(contact);
    }

    public List<Contact> findAllContact() {
        return hibernateTemplate.find("from Contact contact order by contact.webId desc ");
    }

    public List getContactListByUserId(Integer userId) {
        return hibernateTemplate.find("select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.id=?", userId);

    }
    public List getContactAddressListByAddressId(Integer addressId)
    {
       return hibernateTemplate.find("from Contact contact where contact.addressId=?", addressId);
    }
    public void deleteContact(Contact contact)
    {
        hibernateTemplate.delete(contact);
    }

}
