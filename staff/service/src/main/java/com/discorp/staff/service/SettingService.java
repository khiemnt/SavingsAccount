package com.discorp.staff.service;

import com.discorp.staff.xml.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SettingService {
    /**
     * @see com.discorp.staff.xml.UpdateCreditCardRequest
     * @param request
     */
    public void updateCreditCardInfo(UpdateCreditCardRequest request);

    /**
     * @see com.discorp.staff.xml.ChangePasswordRequest
     * @param request
     */
    public void changePassword(ChangePasswordRequest request);

    /**
     * @see com.discorp.staff.xml.CancelServiceRequest
     * @param request
     */
    public void cancelService(CancelServiceRequest request);

    /**
     * @see com.discorp.staff.xml.UpdateNotificationRequest
     * @param request
     */
    public void updateNotification(UpdateNotificationRequest request);

    public NotificationInformationResponse getNotificationInfor(NotificationInformationRequest request);
    public MailServerInfoResponse getMailServerInfo(MailServerInfoRequest request);
    public UpdateMailServerInfoResponse updateMailServerInfo(UpdateMailServerInfoRequest request);
}
