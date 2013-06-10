package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.CardInfoDao;
import com.discorp.staff.model.CardInfor;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class CardInfoDaoImpl implements CardInfoDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public void saveCard(CardInfor card) {
        hibernateTemplate.save(card);
    }
    public List getListCardById(Integer id)
    {
       return hibernateTemplate.find("from CardInfor cardInfo  where cardInfo.webId=? ", id);
    }
    public void updateCard(CardInfor card)
    {
        hibernateTemplate.update(card);
    }
    public List<CardInfor>findAllCardInfor()
    {
        return hibernateTemplate.find("from CardInfor cardInfo order by cardInfo.webId desc");
    }
}
