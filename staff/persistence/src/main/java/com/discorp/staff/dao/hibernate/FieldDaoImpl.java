package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.FieldDao;
import com.discorp.staff.model.*;

import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate3.HibernateTemplate;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/11/12
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldDaoImpl implements FieldDao
{
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public List getListField(int blockId)
    {
        String sql = "select field.fieldId,field.fieldName,field.editType from Field field where  field.blockId=? order by field.position asc";
        List results = hibernateTemplate.find(sql, blockId);
        return results;
    }
    public List getListFieldOrderAsc(Integer blockId)
    {
        return hibernateTemplate.find("from Field field where  field.blockId=? order by field.position asc", blockId);

    }
    public List getResults(String sql)
    {
        List results = hibernateTemplate.find(sql);
        return results;
    }
    public List getResults(String sql,Object[] objects)
    {
        List results = hibernateTemplate.find(sql,objects);
        return results;
    }

    public Field getField(Integer fieldId)
    {
        return (Field) hibernateTemplate.find("from Field field where field.fieldId=?", fieldId).get(0);
    }
    public List<Field>getFieldParent(Integer fieldId)
    {
        return hibernateTemplate.find("from Field field where field.parentId=?", fieldId);
    }

    public List checkValidateInvitationCode(String invitationCode)
    {
        String sql = "select customer,fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
        sql += " and fieldValue.fieldId=101 and fieldValue.fieldValue=?";
        return hibernateTemplate.find(sql, invitationCode);

    }

}
