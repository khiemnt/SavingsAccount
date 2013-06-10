package com.discorp.staff.dao;
import  com.discorp.staff.model.CardInfor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CardInfoDao {
    public List<CardInfor>findAllCardInfor();
    public void saveCard(CardInfor card);
    public void updateCard(CardInfor card);
    public List getListCardById(Integer dealerId);
}
