package com.discorp.staff.service.impl;

import com.discorp.staff.model.*;
import com.discorp.staff.dao.*;
import com.discorp.staff.service.AdminService;
import com.discorp.staff.xml.Account;
import com.discorp.staff.xml.CompanyResponse;
import com.discorp.staff.xml.EditEntryTextRequest;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminServiceImpl implements AdminService
{

    private PaymentDao paymentDAO;
    private CompanyDao companyDAO;
    private TextEntryDao textEntryDAO;
    private CustomerDao customerDAO;
    private FieldDao fieldDAO;
    private FieldValueDao fieldValueDAO;
    private BillingHistoryDao billingHistoryDAO;
    private CardInfoDao cardInfoDAO;
    private UserPaymentDao userPaymentDAO;

    public FieldValueDao getFieldValueDAO()
    {
        return fieldValueDAO;
    }

    public void setFieldValueDAO(FieldValueDao fieldValueDAO)
    {
        this.fieldValueDAO = fieldValueDAO;
    }

    public UserPaymentDao getUserPaymentDAO()
    {
        return userPaymentDAO;
    }

    public void setUserPaymentDAO(UserPaymentDao userPaymentDAO)
    {
        this.userPaymentDAO = userPaymentDAO;
    }

    public CardInfoDao getCardInfoDAO()
    {
        return cardInfoDAO;
    }

    public void setCardInfoDAO(CardInfoDao cardInfoDAO)
    {
        this.cardInfoDAO = cardInfoDAO;
    }

    public FieldDao getFieldDAO()
    {
        return fieldDAO;
    }

    public void setFieldDAO(FieldDao fieldDAO)
    {
        this.fieldDAO = fieldDAO;
    }

    public BillingHistoryDao getBillingHistoryDAO()
    {
        return billingHistoryDAO;
    }

    public void setBillingHistoryDAO(BillingHistoryDao billingHistoryDAO)
    {
        this.billingHistoryDAO = billingHistoryDAO;
    }

    public PaymentDao getPaymentDAO()
    {
        return paymentDAO;
    }

    public void setPaymentDAO(PaymentDao paymentDAO)
    {
        this.paymentDAO = paymentDAO;
    }

    public CompanyDao getCompanyDAO()
    {
        return companyDAO;
    }

    public void setCompanyDAO(CompanyDao companyDAO)
    {
        this.companyDAO = companyDAO;
    }

    public TextEntryDao getTextEntryDAO()
    {
        return textEntryDAO;
    }

    public void setTextEntryDAO(TextEntryDao textEntryDAO)
    {
        this.textEntryDAO = textEntryDAO;
    }

    public CustomerDao getCustomerDAO()
    {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDao customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    public CompanyResponse getAdminList()
    {

        List<Integer> list = paymentDAO.getPaymentIdList();
        List<CompanyResponse.Company> listCompanyDTO = new ArrayList<CompanyResponse.Company>();
        CompanyResponse companyResponse = new CompanyResponse();
        for (Integer i : list)
        {
            if (null != i)
            {
                CompanyResponse.Company companyDTO = new CompanyResponse.Company();
                List<Account> listAdminChild = new ArrayList<Account>();
                List<String> listCompanyName = companyDAO.getCompanyNameByDealerId(i);
                if (listCompanyName.size() != 0)
                {
                    String companyName = listCompanyName.get(0);
                    if (companyName.length() > 17)
                    {
                        companyName = companyName.substring(0, 17) + "...";
                    }
                    companyDTO.setCompanyName(companyName);
                    companyDTO.setDealerId(String.valueOf(i));
                }

                List<Customer> listDealer = customerDAO.getCustomerListByDealerId(i);
                companyDTO.setNoMember(String.valueOf(listDealer.size()));

                // payment
                List<Payment> listPayment = paymentDAO.getPaymentListByDealerId(i);
                if (listPayment.size() > 0)
                {
                    String pattern = "MM/dd/yyyy";
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    NumberFormat formatCurrentcy = NumberFormat.getCurrencyInstance();
                    companyDTO.setStartDate(format.format(listPayment.get(0).getStartDate()));
                    companyDTO.setBillingDelay(listPayment.get(0).getBillingDelay().toString() + " days");
                    companyDTO.setRate(formatCurrentcy.format(listPayment.get(0).getBaseRate()) + "/" + listPayment.get(0).getMemberRate());
                    Double amount = listPayment.get(0).getBaseRate() + (listDealer.size() * listPayment.get(0).getMemberRate());
                    companyDTO.setMonthlyBilling(formatCurrentcy.format(amount));

                }
                for (Customer customer : listDealer)
                {
                    Integer userId = customer.getWebId();
                    String uid = customer.getUid();
                    List<FieldValue> listHR = fieldValueDAO.getFieldValueListByUserIdAndRole(userId, "ROLE_SETUP_ADMIN");
                    if (listHR.size() > 0)
                    {
                        Account adminDTOChild = new Account();
                        //get email
                        List listEmail = fieldDAO.getResults("select author,contact,communicationdetail,address from Customer author ,Contact contact ,Address address , CommunicationDetail communicationdetail  where author.addressId=contact.addressId  and author.uid=? and author.addressId = address.id and address.id = communicationdetail.addressId and communicationdetail.communicationMethodId =3", new Object[]{uid});
                        if (listEmail.size() > 0)
                        {
                            Object[] objects3 = (Object[]) listEmail.get(0);
                            Contact contact = (Contact) objects3[1];
                            CommunicationDetail communicationDetail = (CommunicationDetail) objects3[2];
                            adminDTOChild.setId(userId);
                            adminDTOChild.setFirstName(contact.getFirstName());
                            adminDTOChild.setEmail(communicationDetail.getInformation());

                            /*listAdminChild.add(adminDTOChild)*/
                        }
                        // get phone
                        List listPhone = fieldDAO.getResults("select author,contact,communicationdetail from Customer author ,Contact contact ,Address address , CommunicationDetail communicationdetail  where author.addressId=contact.addressId  and author.uid=? and author.addressId = address.id and address.id = communicationdetail.addressId and communicationdetail.communicationMethodId =1", new Object[]{uid});//hibernateTemplate.find("select author,contact,communicationdetail from Customer author ,Contact contact ,Address address , CommunicationDetail communicationdetail  where author.addressId=contact.addressId  and author.uid=? and author.addressId = address.id and address.id = communicationdetail.addressId and communicationdetail.communicationMethodId =1", new Object[]{uid});
                        if (listPhone.size() > 0)
                        {
                            Object[] objects3 = (Object[]) listPhone.get(0);
                            CommunicationDetail communicationDetail = (CommunicationDetail) objects3[2];
                            adminDTOChild.setPhone(communicationDetail.getInformation());

                        }
                        listAdminChild.add(adminDTOChild);
                    }

                }

                companyDTO.setListAdminDTO(listAdminChild);
                List<BillingHistory> listHistory = billingHistoryDAO.getBillingHistoryListByDealerId(i);//hibernateTemplate.find("from BillingHistory billingHistory where billingHistory.dealerId=?", new Object[]{i});//query.list()
                NumberFormat formatCurrency = NumberFormat.getCurrencyInstance();

                Double amount = 0.0;
                for (BillingHistory history : listHistory)
                {
                    amount = amount + history.getAmount();
                }
                companyDTO.setTotalBilled(formatCurrency.format(amount));


                listCompanyDTO.add(companyDTO);
            }

        }
        companyResponse.setListCompany(listCompanyDTO);
        return companyResponse;
    }

    public CompanyResponse getBillingHistoryListByDealerId(Integer dealerId)
    {
        CompanyResponse companyResponse = new CompanyResponse();
        List<BillingHistory> list = billingHistoryDAO.getBillingHistoryListByDealerId(dealerId);
        List<CompanyResponse.BillingHistory> listBillingHistory = new ArrayList<CompanyResponse.BillingHistory>();
        for (BillingHistory billingHistory : list)
        {
            CompanyResponse.BillingHistory billingHistoryDTO = new CompanyResponse.BillingHistory();
            billingHistoryDTO.setFrom(billingHistory.getFrom());
            billingHistoryDTO.setTo(billingHistory.getTo());
            NumberFormat formatCurrentcy = NumberFormat.getCurrencyInstance();
            billingHistoryDTO.setAmount(formatCurrentcy.format(billingHistory.getAmount()));
            billingHistoryDTO.setNote(billingHistory.getNote());
            listBillingHistory.add(billingHistoryDTO);
        }
        companyResponse.setListBillingHistory(listBillingHistory);
        return companyResponse;
    }

    public CompanyResponse getPaymentList()
    {
        CompanyResponse companyResponse = new CompanyResponse();
        List<CompanyResponse.AdminPayment> listAdminPayment = new ArrayList<CompanyResponse.AdminPayment>();
        List<Integer> list = paymentDAO.getPaymentIdList();
        for (Integer i : list)
        {
            if (null != i)
            {

                CompanyResponse.AdminPayment adminDTO = new CompanyResponse.AdminPayment();
                List<Customer> listDealer = customerDAO.getCustomerListByDealerId(i);
                boolean isDealerId = false;
                adminDTO.setId(String.valueOf(i));
                Customer author = listDealer.get(0);
                List listEmail = fieldDAO.getResults("select author,contact,communicationdetail from Customer author ,Contact contact ,Address address , CommunicationDetail communicationdetail  where author.addressId=contact.addressId  and author.uid=? and author.addressId = address.id and address.id = communicationdetail.addressId and communicationdetail.communicationMethodId =3", new Object[]{author.getUid()});
                if (listEmail.size() > 0)
                {
                    isDealerId = true;

                }
                List<CardInfor> listCardInfo = cardInfoDAO.getListCardById(i);
                if (listCardInfo.size() > 0)
                {
                    CardInfor cardInfo = listCardInfo.get(0);
                    adminDTO.setCard_name(cardInfo.getCardName());
                    adminDTO.setCard_number(cardInfo.getCardNumber());
                    adminDTO.setCsv_code(cardInfo.getCsvCode());
                    adminDTO.setExpire_date(cardInfo.getExpireDate());

                    List<Payment> listPayment = paymentDAO.getPaymentListByDealerId(i);///hibernateTemplate.find("from Payment payment where payment.dealerId=?",new Object[]{i});
                    if (listPayment.size() > 0)
                    {
                        NumberFormat formatCurrentcy = NumberFormat.getCurrencyInstance();
                        Double amount = listPayment.get(0).getBaseRate() + (listDealer.size() * listPayment.get(0).getMemberRate());
                        adminDTO.setAmount(formatCurrentcy.format(amount));
                    }

                    if (isDealerId && listPayment.size() > 0)
                    {
                        //delay date
                        Calendar c = Calendar.getInstance();
                        Date startDate = listPayment.get(0).getStartDate();
                        c.set(startDate.getYear(), startDate.getMonth(), startDate.getDate());
                        c.add(Calendar.DAY_OF_YEAR, listPayment.get(0).getBillingDelay());
                        Date dateDelay = c.getTime();
                        //current-Date
                        Calendar c2 = Calendar.getInstance();
                        c2.set(new Date().getYear(), new Date().getMonth(), new Date().getDate());


                        if (c2.getTime().compareTo(dateDelay) >= 0)
                        {

                            List<UserPayment> listUserPayment = userPaymentDAO.getUserPaymentListByDealerId(i);
                            if (listUserPayment.size() > 0)
                            {
                                adminDTO.setCustomer_id(listUserPayment.get(0).getCustomerId());
                                adminDTO.setPayment_id(listUserPayment.get(0).getPaymentId());
                            }
                            listAdminPayment.add(adminDTO);
                        }

                    }
                }

            }

        }
        companyResponse.setListAdminPayment(listAdminPayment);
        return companyResponse;
    }

    public CompanyResponse getEntryTextList()
    {
        CompanyResponse companyResponse = new CompanyResponse();
        List<CompanyResponse.TextEntry> listTextEntry = new ArrayList<CompanyResponse.TextEntry>();
        List<TextEntry> result = textEntryDAO.findAllTextEntry();//hibernateTemplate.find("from TextEntry c");
        if (result.size() > 0)
        {
            for (TextEntry textEntry : result)
            {
                CompanyResponse.TextEntry textEntryTemp = new CompanyResponse.TextEntry();
                textEntryTemp.setId(textEntry.getId());
                textEntryTemp.setBlock_name(textEntry.getBlockName());
                textEntryTemp.setBlock_content(textEntry.getBlockContent());
                listTextEntry.add(textEntryTemp);

            }
        }
        companyResponse.setListEntryText(listTextEntry);
        return companyResponse;
    }

    public CompanyResponse updateEntryText(EditEntryTextRequest editEntryTextRequest)
    {
        List<TextEntry> result = textEntryDAO.getTextEntryListById(editEntryTextRequest.getId());
        if (result.size() > 0)
        {
            TextEntry textEntry = result.get(0);
            textEntry.setBlockContent(editEntryTextRequest.getBlockContent());
            textEntryDAO.updateTextEntry(textEntry);
        }
        return new CompanyResponse();
    }
}
