package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/16/12
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BlockRequest")
public class BlockRequest
{
    @XmlElement(required = true)
    private int blockId;

    public int getBlockId()
    {
        return blockId;
    }

    public void setBlockId(int blockId)
    {
        this.blockId = blockId;
    }
}
