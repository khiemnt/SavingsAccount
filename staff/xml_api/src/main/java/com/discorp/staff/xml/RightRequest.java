package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/16/12
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "RightRequest")
public class RightRequest
{
    @XmlElement(required = true)
    private int userId;
    @XmlElement(required = true)
    private String memberId;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }
}

