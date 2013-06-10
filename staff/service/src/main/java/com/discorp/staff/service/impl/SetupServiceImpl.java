package com.discorp.staff.service.impl;


import com.discorp.staff.model.*;
import com.discorp.staff.dao.*;
import com.discorp.staff.service.SetupService;
import com.discorp.staff.xml.InformationResponse;
import com.discorp.staff.xml.InvationCodeResponse;
import com.discorp.staff.xml.SetupRequest;
import com.discorp.staff.xml.SetupResponse;
import com.discorp.utils.Utils;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/17/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetupServiceImpl implements SetupService
{

    private FieldDao fieldDAO;
    private CustomerDao customerDAO;
    private AddressDao addressDAO;
    private ContactDao contactDAO;
    private BusinessEntityDao businessEntityDAO;
    private CommunicationDetailDao communicationDetailDAO;
    private CardInfoDao cardInfoDAO;
    private PaymentDao paymentDAO;
    private DealerDao dealerDAO;
    private CompanyDao companyDAO;
    private CommonInforDao commonInforDAO;

    public CommonInforDao getCommonInforDAO()
    {
        return commonInforDAO;
    }

    public void setCommonInforDAO(CommonInforDao commonInforDAO)
    {
        this.commonInforDAO = commonInforDAO;
    }

    public PaymentDao getPaymentDAO()
    {
        return paymentDAO;
    }

    public void setPaymentDAO(PaymentDao paymentDAO)
    {
        this.paymentDAO = paymentDAO;
    }

    public DealerDao getDealerDAO()
    {
        return dealerDAO;
    }

    public void setDealerDAO(DealerDao dealerDAO)
    {
        this.dealerDAO = dealerDAO;
    }

    public CompanyDao getCompanyDAO()
    {
        return companyDAO;
    }

    public void setCompanyDAO(CompanyDao companyDAO)
    {
        this.companyDAO = companyDAO;
    }

    public CardInfoDao getCardInfoDAO()
    {
        return cardInfoDAO;
    }

    public void setCardInfoDAO(CardInfoDao cardInfoDAO)
    {
        this.cardInfoDAO = cardInfoDAO;
    }

    public ContactDao getContactDAO()
    {
        return contactDAO;
    }

    public void setContactDAO(ContactDao contactDAO)
    {
        this.contactDAO = contactDAO;
    }

    public CommunicationDetailDao getCommunicationDetailDAO()
    {
        return communicationDetailDAO;
    }

    public void setCommunicationDetailDAO(CommunicationDetailDao communicationDetailDAO)
    {
        this.communicationDetailDAO = communicationDetailDAO;
    }

    public BusinessEntityDao getBusinessEntityDAO()
    {
        return businessEntityDAO;
    }

    public void setBusinessEntityDAO(BusinessEntityDao businessEntityDAO)
    {
        this.businessEntityDAO = businessEntityDAO;
    }

    public AddressDao getAddressDAO()
    {
        return addressDAO;
    }

    public void setAddressDAO(AddressDao addressDAO)
    {
        this.addressDAO = addressDAO;
    }


    public FieldDao getFieldDAO()
    {
        return fieldDAO;
    }

    public void setFieldDAO(FieldDao fieldDAO)
    {
        this.fieldDAO = fieldDAO;
    }

    public CustomerDao getCustomerDAO()
    {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDao customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public SetupResponse setupStaflink(SetupRequest setupRequest)
    {

        // check exist
        SetupResponse setupResponse = new SetupResponse();
        List listInfomation = communicationDetailDAO.getCommunicationDetailByEmail(setupRequest.getEmail());
        if (listInfomation.size() > 0)
        {
            setupResponse.setStatus(0);
        }
        else
        {
            // create a dealer
            Integer webId = 0;

            // get max of webId
            List<Dealer> listDealer = dealerDAO.findAllDealer();
            if (listDealer.size() > 0)
            {
                webId = listDealer.get(0).getWebId();
            }

            Dealer dealer = new Dealer();
            dealer.setWebId(webId + 1);
            dealer.setActive(true);
            dealerDAO.saveDealer(dealer);

            // add an record company
            webId = 0;
            Company company = new Company();
            // get max of webId
            List<Company> listCompany = companyDAO.findAllCompany();
            if (listCompany.size() > 0)
            {
                webId = listCompany.get(0).getWebId();
            }
            company.setWebId(webId + 1);
            company.setVersion(1);
            company.setInternalId("0001");
            company.setDealerId(dealer.getWebId());
            company.setDescription(setupRequest.getNameCompany());
            companyDAO.saveCompany(company);

            //insert new record in "stltf_payment"
            Payment payment = new Payment();
            payment.setBaseRate(30d);
            payment.setMemberRate(0.5d);
            java.util.Date currentDate = new java.util.Date();
            Date currentDateSQL = new Date(currentDate.getTime());
            payment.setStartDate(currentDateSQL);
            payment.setDealerId(dealer.getWebId());
            payment.setBillingDelay(60);
            paymentDAO.savePayment(payment);

            // create information for default user
            // create an address
            // get max of webId
            webId = 0;
            List<Address> listAddress = addressDAO.findAllAddress();
            if (listAddress.size() > 0)
            {
                webId = listAddress.get(0).getWebId();
            }
            Address address = new Address();
            // address field
            address.setWebId(webId + 1);
            address.setName(setupRequest.getName());
            address.setCity(setupRequest.getCity());
            address.setState(setupRequest.getSt());
            address.setPostalCode(setupRequest.getZip());
            address.setVersion(1);
            address.setAddressType(0);
            address.setActive(0);
            address.setDealerId(dealer.getWebId());
            addressDAO.saveAddress(address);

            // create a contact
            // get max of webId
            webId = 0;
            List<Contact> listContact = contactDAO.findAllContact();
            if (listContact.size() > 0)
            {
                webId = listContact.get(0).getWebId();
            }
            Contact contact = new Contact();
            contact.setWebId(webId + 1);
            contact.setFirstName(setupRequest.getName());
            contact.setLastName("Administrator");
            contact.setAddressId(address.getWebId());
            contact.setDealerId(dealer.getWebId());
            contactDAO.saveContact(contact);

            // create a communication detail to store email
            // get max of webId
            webId = 0;
            List<CommunicationDetail> listCommunicationDetail = communicationDetailDAO.findAllCommunicationDetail();
            if (listCommunicationDetail.size() > 0)
            {
                webId = listCommunicationDetail.get(0).getWebId();
            }
            CommunicationDetail email = new CommunicationDetail();
            email.setWebId(webId + 1);
            email.setDealerId(dealer.getWebId());
            email.setInformation(setupRequest.getEmail());
            email.setCommunicationMethodId(3); // email method is 3
            email.setAddressId(address.getWebId());
            email.setMain(true);
            communicationDetailDAO.saveCommunicationDetail(email);

            // create a communication detail to store website
            // get max of webId
            if (setupRequest.getWebsite() != null)
            {
                webId = 0;
                List<CommunicationDetail> listCommunicationDetail2 = communicationDetailDAO.findAllCommunicationDetail();
                if (listCommunicationDetail2.size() > 0)
                {
                    webId = listCommunicationDetail2.get(0).getWebId();
                }
                CommunicationDetail website = new CommunicationDetail();
                website.setWebId(webId + 1);
                website.setDealerId(dealer.getWebId());
                website.setInformation(setupRequest.getWebsite());
                website.setCommunicationMethodId(9); // website method is 9
                website.setAddressId(address.getWebId());
                website.setMain(true);
                communicationDetailDAO.saveCommunicationDetail(website);
            }

            //create a communication detail to store number phone
            //get max of webId
            webId = 0;
            List<CommunicationDetail> listCommunicationDetail3 = communicationDetailDAO.findAllCommunicationDetail();
            if (listCommunicationDetail3.size() > 0)
            {
                webId = listCommunicationDetail3.get(0).getWebId();
            }
            CommunicationDetail phone = new CommunicationDetail();
            phone.setWebId(webId + 1);
            phone.setDealerId(dealer.getWebId());
            phone.setInformation(setupRequest.getPhone());
            phone.setCommunicationMethodId(1); // phone method is 1
            phone.setAddressId(address.getWebId());
            phone.setMain(true);
            communicationDetailDAO.saveCommunicationDetail(phone);


            // create business entity
            // get max of webId
            webId = 0;
            List<BusinessEntity> listBusinessEntity = businessEntityDAO.findAllBusinessEntity();
            if (listBusinessEntity.size() > 0)
            {
                webId = listBusinessEntity.get(0).getWebId();
            }
            BusinessEntity businessEntity = new BusinessEntity();
            businessEntity.setWebId(webId + 1);
            businessEntity.setDealerId(dealer.getWebId());
            businessEntityDAO.saveBusinessEntity(businessEntity);

            // create web_user
            // get max of webId
            webId = 0;
            List<Customer> listWebuser = customerDAO.findAllCustomer();
            if (listWebuser.size() > 0)
            {
                webId = listWebuser.get(0).getWebId();
            }
            Customer customer = new Customer();
            customer.setWebId(webId + 1);
            customer.setBusinessEntity(businessEntity.getWebId());
            customer.setDealerId(dealer.getWebId());
            customer.setAddressId(address.getWebId());
            customer.setUid(setupRequest.getUidLdap());
            customerDAO.saveCustomer(customer);

            // create corresponding card
            // get max of webId
            webId = 0;
            List<CardInfor> listCardInfor = cardInfoDAO.findAllCardInfor();
            if (listCardInfor.size() > 0)
            {
                webId = listCardInfor.get(0).getWebId();
            }
            CardInfor cardInfor = new CardInfor();
            cardInfor.setId(webId + 1);
            cardInfor.setWebId(dealer.getWebId());   // set dealer id
            cardInfor.setCardName(setupRequest.getName_card());
            cardInfor.setCardNumber(setupRequest.getNumber_card());
            cardInfor.setExpireDate(setupRequest.getExpire_date());
            cardInfor.setCsvCode(setupRequest.getCsv_code());
            cardInfor.setCardType(setupRequest.getCardType());
            cardInfoDAO.saveCard(cardInfor);

            setupResponse.setStatus(1);
            setupResponse.setDealerId(dealer.getWebId());
        }

        return setupResponse;
    }

    public InvationCodeResponse checkValidateInvitationCode(String invitationCode)
    {
        if (null != invitationCode)
        {
            invitationCode = invitationCode.trim();
            InvationCodeResponse invationCodeResponse = new InvationCodeResponse();
            String sql = "select customer,fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
            sql += " and fieldValue.fieldId=101 and fieldValue.fieldValue=?";
            List list = fieldDAO.checkValidateInvitationCode(invitationCode);
            if (list.size() > 0)
            {
                Object[] objects = (Object[]) list.get(0);
                Customer customer = (Customer) objects[0];
                String name = Utils.mainMail;
                sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
                sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
                sql += " and address.webId=" + customer.getAddressId();
                sql += " and communicationMethod.description like '" + name + "'";

                List listInformation = fieldDAO.getResults(sql);
                if (listInformation.size() > 0)
                {
                    Object[] objectsContact = (Object[]) listInformation.get(0);
                    CommunicationDetail detail = (CommunicationDetail) objectsContact[1];

                    invationCodeResponse.setUserName(detail.getInformation());
                    invationCodeResponse.setValidate(true);
                    invationCodeResponse.setPassWord(invitationCode);

                }

            }
            return invationCodeResponse;
        }
        return new InvationCodeResponse();
    }

    public InformationResponse getInformation()
    {
        InformationResponse informationResponse = new InformationResponse();
        List<CommonInfor> list = commonInforDAO.findAllCommonInfor();
        if (list.size() > 0)
        {
            informationResponse.setWelCome(list.get(0).getWelcomeInfor());
            informationResponse.setAbouts(list.get(0).getAboutUs());
            informationResponse.setTerm(list.get(0).getTermInfor());
        }
        return informationResponse;

    }
}
