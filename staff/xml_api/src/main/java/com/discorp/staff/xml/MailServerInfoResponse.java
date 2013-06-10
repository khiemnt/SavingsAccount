package com.discorp.staff.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 11/9/12
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "MailServerInfoResponse")
public class MailServerInfoResponse
{
    public MailServerInfo getMailServerInfo()
    {
        return mailServerInfo;
    }

    public void setMailServerInfo(MailServerInfo mailServerInfo)
    {
        this.mailServerInfo = mailServerInfo;
    }

    private MailServerInfo mailServerInfo;
}
