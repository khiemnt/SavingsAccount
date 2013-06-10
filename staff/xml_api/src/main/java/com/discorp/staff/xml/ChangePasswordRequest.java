package com.discorp.staff.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 8/20/12
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ChangePasswordRequest")
public class ChangePasswordRequest
{
    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getNewPwd()
    {
        return newPwd;
    }

    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }

    private String uid;
    private String newPwd;

}
