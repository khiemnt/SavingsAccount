package com.discorp.savings.service;

import com.discorp.savings.dto.*;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 11:56 AM
 */
public interface SavingsAccountService {

    public SavingsAccountDTO openAccount(int ownerID, Double principal, Date startDate);

    public void deposit(SavingsAccountDTO account, TransactionDTO transactionDTO);

    public void withdraw(SavingsAccountDTO account, TransactionDTO transactionDTO);

    public SavingsAccountDTO getAccount(String accountNumber);

    public Double calculatorEarnedBNAInterest(SavingsAccountDTO accountDTO, Date startDate, Date checkDate);

    public long daysBetween2Dates(Date startDate, Date endDate);

    public void addCheck(String accountNumber, CheckDepositDTO checkDepositDTO);

    public List<CheckDepositDTO> getAllCheckOrder(String accountNumber);

    public OrderDTO createOrder(String accountNumber, OrderDTO orderDTO);

    public List<OrderDTO> getAllOrder(String accountNumber);

    public void checkDepositRecurring(SavingsAccountDTO account, OrderDTO orderDTO, Date checkDate);


    public OrderDTO getOrder(String accountNumber, int orderId);

    public void updateOrder(OrderDTO orderDTO);

    public TransactionDTO addTransaction(TransactionDTO transactionDTO);

    public TransactionDTO getTransaction(int id, String accountNumber);


    public List<TransactionDTO> getListTransactionsHistory(String accountNumber);

    public List<TransactionDTO> getListTransactionsHistory(String accountNumber, boolean isExecute);

    public void executeTransaction(TransactionDTO deposit, Date actualExecutionDate);

    public List<TransactionDTO> getListTransactionsWithPlanDate(String accountNumber, Date getListTransactionsWithPlanDate);

    public void accrueInterest(String accountNumber, Date checkDate);

    public Date getNextDateRecurringByTypeOfFrequency(Date date, OrderDTO.Frequency typeOfFrequency);

    public List<TransactionDTO> getTransactionsExecutedInAPeriod(String accountNumber, Date fromDate, Date checkDate);

    public List<TransactionDTO> getTransactionsIncurringInterestInAPeriod(String accountNumber, Date fromDate, Date toDate);

    public TransactionDTO createTransaction(String accountNumber, Double amount, Date createDate,
                                            Date plannedToExecuteDate, boolean isExecute, boolean isDeposit);

    public void approveOrder(OrderDTO orderDTO);


    public List<OrderDTO> getAllPendingOrders();

    public List<OrderDTO> getAllOrderShouldHaveCreatedTransactionByCheckDate(Date checkDate);

    public void disApproveOrder(OrderDTO orderDTO);

    public void cancelOrder(OrderDTO orderDTO);
    public List<OrderDTO> getAllOrderThatApprovedAndNotExpired(Date checkDate);
}