package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.UserPaymentDao;
import com.discorp.staff.model.UserPayment;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserPaymentDaoImpl implements UserPaymentDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<UserPayment> getUserPaymentListByDealerId(Integer dealerId)
    {
        List<UserPayment> listUserPayment = (List<UserPayment>) hibernateTemplate.findByCriteria(
                DetachedCriteria.forClass(UserPayment.class)
                        .add(Restrictions.eq("dealerId", dealerId)));
        return listUserPayment;
    }
}
