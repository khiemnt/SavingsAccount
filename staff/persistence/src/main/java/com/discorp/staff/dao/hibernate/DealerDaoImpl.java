package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.DealerDao;
import com.discorp.staff.model.Dealer;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DealerDaoImpl implements DealerDao {
    private HibernateTemplate hibernateTemplate;
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public void saveDealer(Dealer dealer) {
        hibernateTemplate.save(dealer);
    }
    public void updateDealer(Dealer dealer) {
        hibernateTemplate.update(dealer);
    }
    public  List<Dealer> getDealerListByDealerId(Integer dealer)
    {
        return hibernateTemplate.find("from Dealer dealer where dealer.webId=?", dealer);
    }
    public List<Dealer>findAllDealer()
    {
        return hibernateTemplate.find("from Dealer dealer order by dealer.webId desc");
    }
}
