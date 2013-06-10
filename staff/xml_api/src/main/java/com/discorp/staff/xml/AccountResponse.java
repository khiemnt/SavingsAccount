package com.discorp.staff.xml;


import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 3/20/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AccountResponse")
public class AccountResponse
{
    @XmlElement(name = "listAccount", required = true)
    private List<Account> listAccount;

    @XmlElement(name = "Account", required = true)
    private Account account;

    @XmlElement(required = true)
    private int validate;

    public int getValidate()
    {
        return validate;
    }

    public void setValidate(int validate)
    {
        this.validate = validate;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public List<Account> getListAccount()
    {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount)
    {
        this.listAccount = listAccount;
    }
}
