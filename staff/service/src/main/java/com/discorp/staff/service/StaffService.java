package com.discorp.staff.service;

import com.discorp.staff.xml.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/17/12
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface StaffService {
    public StaffDTOResponse getListBlockField(StaffBlockRequest staffBlockRequest);

    public StaffDTOResponse getStaffDetail(String memberId, int userId, String userLogin, String role);

    public StaffDTOResponse saveListField(StaffDetailAddRequest staffDetailAddRequest);

    public StaffDTOResponse getRole(String memberId);

    public StaffDTOResponse getInformationAccount(int userId);

    public StaffDTOResponse getListFieldMutileRow(int blockId);

    public BlockResponse isAddAnother(int blockId);

    public StaffDTOResponse deleteMultiField(int userId, int fieldId, int group);

    public StaffDTOResponse addStaff();

    public AccountResponse getListCustomer(int userId, String dealerId);
}
