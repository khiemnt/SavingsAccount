package com.discorp.staff.dao;

import com.discorp.savings.dto.CheckDepositDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 10:30 AM
 */
public interface CheckDao {
    public void add(String accountNumber, CheckDepositDTO checkDepositDTO);
    public List<CheckDepositDTO> getAllCheckOrder(String accountNumber);
}
