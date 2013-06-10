package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.FirstLightDao;
import com.discorp.staff.model.BusinessEntity;
import com.discorp.staff.model.Customer;
import com.discorp.staff.model.Dealer;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/10/12
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class FirstLightDaoImpl implements FirstLightDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public Dealer getDealerByUidUser(String uid) {
        List<Dealer> lightCustomers= hibernateTemplate.find("select dealer from Dealer dealer, Customer webuser where dealer.id=webuser.dealerId and webuser.uid=?",uid);
        if(null!=lightCustomers)
        {
            if(lightCustomers.size()>0)
            {
                return lightCustomers.get(0);
            }
        }
        return null;
    }
    public void saveCustomer(Customer customer)
    {
        hibernateTemplate.save(customer);
    }
    public List<Customer> findAllCustomer()
    {
        return hibernateTemplate.find("from Customer customer order by customer.webId desc ");
    }

	public int validateVerificationCode(String uid, String verificationCode) {
		List<BusinessEntity> customerList = hibernateTemplate.find("select businessEntity from BusinessEntity businessEntity, Customer customer where businessEntity.webId=customer.businessEntity and customer.uid=?", uid);
		if(null!=customerList)
		{
			if(verificationCode.equals(customerList.get(0).getVerificationCode()))
			{
				customerList.get(0).setActive(true);
				hibernateTemplate.update(customerList.get(0));
				return 1;
			}
			else return 0;
		}
		return 0;
	}
}
