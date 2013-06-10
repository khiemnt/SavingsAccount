package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/19/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "DeleteMutilFieldRequest")
public class DeleteMutilFieldRequest
{
    @XmlElement(required = true)
    private int userId;
    @XmlElement(required = true)
    private int fieldId;
    @XmlElement(required = true)
    private int group;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getFieldId()
    {
        return fieldId;
    }

    public void setFieldId(int fieldId)
    {
        this.fieldId = fieldId;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }
}
