package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/13/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffReceiveSuggestionRequest")
public class StaffReceiveSuggestionRequest {
    @XmlElement(required = true)
    private Integer dealerId;

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }
}
