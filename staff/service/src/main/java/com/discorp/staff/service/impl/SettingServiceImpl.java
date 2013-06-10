//package com.discorp.staff.service.impl;
//
//import com.discorp.staff.model.*;
//
//import com.discorp.staff.dao.*;
//import com.discorp.staff.service.SettingService;
//import com.discorp.staff.xml.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: tiepnm
// * Date: 9/18/12
// * Time: 1:47 PM
// * To change this template use File | Settings | File Templates.
// */
//public class SettingServiceImpl implements SettingService
//{
//    private CardInfoDao cardInfoDAO;
//    private CommunicationDetailDao communicationDetailDAO;
//    private DealerDao dealerDAO;
//    private CustomerDao customerDAO;
//    private FieldDao fieldDAO;
//    private MailServerInfoDao mailServerInfoDAO;
//    private FieldValueDao fieldValueDAO;
//
//    public FieldValueDao getFieldValueDAO()
//    {
//        return fieldValueDAO;
//    }
//
//    public void setFieldValueDAO(FieldValueDao fieldValueDAO)
//    {
//        this.fieldValueDAO = fieldValueDAO;
//    }
//
//    public CardInfoDao getCardInfoDAO()
//    {
//        return cardInfoDAO;
//    }
//
//    public void setCardInfoDAO(CardInfoDao cardInfoDAO)
//    {
//        this.cardInfoDAO = cardInfoDAO;
//    }
//
//    public CommunicationDetailDao getCommunicationDetailDAO()
//    {
//        return communicationDetailDAO;
//    }
//
//    public void setCommunicationDetailDAO(CommunicationDetailDao communicationDetailDAO)
//    {
//        this.communicationDetailDAO = communicationDetailDAO;
//    }
//
//    public DealerDao getDealerDAO()
//    {
//        return dealerDAO;
//    }
//
//    public void setDealerDAO(DealerDao dealerDAO)
//    {
//        this.dealerDAO = dealerDAO;
//    }
//
//    public CustomerDao getCustomerDAO()
//    {
//        return customerDAO;
//    }
//
//    public void setCustomerDAO(CustomerDao customerDAO)
//    {
//        this.customerDAO = customerDAO;
//    }
//
//    public FieldDao getFieldDAO()
//    {
//        return fieldDAO;
//    }
//
//    public void setFieldDAO(FieldDao fieldDAO)
//    {
//        this.fieldDAO = fieldDAO;
//    }
//
//    public MailServerInfoDao getMailServerInfoDAO()
//    {
//        return mailServerInfoDAO;
//    }
//
//    public void setMailServerInfoDAO(MailServerInfoDao mailServerInfoDAO)
//    {
//        this.mailServerInfoDAO = mailServerInfoDAO;
//    }
//
//    public void updateCreditCardInfo(UpdateCreditCardRequest request)
//    {
//        //To change body of implemented methods use File | Settings | File Templates.
//        String cardName = request.getNameCard();
//        String cardNumber = request.getCreditCardNumber();
//        String expireDate = request.getExpireDate();
//        String csvCode = request.getCsvCode();
//        List<CardInfor> list = cardInfoDAO.getListCardById(Integer.parseInt(request.getDealerId()));
//        if (list.size() > 0)
//        {
//            CardInfor cardInfor = list.get(0);
//            cardInfor.setCardName(cardName);
//            cardInfor.setCardNumber(cardNumber);
//            cardInfor.setExpireDate(expireDate);
//            cardInfor.setCsvCode(csvCode);
//            cardInfoDAO.updateCard(cardInfor);
//        }
//    }
//
//    public void changePassword(ChangePasswordRequest request)
//    {
//        List<FieldValue> list = fieldValueDAO.getListFieldValuePassWord(request.getUid());
//        if (list.size() > 0)
//        {
//            list.get(0).setFieldValue(request.getNewPwd());
//            fieldValueDAO.updateFieldValue(list.get(0));
//
//        }
//    }
//
//    public void cancelService(CancelServiceRequest request)
//    {
//        List<Dealer> list = dealerDAO.getDealerListByDealerId(request.getDealerId());
//        if (list.size() > 0)
//        {
//            list.get(0).setActive(false);
//            dealerDAO.updateDealer(list.get(0));
//
//        }
//    }
//
//    public void updateNotification(UpdateNotificationRequest request)
//    {
//        String email = request.getEmail();
//        String phone = request.getPhone();
//        String uid = request.getUid();
//        List<Customer> customer = customerDAO.getCustomerListByMemberId(uid);
//        Integer addressId = customer.get(0).getAddressId();
//        Integer dealerId = customer.get(0).getDealerId();
//        List<CommunicationDetail> list = communicationDetailDAO.getCommunicationDetailListByUid(uid);
//
//        Integer webId = 0;
//        // get max of webId
//        List<CommunicationDetail> listComDetail = communicationDetailDAO.findAllCommunicationDetail();
//        if (listComDetail.size() > 0)
//        {
//            webId = listComDetail.get(0).getWebId();
//        }
//        if (list.size() > 0)
//        {
//            boolean emailInstance = true, phoneInstance = true;
//            for (CommunicationDetail communicationDetail : list)
//            {
//                if (communicationDetail.getCommunicationMethodId() == 1 && !phone.trim().equals(""))
//                {
//                    phoneInstance = false;
//                    communicationDetail.setInformation(phone);
//                    communicationDetailDAO.updateCommunicationDetail(communicationDetail);
//                }
//                if (communicationDetail.getCommunicationMethodId() == 3 && !email.trim().equals(""))
//                {
//                    emailInstance = false;
//                    communicationDetail.setInformation(email);
//                    communicationDetailDAO.updateCommunicationDetail(communicationDetail);
//                }
//
//            }
//
//            if (phoneInstance && !phone.trim().equals(""))
//            {
//                CommunicationDetail communicationDetail = new CommunicationDetail();
//                communicationDetail.setWebId(webId + 1);
//                communicationDetail.setInformation(phone);
//                communicationDetail.setCommunicationMethodId(1);
//                communicationDetail.setAddressId(addressId);
//                communicationDetail.setDealerId(dealerId);
//                communicationDetail.setMain(false);
//                webId++;
//                communicationDetailDAO.saveCommunicationDetail(communicationDetail);
//            }
//            if (emailInstance && !email.trim().equals(""))
//            {
//                CommunicationDetail communicationDetail = new CommunicationDetail();
//                communicationDetail.setWebId(webId + 1);
//                communicationDetail.setInformation(email);
//                communicationDetail.setCommunicationMethodId(3);
//                communicationDetail.setAddressId(addressId);
//                communicationDetail.setDealerId(dealerId);
//                communicationDetail.setMain(false);
//                communicationDetailDAO.saveCommunicationDetail(communicationDetail);
//            }
//        }
//        else
//        {
//
//            CommunicationDetail communicationDetail = new CommunicationDetail();
//            if (!phone.trim().equals(""))
//            {
//                communicationDetail.setWebId(webId + 1);
//                communicationDetail.setInformation(phone);
//                communicationDetail.setCommunicationMethodId(1);
//                communicationDetail.setAddressId(addressId);
//                communicationDetail.setDealerId(dealerId);
//                communicationDetail.setMain(false);
//                webId++;
//                communicationDetailDAO.saveCommunicationDetail(communicationDetail);
//            }
//            if (!email.trim().equals(""))
//            {
//                communicationDetail.setWebId(webId + 1);
//                communicationDetail.setInformation(email);
//                communicationDetail.setCommunicationMethodId(3);
//                communicationDetail.setAddressId(addressId);
//                communicationDetail.setDealerId(dealerId);
//                communicationDetail.setMain(false);
//                communicationDetailDAO.saveCommunicationDetail(communicationDetail);
//            }
//        }
//
//    }
//
//    public NotificationInformationResponse getNotificationInfor(NotificationInformationRequest request)
//    {
//        NotificationInformationResponse response = new NotificationInformationResponse();
//        NotificationInformation notificationInformation;
//        List<NotificationInformation> listNotificationInfo = new ArrayList<NotificationInformation>();
//        List<CommunicationDetail> list = communicationDetailDAO.getCommunicationDetailListByUid(request.getUid());//hibernateTemplate.find("select com_detail from Customer customer, CommunicationDetail com_detail where customer.addressId=com_detail.addressId and com_detail.main=false and customer.uid=?", new Object[]{request.getUid()});
//        List<CommunicationDetail> primaryList = communicationDetailDAO.getCommunicationDetailMainListByUid(request.getUid());
//        String primaryEmail = "", primaryPhone = "";
//        if (primaryList.size() > 0)
//        {
//            for (CommunicationDetail communicationDetail : primaryList)
//            {
//                if (communicationDetail.getCommunicationMethodId() == 1)
//                {
//                    primaryPhone = communicationDetail.getInformation();
//                }
//                if (communicationDetail.getCommunicationMethodId() == 3)
//                {
//                    primaryEmail = communicationDetail.getInformation();
//                }
//
//            }
//
//        }
//        if (list.size() > 0)
//        {
//            boolean emailInstance = true, phoneInstance = true;
//            for (CommunicationDetail communicationDetail : list)
//            {
//                if (communicationDetail.getCommunicationMethodId() == 1)
//                {
//                    phoneInstance = false;
//                    notificationInformation = new NotificationInformation();
//                    notificationInformation.setCommunicationMethodId(1);
//                    notificationInformation.setInformation(communicationDetail.getInformation());
//                    listNotificationInfo.add(notificationInformation);
//                }
//                if (communicationDetail.getCommunicationMethodId() == 3)
//                {
//                    emailInstance = false;
//                    notificationInformation = new NotificationInformation();
//                    notificationInformation.setCommunicationMethodId(3);
//                    notificationInformation.setInformation(communicationDetail.getInformation());
//                    listNotificationInfo.add(notificationInformation);
//                }
//
//            }
//
//            if (emailInstance)
//            {
//                notificationInformation = new NotificationInformation();
//                notificationInformation.setCommunicationMethodId(3);
//                notificationInformation.setInformation(primaryEmail);
//                listNotificationInfo.add(notificationInformation);
//            }
//            if (phoneInstance)
//            {
//                notificationInformation = new NotificationInformation();
//                notificationInformation.setCommunicationMethodId(1);
//                notificationInformation.setInformation(primaryPhone);
//                listNotificationInfo.add(notificationInformation);
//            }
//        }
//        else
//        {
//            notificationInformation = new NotificationInformation();
//            notificationInformation.setCommunicationMethodId(1);
//            notificationInformation.setInformation(primaryPhone);
//            listNotificationInfo.add(notificationInformation);
//            notificationInformation = new NotificationInformation();
//            notificationInformation.setCommunicationMethodId(3);
//            notificationInformation.setInformation(primaryEmail);
//            listNotificationInfo.add(notificationInformation);
//        }
//        response.setListNotificationInfor(listNotificationInfo);
//        return response;
//    }
//
//    public MailServerInfoResponse getMailServerInfo(MailServerInfoRequest request)
//    {
//        MailServerInfoResponse mailServerInfoResponse = new MailServerInfoResponse();
//        List<com.discorp.model.common.MailServerInfo> mailServerInfoList = mailServerInfoDAO.getMailServerListById(request.getWebId());
//        com.discorp.staff.xml.MailServerInfo mailServerInfo = new com.discorp.staff.xml.MailServerInfo();
//        if (mailServerInfoList.size() > 0)
//        {
//            mailServerInfo.setHost(mailServerInfoList.get(0).getHost());
//            mailServerInfo.setPort(mailServerInfoList.get(0).getPort());
//            mailServerInfo.setUserName(mailServerInfoList.get(0).getUsername());
//            mailServerInfo.setPassWord(mailServerInfoList.get(0).getPassword());
//        }
//        mailServerInfoResponse.setMailServerInfo(mailServerInfo);
//        return mailServerInfoResponse;
//    }
//
//    public UpdateMailServerInfoResponse updateMailServerInfo(UpdateMailServerInfoRequest request)
//    {
//        List<com.discorp.model.common.MailServerInfo> mailServerInfoList = mailServerInfoDAO.getMailServerListById(request.getwebId());
//        if (mailServerInfoList.size() > 0)
//        {
//            mailServerInfoList.get(0).setHost(request.getHost());
//            mailServerInfoList.get(0).setPort(request.getPort());
//            mailServerInfoList.get(0).setUsername(request.getUsername());
//            mailServerInfoList.get(0).setPassword(request.getPassword());
//            mailServerInfoDAO.updateMailServer(mailServerInfoList.get(0));
//        }
//        else
//        {
//            Integer id = 0;
//
//            // get max of webId
//            List<com.discorp.model.common.MailServerInfo> listMailInfo = mailServerInfoDAO.findAllMailServer();//hibernateTemplate.find("from MailServerInfo c order by c.id desc");
//            if (listMailInfo.size() > 0)
//            {
//                id = listMailInfo.get(0).getId();
//            }
//            com.discorp.model.common.MailServerInfo mailServerInfo = new com.discorp.model.common.MailServerInfo();
//            mailServerInfo.setHost(request.getHost());
//            mailServerInfo.setPort(request.getPort());
//            mailServerInfo.setUsername(request.getUsername());
//            mailServerInfo.setPassword(request.getPassword());
//            mailServerInfo.setWebId(request.getwebId());
//            mailServerInfo.setId(id + 1);
//            //hibernateTemplate.saveMailServer(mailServerInfo);
//            mailServerInfoDAO.saveMailServer(mailServerInfo);
//        }
//        return new UpdateMailServerInfoResponse();
//
//    }
//}
