package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/10/12
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AccountRequest")
public class AccountRequest {
    @XmlElement(required = true)
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
