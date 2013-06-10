package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.CommunicationDetailDao;
import com.discorp.staff.model.CommunicationDetail;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommunicationDetailDaoImpl implements CommunicationDetailDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<CommunicationDetail> findAllCommunicationDetail()
    {
        return hibernateTemplate.find("from CommunicationDetail communicationDetail order by communicationDetail.webId desc");
    }
    public void saveCommunicationDetail(CommunicationDetail communicationDetail)
    {
        hibernateTemplate.save(communicationDetail);
    }
    public void updateCommunicationDetail(CommunicationDetail communicationDetail)
    {
        hibernateTemplate.update(communicationDetail);
    }
    public void deleteCommunicationDetail(CommunicationDetail communicationDetail)
    {
        hibernateTemplate.delete(communicationDetail);
    }
    public List<CommunicationDetail> getCommunicationDetailListByUid(String uid)
    {
       return hibernateTemplate.find("select com_detail from Customer customer, CommunicationDetail com_detail where customer.addressId=com_detail.addressId and com_detail.main=false and customer.uid=?", uid);
    }
    public List<CommunicationDetail> getCommunicationDetailMainListByUid(String uid)
    {
        return hibernateTemplate.find("select com_detail from Customer customer, CommunicationDetail com_detail where customer.addressId=com_detail.addressId and com_detail.main=true and customer.uid=?", uid);
    }
    public List<CommunicationDetail> getCommunicationDetailByAddressId(Integer addressId)
    {
        return hibernateTemplate.find("from CommunicationDetail communicationDetail  where communicationDetail.addressId=?", addressId);
    }
    public List<CommunicationDetail> getCommunicationDetailByEmail(String email)
    {
        String sql = "from CommunicationDetail communicationDetail where communicationDetail.communicationMethodId = 3 and communicationDetail.information = '" +email + "'";
        return hibernateTemplate.find(sql);
    }
}
