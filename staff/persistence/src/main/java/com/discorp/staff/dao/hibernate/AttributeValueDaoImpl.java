package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.AttributeValueDao;
import com.discorp.staff.model.AttributeValue;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 3/7/13
 * Time: 11:44 AM
 */
public class AttributeValueDaoImpl implements AttributeValueDao{
    private HibernateTemplate hibernateTemplate;
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<AttributeValue> findAttributeValueById(int attributeId) {
        return hibernateTemplate.find("from AttributeValue attributeValue where attributeValue.attributeId = ? order by attributeValue.webId desc",attributeId);
    }
}
