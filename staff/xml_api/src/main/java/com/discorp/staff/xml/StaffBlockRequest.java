package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/11/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffBlockRequest")
public class StaffBlockRequest
{
    @XmlElement(required = true)
    private int blockId;
    @XmlElement(required = true)
    private int userId;

    public int getBlockId()
    {
        return blockId;
    }

    public void setBlockId(int blockId)
    {
        this.blockId = blockId;
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
