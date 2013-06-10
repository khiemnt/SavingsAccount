package com.discorp.savings.dto;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/24/13
 * Time: 9:19 AM
 */
public class OrderDTO {


    public enum Frequency { ONE_TIME, WEEKLY, MONTHLY };
    public enum Type { PAYROLL, BANK_ACH, CHECK };

    private boolean canceled=false;
    private int id;
    private String name;
    private boolean isDeposit;
    private double amount;
    private int frequency;
    private Date createdOnDate;
    private Date transactionGenerateDate;
    private boolean validUntilCancel;
    private Integer numOfTimesInTotal;
    private Boolean isApproved;
    private String accountNumber;
    private Type typeOfOrder;
    private Frequency transactionFrequency;

    public Frequency getTransactionFrequency() {
        return transactionFrequency;
    }

    public void setTransactionFrequency(Frequency transactionFrequency) {
        this.transactionFrequency = transactionFrequency;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Date getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(Date createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public boolean isValidUntilCancel() {
        return validUntilCancel;
    }

    public void setValidUntilCancel(boolean validUntilCancel) {
        this.validUntilCancel = validUntilCancel;
    }


    public Integer getNumOfTimesInTotal() {
        return numOfTimesInTotal;
    }

    public void setNumOfTimesInTotal(Integer numOfTimesInTotal) {
        this.numOfTimesInTotal = numOfTimesInTotal;
    }

    public void setId(int orderId) {
        this.id =  orderId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Type getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(Type typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }


    public Date getTransactionGenerateDate() {
        return transactionGenerateDate;
    }

    public void setTransactionGenerateDate(Date transactionGenerateDate) {
        this.transactionGenerateDate = transactionGenerateDate;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
    public boolean isCanceled() {
        return canceled;
    }
    public void setIsCanceled(boolean canceled) {
         this.canceled=canceled;
    }

}

