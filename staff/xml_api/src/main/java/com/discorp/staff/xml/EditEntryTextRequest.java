package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 11/7/12
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "EditEntryTextRequest")
public class EditEntryTextRequest
{
    public String getBlockContent()
    {
        return blockContent;
    }

    public void setBlockContent(String blockContent)
    {
        this.blockContent = blockContent;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement(required = true)
    private String blockContent;

    @XmlElement(required = true)
    private int id;

}
