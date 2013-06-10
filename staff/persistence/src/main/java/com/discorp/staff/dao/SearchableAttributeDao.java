package com.discorp.staff.dao;

import com.discorp.staff.model.SearchableAttribute;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 3/7/13
 * Time: 11:32 AM
 */
public interface SearchableAttributeDao {
    public List<SearchableAttribute> findAllSeachabel();
	public List<SearchableAttribute> searchByAttributeName(String attribute);
	public List<String> searchCustomerMarketing(String query);
}
