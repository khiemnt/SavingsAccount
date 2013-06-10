package com.discorp.savings.service;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 5:46 PM
 */
public class Utils {
    public static double interest = 0.1;
    public static int dayOfYear = 365;
    public  static Date AccruedFirstDate = new Date("2013/3/31");
    public  static Date AccruedSecondDate = new Date("2013/12/31");
    public  static Date Apr1st = new Date("2013/4/1");
    public  static Date Jan1st = new Date("2014/1/1");
    public static Date nextInterestPeriodStartDate(Date date) {
        if (0==date.compareTo(AccruedFirstDate) ) return Apr1st;
        if (0==date.compareTo(AccruedSecondDate) ) return Jan1st;
        return null;
    }
}
