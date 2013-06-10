package com.discorp.utils;

import com.discorp.staff.xml.Account;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/26/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountSortByName implements Comparator<Account>
{
    public int compare(Account o1, Account o2)
    {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
