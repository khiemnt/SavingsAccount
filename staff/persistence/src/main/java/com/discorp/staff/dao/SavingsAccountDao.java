package com.discorp.staff.dao;

import com.discorp.savings.dto.SavingsAccountDTO;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 12:06 PM
 */
public interface SavingsAccountDao {

    SavingsAccountDTO getAccountByAccountNumber(String accountNumber);

    void save(SavingsAccountDTO account);
}
