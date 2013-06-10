package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/16/12
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffInformationRequest")
public class StaffInformationRequest
{
    @XmlElement(required = true)
    private int userId;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
