package com.discorp.staff.service;

import com.discorp.staff.xml.CompanyResponse;
import com.discorp.staff.xml.EditEntryTextRequest;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {
    public CompanyResponse getAdminList();
    public CompanyResponse getBillingHistoryListByDealerId(Integer dealerId);
    public CompanyResponse getPaymentList();
    public CompanyResponse getEntryTextList();
    public CompanyResponse updateEntryText(EditEntryTextRequest editEntryTextRequest);
}
