package com.discorp.staff.dao;

import com.discorp.staff.model.BillingHistory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BillingHistoryDao {
    List<BillingHistory>getBillingHistoryListByDealerId(Integer dealerId);
}
