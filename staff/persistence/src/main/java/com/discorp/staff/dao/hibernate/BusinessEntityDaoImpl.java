package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.BusinessEntityDao;
import com.discorp.staff.model.BusinessEntity;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class BusinessEntityDaoImpl implements BusinessEntityDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void saveBusinessEntity(BusinessEntity businessEntity)
    {
        hibernateTemplate.save(businessEntity);
    }
    public List<BusinessEntity> findAllBusinessEntity()
    {
        return   hibernateTemplate.find("from BusinessEntity businessEntity order by businessEntity.webId desc ");
    }
    public List getBusinessEntityListByUserId(Integer userId)
    {
        String sqlBussinessAddress = "select businessEntity from Customer customer,BusinessEntityAddress businessEntity where customer.businessEntity=businessEntity.businessEntityId and customer.webId=" + userId;
        return hibernateTemplate.find(sqlBussinessAddress);
    }
    public List getListBusinessEntityFromId(Integer businessId)
    {
       return hibernateTemplate.find("from BusinessEntity businessEntity  where businessEntity.webId=?", businessId);
    }
    public void deleteBusinessEntity(BusinessEntity businessEntity)
    {
        hibernateTemplate.delete(businessEntity);
    }

}
