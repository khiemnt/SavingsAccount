package com.discorp.savings.dto;

/**
 * Created with IntelliJ IDEA.
 * User: khiemnt
 * Date: 5/23/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankACHDTO extends OrderDTO {

    private String rollBank;
    private String accountName;

    public String getRollBank() {
        return rollBank;
    }

    public void setRollBank(String rollBank) {
        this.rollBank = rollBank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
