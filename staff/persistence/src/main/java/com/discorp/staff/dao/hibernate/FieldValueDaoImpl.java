package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.FieldValueDao;
import com.discorp.staff.model.FieldValue;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class FieldValueDaoImpl implements FieldValueDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public FieldValue getFieldValue(Integer fieldValueId)
    {
        return (FieldValue) hibernateTemplate.find("from FieldValue value where value.fieldValueId=?", fieldValueId).get(0);
    }

    public List<FieldValue> getListFieldValueByUserId(Integer userId)
    {
        return hibernateTemplate.find("from FieldValue value where value.userId=?", userId);
    }

    public List<FieldValue> getListFieldValuePassWord(String uid)
    {
        return hibernateTemplate.find("select a from FieldValue a,Customer b where b.uid=? and a.userId=b.webId and a.fieldId=101", uid);
    }

    public void saveFieldValue(FieldValue fieldValue)
    {
        hibernateTemplate.save(fieldValue);
    }

    public void updateFieldValue(FieldValue fieldValue)
    {
        hibernateTemplate.update(fieldValue);
    }
    public void deleteFieldValue(FieldValue fieldValue)
    {
        hibernateTemplate.delete(fieldValue);
    }

    public List<FieldValue>getListFieldValueUser(Integer userId,Integer fieldId, Integer group)
    {
        String sql = "from FieldValue value where value.userId=? and value.fieldId=? and value.groupMulti=?";
        List<FieldValue> list = hibernateTemplate.find(sql, userId, fieldId, group);
        return list;
    }

    public List getListFieldValueByMemberId(String memberId)
    {
        String sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId and fieldValue.fieldId=99";
        sql += " and customer.uid = ?";
        List list = hibernateTemplate.find(sql,memberId);
        return list;
    }
    public List<FieldValue> getFieldValueListByUserIdAndRole(Integer userId,String role)
    {
        String sql="from FieldValue fieldValue where fieldValue.userId=" + userId + " and fieldValue.fieldValue LIKE '%"+role+"%'";
       return hibernateTemplate.find(sql);
    }
    public List<Integer>getGroupListByUserId(Integer fieldId,Integer userId)
    {
        String sql = "select value.groupMulti from FieldValue value where value.fieldId=" + fieldId;
        sql += " and value.userId=" + userId;
        return  hibernateTemplate.find(sql);
    }
    public List<Integer>getIdListByUserIdAndGroup(Integer fieldId,Integer userId,Integer group)
    {
        String sql = "select value.fieldValueId from FieldValue value where value.fieldId=" + fieldId + " and value.userId=" + userId;
        sql += " and value.groupMulti=" + group;
        return hibernateTemplate.find(sql);
    }
    public List<Integer>getFieldIdListByUserIdAndGroup(Integer fieldId,Integer userId,Integer group)
    {
        String sql = "select value.fieldId from FieldValue value where value.fieldId=" + fieldId + " and value.userId=" + userId;
        sql += " and value.groupMulti=" + group;
        return hibernateTemplate.find(sql);
    }
}
