package com.discorp.staff.dao;

import com.discorp.staff.model.FieldValue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FieldValueDao  {
    public FieldValue getFieldValue(Integer fieldValueId);
    public List<FieldValue> getListFieldValueByUserId(Integer userId);
    public List<FieldValue> getFieldValueListByUserIdAndRole(Integer userId,String role);
    public List<FieldValue> getListFieldValuePassWord(String uid);
    public void saveFieldValue(FieldValue fieldValue);
    public void updateFieldValue(FieldValue fieldValue);
    public void deleteFieldValue(FieldValue fieldValue);
    public List<FieldValue>getListFieldValueUser(Integer userId,Integer fieldId, Integer group);
    public List<FieldValue> getListFieldValueByMemberId(String memberId);
    public List<Integer>getGroupListByUserId(Integer field_id,Integer userId);
    public List<Integer>getIdListByUserIdAndGroup(Integer field_id,Integer userId,Integer group);
    public List<Integer>getFieldIdListByUserIdAndGroup(Integer field_id,Integer userId,Integer group);
}
