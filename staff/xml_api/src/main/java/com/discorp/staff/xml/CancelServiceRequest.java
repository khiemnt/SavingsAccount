package com.discorp.staff.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 8/22/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "CancelServiceRequest")
public class CancelServiceRequest
{
    public Integer getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(Integer dealerId)
    {
        this.dealerId = dealerId;
    }

    private Integer dealerId;

}
