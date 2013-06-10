package com.discorp.savings.dto;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 6:28 PM
 */
public class TransactionDTO {

    //contain date withdrawal or deposit
    private Date date;
    //money amount
    private Double amount;
    //deposit or withdrawal
    private boolean isDeposit;
    //
    private int typeOfOrder;
    private String description ;
    //account number
    private String accountNumber;
    private boolean isExecute;
    private int id;

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    private Date interestDate;

    private Date plannedToExecuteDate;
    private Date actualExecutionDate;

    public Date getPlannedToExecuteDate() {
        return plannedToExecuteDate;
    }

    public void setPlannedToExecuteDate(Date plannedToExecuteDate) {
        this.plannedToExecuteDate = plannedToExecuteDate;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public int getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(int typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExecute(boolean isExecute) {
        this.isExecute = isExecute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isExecute() {
        return isExecute;
    }



    public Date getActualExecutionDate() {
         return actualExecutionDate;
    }

    public void setActualExecutionDate(Date actualExecutionDate) {
        this.actualExecutionDate = actualExecutionDate;
    }




}
