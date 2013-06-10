package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.BillingHistoryDao;
import com.discorp.staff.model.BillingHistory;
import com.discorp.staff.model.Block;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BillingHistoryDaoImpl implements BillingHistoryDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public List<BillingHistory> getBillingHistoryListByDealerId(Integer dealerId) {
       return hibernateTemplate.find("from BillingHistory billingHistory where billingHistory.dealerId=?", dealerId);
    }
}
