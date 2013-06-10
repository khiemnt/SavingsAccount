package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 3/21/12
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffRequest")
public class StaffRequest
{
    @XmlElement(required = true)
    int userId = 0;

    @XmlElement(required = true)
    private String name = null;

    @XmlElement(required = true)
    private String dealerId;

    @XmlElement(required = true)
    private Boolean isAchive;

    public Boolean getAchive()
    {
        return isAchive;
    }

    public void setAchive(Boolean achive)
    {
        isAchive = achive;
    }

    public String getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
