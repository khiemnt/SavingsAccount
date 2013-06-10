package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/29/12
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "InvationCodeResponse")
public class InvationCodeResponse
{
    @XmlElement(required = true)
    private String userName;

    @XmlElement(required = true)
    private String passWord;

    @XmlElement(required = true)
    private Boolean isValidate;

    public Boolean getValidate()
    {
        return isValidate;
    }

    public void setValidate(Boolean validate)
    {
        isValidate = validate;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
}
