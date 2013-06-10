package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.CustomerDao;
import com.discorp.staff.model.Customer;
import com.discorp.staff.model.FieldValue;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerDaoImpl implements CustomerDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List getCustomerListByUserId(Integer userId)
    {
        return hibernateTemplate.find("from Customer customer  where  customer.webId=?", userId);

    }
    public List getCustomerContactListByUserId(Integer userId)
    {
        return hibernateTemplate.find("select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.webId=?", userId);
    }
    public List getCustomerContactListByDealerId(Integer dealerId)
    {
        return hibernateTemplate.find("select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and  customer.uid != 'none' and  customer.uid != 'archive' and customer.dealerId=?", dealerId);
    }
    public List getCustomerListByDealerId(Integer dealerId)
    {
        return hibernateTemplate.find("select customer from Customer customer where customer.dealerId=? and customer.uid != 'none' and customer.uid != 'archive' order by customer.webId asc", dealerId);
    }

    public List<FieldValue> getCustomerFieldValueListByWebId(Integer webId)
    {
        String sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
        sql += " and fieldValue.fieldId=16";
        sql += " and customer.webId = ?";

        List<FieldValue> listFieldValue = hibernateTemplate.find(sql, webId);
        return  listFieldValue;
    }
    public List<FieldValue> getCustomerListFromCityOrImage(Integer webId)
    {
        String sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
        sql += " and (fieldValue.fieldId=16 or  fieldValue.fieldId=102) ";
        sql += " and customer.webId = ?";

        List<FieldValue> listFieldValue = hibernateTemplate.find(sql, webId);
        return  listFieldValue;
    }
    public List<Customer> getCustomerListByMemberId(String memberId)
    {
        return hibernateTemplate.find("from  Customer customer where customer.uid=?", memberId);
    }
    public List<Customer> findAllCustomer()
    {
        return hibernateTemplate.find("from Customer customer order by customer.webId desc ");
    }
    public void saveCustomer(Customer customer)
    {
        hibernateTemplate.save(customer);
    }
    public void updateCustomer(Customer customer)
    {
        hibernateTemplate.update(customer);
    }
    public void deleteCustomer(Customer customer)
    {
        hibernateTemplate.delete(customer);
    }
    public List getListStaffReceiveSuggestion(Integer dealerId)
    {
        String sql="select customer,contact from Customer customer,Contact contact,FieldValue fieldValue where" +
                " customer.addressId=contact.addressId and customer.webId=fieldValue.userId and customer.uid is not null and " +
                " customer.uid != 'none' and  customer.uid != 'archive' and customer.dealerId=? and fieldValue.fieldId=104" +
                " and fieldValue.fieldValue like 'true'";
        return hibernateTemplate.find(sql,dealerId);

    }
}
