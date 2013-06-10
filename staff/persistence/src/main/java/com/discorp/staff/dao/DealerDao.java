package com.discorp.staff.dao;
import com.discorp.staff.model.Dealer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DealerDao {
    public List<Dealer>findAllDealer();
    public void saveDealer(Dealer dealer);
    public List<Dealer> getDealerListByDealerId(Integer dealer);
    public  void updateDealer(Dealer dealer);
}
