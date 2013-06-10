package routing;

import com.discorp.staff.service.SettingService;
import com.discorp.staff.service.SetupService;
import com.discorp.staff.xml.*;
import org.springframework.beans.factory.InitializingBean;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/29/12
 * <p/>
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpTarget implements InitializingBean
{
    XPath xpath;

    private SettingService settingService;
    private SetupService setupServiceTarget;

    public SettingService getSettingService() {
        return settingService;
    }

    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }

    public SetupService getSetupServiceTarget() {
        return setupServiceTarget;
    }

    public void setSetupServiceTarget(SetupService setupServiceTarget) {
        this.setupServiceTarget = setupServiceTarget;
    }

    public XPath getXpath()
    {
        return xpath;
    }

    public void setXpath(XPath xpath)
    {
        this.xpath = xpath;
    }




    /**
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception
    {
        xpath = XPathFactory.newInstance().newXPath();

    }

    public InvationCodeResponse checkInvationCode(InvationCodeRequest request)
    {
        return setupServiceTarget.checkValidateInvitationCode(request.getInvitationCode());
    }

    public UpdateCreditCardResponse updateCreditCardInfo(UpdateCreditCardRequest request)
    {
        UpdateCreditCardResponse updateCreditCardResponse = new UpdateCreditCardResponse();
        settingService.updateCreditCardInfo(request);
        return updateCreditCardResponse;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request)
    {
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
        settingService.changePassword(request);
        return changePasswordResponse;
    }

    public CancelServiceResponse cancelService(CancelServiceRequest request)
    {
        CancelServiceResponse cancelServiceResponse = new CancelServiceResponse();
        settingService.cancelService(request);
        return cancelServiceResponse;
    }

    public UpdateNotificationResponse updateNotification(UpdateNotificationRequest request)
    {
        UpdateNotificationResponse updateNotificationResponse = new UpdateNotificationResponse();
        settingService.updateNotification(request);
        return updateNotificationResponse;
    }

    public NotificationInformationResponse getNotificationInfor(NotificationInformationRequest request)
    {
        NotificationInformationResponse notificationInformationResponse = new NotificationInformationResponse();
        if(null != settingService.getNotificationInfor(request))
            notificationInformationResponse = settingService.getNotificationInfor(request);
        return notificationInformationResponse;
    }

    public InformationResponse getInformation(InformationRequest informationRequest)
    {
        return setupServiceTarget.getInformation();
    }

    public SetupResponse setupStaflink(SetupRequest setupRequest)
    {
        return setupServiceTarget.setupStaflink(setupRequest);
    }

    public MailServerInfoResponse getMailServerInfo(MailServerInfoRequest mailServerInfoRequest)
    {
        return settingService.getMailServerInfo(mailServerInfoRequest);
    }

    public UpdateMailServerInfoResponse updateMailServerInfo(UpdateMailServerInfoRequest request)
    {
        return settingService.updateMailServerInfo(request);
    }


}
