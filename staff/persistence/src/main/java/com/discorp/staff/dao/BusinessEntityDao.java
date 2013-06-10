package com.discorp.staff.dao;

import com.discorp.staff.model.BusinessEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BusinessEntityDao {
    public List<BusinessEntity> findAllBusinessEntity();
    public List getBusinessEntityListByUserId(Integer userId);
    public void saveBusinessEntity(BusinessEntity businessEntity);
    public void deleteBusinessEntity(BusinessEntity businessEntity);
    public List getListBusinessEntityFromId(Integer businessId);
}
