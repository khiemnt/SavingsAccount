package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 11/9/12
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "MailServerInfoRequest")
public class MailServerInfoRequest
{
    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    @XmlElement(required = true)
    private int webId;
}
