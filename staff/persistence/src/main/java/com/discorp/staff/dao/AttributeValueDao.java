package com.discorp.staff.dao;

import com.discorp.staff.model.AttributeValue;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 3/7/13
 * Time: 11:39 AM
 */
public interface AttributeValueDao {
    public List<AttributeValue> findAttributeValueById(int attributeId);
}
