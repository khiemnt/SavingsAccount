package com.discorp.savings.dto;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 4:19 PM
 */
public class SavingsAccountDTO {
    private final int ownerID;
    private Double principal;
    private final Date startDate;
    private Double balance;
    private String accountNumber;
    private Date interestPeriodStartDate;

    public SavingsAccountDTO(int ownerID, Double principal, Date startDate) {
        this.ownerID = ownerID;
        this.principal = principal;
        this.startDate = startDate;
        interestPeriodStartDate = startDate;
        balance = principal;
    }

    public SavingsAccountDTO(SavingsAccountDTO account) {
        ownerID = account.ownerID;
        startDate = account.startDate;
        copy(account);
    }

    public int getOwnerID() {
        return ownerID;
    }

    public Double getPrincipal() {
        return principal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void copy(SavingsAccountDTO account) {

        principal = account.principal;
        balance = account.balance;
        accountNumber = account.accountNumber;
        interestPeriodStartDate = account.interestPeriodStartDate;
    }

    public Date getInterestPeriodStartDate() {
        return interestPeriodStartDate;
    }

    public void setInterestPeriodStartDate(Date interestPeriodStartDate) {
        this.interestPeriodStartDate = interestPeriodStartDate;
    }
    //    private Integer id;
//    private String nameSaving;
//    private Double balance;
//    private Double principal; //goc
//    private List<TransactionDTO> transactionList;
//    private Double earnedInterest;
//
//    public String getNameSaving() {
//        return nameSaving;
//    }
//
//    public void setNameSaving(String nameSaving) {
//        this.nameSaving = nameSaving;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
//
//    public List<TransactionDTO> getTransactionList() {
//        return transactionList;
//    }
//
//    public void setTransactionList(List<TransactionDTO> transactionList) {
//        this.transactionList = transactionList;
//    }
//
//    public Double getEarnedInterest() {
//        return earnedInterest;
//    }
//
//    public void setEarnedInterest(Double earnedInterest) {
//        this.earnedInterest = earnedInterest;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Double getPrincipal() {
//        return principal;
//    }
//
//    public void setPrincipal(Double principal) {
//        this.principal = principal;
//    }
}
