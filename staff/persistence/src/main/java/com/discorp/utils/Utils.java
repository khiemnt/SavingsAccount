package com.discorp.utils;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 3/30/12
 * Time: 8:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utils
{
    public static HashMap<String, String> map = new HashMap<String, String>();
    public static String mainPhone = "PHONE";
    public static String mainMail = "EMAIL";
    public static String name = "Name";
    public static String emailAddress = "Primary email address";
    public static String phoneNumber = "Primary Phone Number";
    public static String family = "Family";
    public static String address = "Address";
    public static String gender = "Gender";
    public static String StaffMember = "Staff Member";
    public static String invationCode = "Invitation Code";
    public static String imagePath = "Imagepath";
    public static enum ROLE
    {
        StaffMember, Manager, HR
    }
    public static enum OPERATOR_STRING
    {
        IS, ALL,IS_NOT
    }
    public static enum OPERATOR_DATE
    {
         ALL,BEFORE_MONTH,BEFORE_YEAR,BEFORE_WEEK,EQUAL
    }

    public static enum OPERATOR_INTEGER
    {
        GREATER, LESS, EQUAL,ALL
    }


}
