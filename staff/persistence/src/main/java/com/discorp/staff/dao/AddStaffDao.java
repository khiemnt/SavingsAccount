package com.discorp.staff.dao;

import com.discorp.staff.xml.AccountResponse;
import com.discorp.staff.xml.StaffDTOResponse;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/13/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AddStaffDao
{
    public StaffDTOResponse saveStaff();
    public AccountResponse getListCustomer(int userId,String dealerId);
}
