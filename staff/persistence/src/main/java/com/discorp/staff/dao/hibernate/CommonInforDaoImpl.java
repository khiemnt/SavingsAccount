package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.CommonInforDao;
import com.discorp.staff.model.CommonInfor;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommonInforDaoImpl implements CommonInforDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<CommonInfor> findAllCommonInfor()
    {
        return hibernateTemplate.find("from CommonInfor");
    }
}
