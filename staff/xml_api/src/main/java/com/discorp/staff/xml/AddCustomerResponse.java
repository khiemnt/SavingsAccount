package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/8/12
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AddCustomerResponse")
public class AddCustomerResponse {
    @XmlElement(required = true)
    private int status;

    @XmlElement(required = true)
    private int dealerId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }
}
