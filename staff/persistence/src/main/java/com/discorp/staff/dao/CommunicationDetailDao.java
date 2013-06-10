package com.discorp.staff.dao;

import java.util.List;
import com.discorp.staff.model.CommunicationDetail;
/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommunicationDetailDao {
    public List<CommunicationDetail> findAllCommunicationDetail();
    public void saveCommunicationDetail(CommunicationDetail communicationDetail);
    public void updateCommunicationDetail(CommunicationDetail communicationDetail);
    public void deleteCommunicationDetail(CommunicationDetail communicationDetail);
    public List<CommunicationDetail> getCommunicationDetailListByUid(String uid);
    public List<CommunicationDetail> getCommunicationDetailMainListByUid(String uid);
    public List<CommunicationDetail> getCommunicationDetailByAddressId(Integer addressId);
    public List<CommunicationDetail> getCommunicationDetailByEmail(String email);
}
