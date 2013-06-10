package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 8/23/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "NotificationInformationResponse")
public class NotificationInformationResponse
{
    public List<NotificationInformation> getListNotificationInfor()
    {
        return listNotificationInfor;
    }

    public void setListNotificationInfor(List<NotificationInformation> listNotificationInfor)
    {
        this.listNotificationInfor = listNotificationInfor;
    }

    @XmlElement(name = "listNotificationInfor")
    private List<NotificationInformation> listNotificationInfor;
}
