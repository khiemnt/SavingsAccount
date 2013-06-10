package com.discorp.staff.service;

import com.discorp.staff.xml.AccountResponse;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonUserService {
    public AccountResponse findAll(String dealerId);
    public int isValidate(int userId, String userName, String firstName, String lastName, String email);
    public int updateUser(int userId, String memberId);
    public AccountResponse searchUser(String name, String dealerId, Boolean isAchive);

    /**
     * get all list receive mail from
     * @param dealerId
     * @return
     */
    public AccountResponse getListStaffReceiveSuggestion(Integer dealerId);
}
