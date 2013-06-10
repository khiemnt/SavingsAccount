package com.discorp.staff.dao;

import com.discorp.savings.dto.TransactionDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 9:24 AM
 */
public interface TransactionDao {
    public TransactionDTO save(TransactionDTO transactionDTO);
    public List<TransactionDTO> getListTransactionsHistory(String accountNumber);

    public TransactionDTO getTransaction(int id,String accountNumber);

    public void update(TransactionDTO transactionDTO);

    public List<TransactionDTO> getListTransactionsWithPlanDate(String accountNumber, Date dateReviewTransaction);

    public List<TransactionDTO> getAllTransaction();
}
