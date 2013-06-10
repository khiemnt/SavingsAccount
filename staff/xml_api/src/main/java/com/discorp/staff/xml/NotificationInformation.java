package com.discorp.staff.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 8/23/12
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "NotificationInformation")
public class NotificationInformation
{
    private String information;
    private int communicationMethodId;
    public String getInformation()
    {
        return information;
    }

    public void setInformation(String information)
    {
        this.information = information;
    }


    public int getCommunicationMethodId() {
        return communicationMethodId;
    }

    public void setCommunicationMethodId(int communicationMethodId) {
        this.communicationMethodId = communicationMethodId;
    }


}
