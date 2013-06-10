package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.AddressDao;
import com.discorp.staff.model.BusinessEntityAddress;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.discorp.staff.model.Address;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddressDaoImpl implements AddressDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public List<BusinessEntityAddress> getBusinessEntityAddressList(Integer businessEntity) {
        return hibernateTemplate.find("from BusinessEntityAddress businessEntityAddress where businessEntityAddress.businessEntityId=?", businessEntity);
    }


    public List<Address> findAllAddress() {
        return hibernateTemplate.find("from Address address order by address.webId desc");

    }

    public List getAddressListById(Integer addressId) {
        return hibernateTemplate.find("from Address address where address.webId=?", addressId);

    }

    public List getAddressListActiveById(Integer addressId) {
        return hibernateTemplate.find("from Address address where address.webId=? and address.active=1", addressId);

    }
    public void saveAddress(Address address)
    {
        hibernateTemplate.save(address);
    }
    public void updateAddress(Address address)
    {
        hibernateTemplate.update(address);
    }
    public void deleteAddress(Address address)
    {
        hibernateTemplate.delete(address);
    }
    public void deleteBusinessEntityAddress(BusinessEntityAddress businessEntityAddress)
    {
        hibernateTemplate.delete(businessEntityAddress);
    }
    public void saveBusinessEntityAddress(BusinessEntityAddress businessEntityAddress)
    {
        hibernateTemplate.save(businessEntityAddress);
    }
}
