package com.discorp.savings.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: khiemnt
 * Date: 5/23/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckDepositDTO extends OrderDTO {

    private String fileURL;
    private String accountTo;
    private Date dateDeposit;
    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Date getDateDeposit() {
        return dateDeposit;
    }

    public void setDateDeposit(Date dateDeposit) {
        this.dateDeposit = dateDeposit;
    }
}
