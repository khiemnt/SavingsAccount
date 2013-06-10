package com.discorp.staff.dao;

import java.util.List;
import com.discorp.staff.model.CommonInfor;
/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/6/12
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonInforDao {
    public List<CommonInfor> findAllCommonInfor();
}
