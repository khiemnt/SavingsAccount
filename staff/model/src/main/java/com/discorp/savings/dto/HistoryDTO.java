package com.discorp.savings.dto;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 6:28 PM
 */
public class HistoryDTO {
    //contain date withdrawal or deposit
    private Date dateHistory;
    //money amount
    private Double Amount;
    //deposit or withdrawal
    private int type;

    public Date getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(Date dateHistory) {
        this.dateHistory = dateHistory;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
