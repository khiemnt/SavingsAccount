package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/16/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BlockResponse")
public class BlockResponse
{
    @XmlElement(required = true)
    private int addAnother;

    public int getAddAnother()
    {
        return addAnother;
    }

    public void setAddAnother(int addAnother)
    {
        this.addAnother = addAnother;
    }
}
