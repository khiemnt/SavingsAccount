package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.MailServerInfoDao;
import com.discorp.staff.model.Dealer;
import com.discorp.staff.model.MailServerInfo;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailServerInfoImpl implements MailServerInfoDao {
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
    public List<MailServerInfo> findAllMailServer() {
        return hibernateTemplate.find("from MailServerInfo c order by c.id desc");

    }

    public List<MailServerInfo> getMailServerListById(Integer webId) {
        return hibernateTemplate.find("select c from MailServerInfo c where c.webId=?", webId);
    }
    public MailServerInfo updateMailServer(MailServerInfo mailServerInfo)
    {
           hibernateTemplate.update(mailServerInfo);
        return mailServerInfo;
    }
    public MailServerInfo saveMailServer(MailServerInfo mailServerInfo)
    {
        hibernateTemplate.save(mailServerInfo);
        return mailServerInfo;
    }
}
