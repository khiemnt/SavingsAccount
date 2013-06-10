package com.discorp.savings.service.impl;


import com.discorp.savings.dto.TransactionDTO;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 12:02 PM
 */
public class TransactionSortByDate implements Comparator<TransactionDTO>
{

    public int compare(TransactionDTO o1, TransactionDTO o2)
    {
        try
        {
            return o1.getDate().compareTo(o2.getDate());
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
