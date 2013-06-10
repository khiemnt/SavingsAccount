package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.PaymentDao;
import com.discorp.staff.model.Payment;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDaoImpl implements PaymentDao {
    private HibernateTemplate hibernateTemplate;
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public void savePayment(Payment payment) {
        hibernateTemplate.save(payment);
    }

    public List<Integer> getPaymentIdList() {
        return hibernateTemplate.find("select distinct payment.dealerId from Payment payment order by payment.dealerId asc");
    }

    public List<Payment> getPaymentListByDealerId(Integer dealerId) {
        return hibernateTemplate.find("from Payment payment where payment.dealerId=?", dealerId);
    }
}
