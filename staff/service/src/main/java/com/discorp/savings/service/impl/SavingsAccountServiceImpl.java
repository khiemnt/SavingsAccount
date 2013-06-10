package com.discorp.savings.service.impl;

import com.discorp.savings.dto.CheckDepositDTO;
import com.discorp.savings.dto.OrderDTO;
import com.discorp.savings.dto.SavingsAccountDTO;
import com.discorp.savings.dto.TransactionDTO;
import com.discorp.savings.service.SavingsAccountService;
import com.discorp.savings.service.Utils;
import com.discorp.staff.dao.CheckDao;
import com.discorp.staff.dao.OrderDao;
import com.discorp.staff.dao.SavingsAccountDao;
import com.discorp.staff.dao.TransactionDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khiemnt
 * Date: 5/24/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccountServiceImpl implements SavingsAccountService {

    private SavingsAccountDao savingsAccountDAO;
    private TransactionDao transactionDao;
    private CheckDao checkDao;
    private OrderDao orderDao;

    public SavingsAccountServiceImpl(SavingsAccountDao savingsAccountDao, TransactionDao transactionDao, CheckDao checkDao, OrderDao orderDao) {

        this.savingsAccountDAO = savingsAccountDao;
        this.transactionDao = transactionDao;
        this.checkDao = checkDao;
        this.orderDao = orderDao;
    }


    public SavingsAccountDTO openAccount(int ownerID, Double principal, Date startDate) {
        SavingsAccountDTO account = new SavingsAccountDTO(ownerID, principal, startDate);
        account.setAccountNumber(generateNextAccountNumber());
        savingsAccountDAO.save(account);
        boolean isDeposit =true;
        boolean isExecute =true;

        createTransaction(account.getAccountNumber(), principal,
                startDate, new Date(), isExecute, isDeposit);
        return account;
    }

    private static int nextAccountNumber = 0;

    private String generateNextAccountNumber() {
        nextAccountNumber++;
        return new Integer(nextAccountNumber).toString();

    }

    public void deposit(SavingsAccountDTO account, TransactionDTO transactionDTO) {
        if (!transactionDTO.isExecute()) {
            account.setBalance(account.getBalance() + transactionDTO.getAmount());
            savingsAccountDAO.save(account);
        }
    }

    public void withdraw(SavingsAccountDTO account, TransactionDTO transactionDTO) {
        if (account.getBalance() - transactionDTO.getAmount() >= 0 && !transactionDTO.isExecute()) {
            account.setBalance(account.getBalance() - transactionDTO.getAmount());
            savingsAccountDAO.save(account);
        }
    }

    private int getMaxTransactionId(List<TransactionDTO> transactionDTOs) {
        int max = 0;
        if(transactionDTOs!=null){
            for (TransactionDTO transactionDTO : transactionDTOs) {
                if (transactionDTO.getId() > max) {
                    max = transactionDTO.getId();
                }
            }
        }

        return max;
    }
    private  int getMaxOrderId(List<OrderDTO> orderDTOs){
        int max=0;
        if(orderDTOs!=null){
            for (OrderDTO orderDTO:orderDTOs){
                if(orderDTO.getId()>max){
                    max=orderDTO.getId();
                }
            }
        }
        return max;
    }
    public TransactionDTO createTransaction( String accountNumber, Double amount, Date createDate,
                                             Date plannedToExecuteDate, boolean isExecute, boolean isDeposit) {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(getMaxTransactionId(transactionDao.getAllTransaction())+1);
        transactionDTO.setDate(createDate);
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setInterestDate(createDate);
        transactionDTO.setDate(createDate);
        transactionDTO.setAmount(amount);
        transactionDTO.setDeposit(isDeposit);
        transactionDTO.setTypeOfOrder(1);
        transactionDTO.setDescription("");
        transactionDTO.setExecute(isExecute);
        transactionDTO.setPlannedToExecuteDate(plannedToExecuteDate);
        addTransaction(transactionDTO);
        return transactionDTO;
    }
    public SavingsAccountDTO getAccount(String accountNumber) {
        return savingsAccountDAO.getAccountByAccountNumber(accountNumber);
    }

    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        return transactionDao.save(transactionDTO);
    }

    public List<TransactionDTO> getListTransactionsHistory(String accountNumber) {
        return transactionDao.getListTransactionsHistory(accountNumber);
    }

    public List<TransactionDTO> getListTransactionsHistory(String accountNumber, boolean isExecute) {
        List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
        for (TransactionDTO transactionDTO : getListTransactionsHistory(accountNumber)) {

            if (transactionDTO.isExecute() == isExecute) {
                transactionDTOs.add(transactionDTO);
            }
        }
        return transactionDTOs;
    }

    public List<TransactionDTO> getListTransactionsWithPlanDate(String accountNumber, Date dateReviewTransaction) {
        return transactionDao.getListTransactionsWithPlanDate(accountNumber, dateReviewTransaction);

    }

    public void executeTransaction(TransactionDTO transaction, Date actualExecutionDate) {
        SavingsAccountDTO account = savingsAccountDAO.getAccountByAccountNumber(transaction.getAccountNumber());
        if (transaction.isDeposit()) {
            deposit(account, transaction);
        } else {
            withdraw(account, transaction);
        }
        transaction.setExecute(true);
        transaction.setActualExecutionDate(actualExecutionDate);
        transactionDao.update(transaction);
    }


    private List<TransactionDTO> getTransactionsThatStartGeneratingInterestByCheckDate(List<TransactionDTO> transactionDTOList, Date checkDate) {
        List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
        for (TransactionDTO transactionDTO : transactionDTOList) {
            if (null != transactionDTO.getInterestDate()) {
                if (checkDate.compareTo(transactionDTO.getInterestDate()) == 1) {
                    transactionDTOs.add(transactionDTO);
                }
            } else {
                transactionDTOs.add(transactionDTO);
            }

        }
        return transactionDTOs;
    }
    public List<TransactionDTO> getTransactionsExecutedInAPeriod(String accountNumber,Date fromDate, Date checkDate) {
         List<TransactionDTO> list=new ArrayList<TransactionDTO>();
        List<TransactionDTO> transactionDTOTemp= getListTransactionsHistory(accountNumber,true);
        for(TransactionDTO transactionDTO:transactionDTOTemp){
             if(transactionDTO.getActualExecutionDate()!=null ){
                 if(transactionDTO.getActualExecutionDate().compareTo(fromDate)>=0 && transactionDTO.getActualExecutionDate().compareTo(checkDate) <=0 ){
                     list.add(transactionDTO);
                 }
             }

        }

        return list;
    }
    public List<TransactionDTO> getTransactionsIncurringInterestInAPeriod(String accountNumber,Date fromDate, Date toDate) {
        List<TransactionDTO> list=new ArrayList<TransactionDTO>();
        List<TransactionDTO> transactionDTOTemp= getListTransactionsHistory(accountNumber,true);
        for(TransactionDTO transactionDTO:transactionDTOTemp){
            if(transactionDTO.getInterestDate()!=null ){
                if(transactionDTO.getInterestDate().compareTo(fromDate)>=0 && transactionDTO.getInterestDate().compareTo(toDate) <=0 ){
                    list.add(transactionDTO);
                }
            }

        }

        return list;
    }
    public Double calculatorEarnedBNAInterest(SavingsAccountDTO accountDTO, Date startDate, Date checkDate) {
        boolean isExecute = true;
        List<TransactionDTO> transactionList = getListTransactionsHistory(accountDTO.getAccountNumber(), isExecute);
        transactionList = getTransactionsIncurringInterestInAPeriod(accountDTO.getAccountNumber(), accountDTO.getInterestPeriodStartDate(), checkDate);
        //calculator earn of principal
        Double principal = (accountDTO.getPrincipal() * Utils.interest * daysBetween2Dates(accountDTO.getInterestPeriodStartDate(), checkDate)) / Utils.dayOfYear;
        Double earnedFromDeposit = 0.0;
        Double subFromWithdraw = 0.0;
        if (null != transactionList) {
            for (int i = 1; i < transactionList.size(); i++) {
                TransactionDTO transactionDTO = transactionList.get(i);
                if (transactionDTO.isDeposit()) {
                    earnedFromDeposit += (transactionDTO.getAmount() * Utils.interest * daysBetween2Dates(transactionDTO.getInterestDate(), checkDate)) / Utils.dayOfYear;
                } else {
                    subFromWithdraw += (transactionDTO.getAmount() * Utils.interest * daysBetween2Dates(transactionDTO.getInterestDate(), checkDate)) / Utils.dayOfYear;
                }

            }
        }
        return principal + earnedFromDeposit - subFromWithdraw;

    }

    public long daysBetween2Dates(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);
        return noDay;
    }

    public void addCheck(String accountNumber, CheckDepositDTO checkDepositDTO) {
        // deposit(account, checkDepositDTO.getAmount(), checkDepositDTO.getDateDeposit());
        checkDao.add(accountNumber, checkDepositDTO);
    }

    public List<CheckDepositDTO> getAllCheckOrder(String accountNumber) {
        return checkDao.getAllCheckOrder(accountNumber);
    }


    public List<OrderDTO> getAllOrder(String accountNumber) {
        return orderDao.getAllOrder(accountNumber);
    }

    public OrderDTO createOrder(String accountNumber, OrderDTO orderDTO) {
        orderDTO.setId(getMaxOrderId(orderDao.getAllOrder())+1);
        return orderDao.createOrder(accountNumber, orderDTO);
    }

    public OrderDTO getOrder(String accountNumber, int orderId) {
        return orderDao.getOrder(accountNumber, orderId);
    }

    public void accrueInterest(String accountNumber, Date checkDate) {

        Date AccruedFirstDate = Utils.AccruedFirstDate;
        Date AccruedSecondDate = Utils.AccruedSecondDate;

        SavingsAccountDTO account=getAccount(accountNumber);
        double newPrincipal = account.getPrincipal();
        if (checkDate.compareTo(AccruedFirstDate) == 0 || checkDate.compareTo(AccruedSecondDate) == 0) {
            List<TransactionDTO> listAllTransaction=getTransactionsExecutedInAPeriod(accountNumber,account.getInterestPeriodStartDate(),checkDate);
              List<TransactionDTO> transactionsIncurringInterest=getTransactionsThatStartGeneratingInterestByCheckDate(listAllTransaction ,checkDate);

            for (TransactionDTO transactionDTO : transactionsIncurringInterest) {
                if (transactionDTO.isDeposit()) {
                    newPrincipal += transactionDTO.getAmount();

                } else {
                    newPrincipal -= transactionDTO.getAmount();
                }

            }
            double interest=calculatorEarnedBNAInterest(account, account.getInterestPeriodStartDate(), checkDate);

            newPrincipal+=  interest;
            account=getAccount(accountNumber);
            TransactionDTO accrueTransaction = createTransaction(accountNumber,interest,checkDate,checkDate,false,true);
            executeTransaction(accrueTransaction,checkDate);

            account.setPrincipal(newPrincipal);
            account.setInterestPeriodStartDate( Utils.nextInterestPeriodStartDate(checkDate));
            savingsAccountDAO.save(account);

        }
    }

    public void checkDepositRecurring(SavingsAccountDTO account, OrderDTO orderDTO, Date checkDate) {
        if (orderDTO.getApproved() && !orderDTO.isValidUntilCancel()) {
            if(checkDate.compareTo(orderDTO.getTransactionGenerateDate())>=0 )
            {
                if(null==orderDTO.getNumOfTimesInTotal()){
                    Date nextDateRecurring= getNextDateRecurringByTypeOfFrequency(orderDTO.getTransactionGenerateDate(),orderDTO.getTransactionFrequency());
                    createTransaction(account.getAccountNumber(), orderDTO.getAmount(), orderDTO.getTransactionGenerateDate(),
                            nextDateRecurring , false, orderDTO.isDeposit());

                    orderDTO.setTransactionGenerateDate(nextDateRecurring);
                    orderDao.updateOrder(orderDTO);
                } else{
                    if( orderDTO.getNumOfTimesInTotal()>0)   {
                        Date nextDateRecurring= getNextDateRecurringByTypeOfFrequency(orderDTO.getTransactionGenerateDate(),orderDTO.getTransactionFrequency());
                        createTransaction(account.getAccountNumber(), orderDTO.getAmount(), orderDTO.getTransactionGenerateDate(),
                                nextDateRecurring , false, orderDTO.isDeposit());

                        orderDTO.setTransactionGenerateDate(nextDateRecurring);
                        orderDTO.setNumOfTimesInTotal(orderDTO.getNumOfTimesInTotal() - 1);
                        orderDao.updateOrder(orderDTO);
                    }
                }

            }

        }

    }

    public Date getNextDateRecurringByTypeOfFrequency(Date date, OrderDTO.Frequency typeOfFrequency) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        if(typeOfFrequency==OrderDTO.Frequency.MONTHLY){
            c.add(Calendar.DATE, 30);
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)  {
                c.add(Calendar.DATE,31);
            }
        } else if(typeOfFrequency==OrderDTO.Frequency.WEEKLY){
            //after 7 days
            c.add(Calendar.DATE, 7);
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)  {
                c.add(Calendar.DATE,8);
            }
        }
        Date nextDateRecurring  = c.getTime();
        return nextDateRecurring;
    }

    public TransactionDTO getTransaction(int id, String accountNumber) {
        return transactionDao.getTransaction(id, accountNumber);
    }


    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void updateOrder(OrderDTO orderDTO) {
        orderDao.updateOrder(orderDTO);
    }

    public void approveOrder(OrderDTO orderDTO) {
        orderDTO.setApproved(true);
        orderDao.updateOrder(orderDTO);
    }

    public List<OrderDTO> getAllPendingOrders() {
        return orderDao.getAllPendingOrders();
    }
    public  List<OrderDTO> getAllOrderShouldHaveCreatedTransactionByCheckDate(Date checkDate){
        return orderDao.getAllOrderShouldHaveCreatedTransactionByCheckDate(checkDate);
    }
    public void disApproveOrder(OrderDTO orderDTO){
        orderDTO.setApproved(false);
        orderDao.updateOrder(orderDTO);
    }

    public void cancelOrder(OrderDTO orderDTO) {
         orderDTO.setIsCanceled(true);
         orderDao.updateOrder(orderDTO);
    }

    public List<OrderDTO> getAllOrderThatApprovedAndNotExpired(Date checkDate) {
        return orderDao.getAllOrderThatApprovedAndNotExpired(checkDate);
    }
}
