package com.discorp.staff.service.impl;


import com.discorp.staff.model.*;
import com.discorp.staff.dao.CustomerDao;
import com.discorp.staff.dao.FieldDao;
import com.discorp.staff.service.CommonUserService;
import com.discorp.staff.xml.Account;
import com.discorp.staff.xml.AccountResponse;
import com.discorp.utils.AccountSortByName;
import com.discorp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonUserServiceImpl implements CommonUserService
{
    private CustomerDao customerDAO;
    private FieldDao fieldDAO;

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


    public AccountResponse findAll(String dealerId)
    {
        List list = customerDAO.getCustomerContactListByDealerId(Integer.parseInt(dealerId));
        return getListUser(list, null);
    }

    public int isValidate(int userId, String userName, String firstName, String lastName, String email)
    {


        List listAccount = customerDAO.getCustomerContactListByUserId(userId);
        if (listAccount.size() > 0)
        {

            if (null == email || "".equals(email))
            {
                return 4;
            }
            if (null != lastName && null != firstName)
            {

                if (!userName.equals("") && !email.equals("") && !lastName.equals("") && !firstName.equals(""))
                {


                    String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
                    sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
                    sql += " and communicationMethod.description like '" + Utils.mainMail + "'";

                    List listInfomation = fieldDAO.getResults(sql);
                    int exists = 0;
                    for (int i = 0; i < listInfomation.size(); i++)
                    {
                        Object[] ObjectInfomation = (Object[]) listInfomation.get(i);
                        CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInfomation[1];


                        if (communicationDetail.getInformation().equals(email))
                        {
                            exists++;
                            if (exists > 1)
                            {
                                return 2;
                            }

                        }
                    }
                }
                else
                {
                    return 3;
                }
                //it's ok
                return 1;
            }
            else
            {
                return 3;
            }

        }
        else
        {
            return 3;
        }


    }

    public int updateUser(int userId, String memberId)
    {
        List<Customer> listCustomer = customerDAO.getCustomerListByUserId(userId);
        if (listCustomer.size() > 0)
        {
            Customer customer = listCustomer.get(0);
            customer.setUid(memberId);
            customerDAO.updateCustomer(customer);
            return 1;
        }
        return 0;
    }

    public AccountResponse searchUser(String name, String dealerId, Boolean isAchive)
    {

        //filter name
        String sql = "select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and customer.uid != 'none' ";
        if (!isAchive)
        {
            sql = "select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and customer.uid != 'none' and customer.uid != 'archive' ";
        }

        sql += " and ( upper(contact.firstName) like ? or upper(contact.lastName) like ? or upper(contact.middleName) like ? )  and customer.dealerId=?";
        List list = fieldDAO.getResults(sql, new Object[]{"%" + name + "%", "%" + name + "%", "%" + name + "%", Integer.parseInt(dealerId)});

        List<Account> listCustomer = getListUser(list, null).getListAccount();
        //filter email,phone...
        sql = "select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and customer.uid != 'none' and customer.dealerId=?";
        if (!isAchive)
        {
            sql = "select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and customer.uid != 'none' and customer.uid != 'archive' and customer.dealerId=?";
        }
        list = fieldDAO.getResults(sql, new Object[]{Integer.parseInt(dealerId)});
        List<Account> listContact = getListUser(list, name).getListAccount();
        //filter office
        sql = "select customer,contact,fieldValue from Customer customer,Contact contact,FieldValue fieldValue where fieldValue.userId=customer.webId ";
        sql += " and customer.uid is not null and customer.uid != 'none' and customer.addressId=contact.addressId and fieldValue.fieldId=16 and customer.dealerId=? and upper(fieldValue.fieldValue) like ?";
        if (!isAchive)
        {
            sql += " and customer.uid!='archive'";
        }

        list = fieldDAO.getResults(sql, new Object[]{Integer.parseInt(dealerId), "%" + name + "%"});
        List<Account> listOffice = getListUser(list, null).getListAccount();


        for (Account account : listContact)
        {
            if (!listCustomer.contains(account))
            {
                listCustomer.add(account);
            }
        }
        for (Account account : listOffice)
        {
            if (!listCustomer.contains(account))
            {
                listCustomer.add(account);
            }
        }
        AccountResponse accountResponse = new AccountResponse();
        if (listCustomer.size() > 0)
        {

            accountResponse.setListAccount(listCustomer);
            return accountResponse;
        }
        else
        {
            return getListUser(list, null);
        }

    }

    public AccountResponse getListUser(List list, String information)
    {
        List<Account> listAccounts = new ArrayList<Account>();
        for (int j = 0; j < list.size(); j++)
        {
            Object[] objects = (Object[]) list.get(j);
            Customer customer = (Customer) objects[0];
            Contact contact = (Contact) objects[1];
            Account account = new Account();
            account.setFirstName(contact.getFirstName());
            account.setLastName(contact.getLastName());
            account.setDisplayName(account.getFirstName() + ", " + account.getLastName());
            account.setId(customer.getWebId());
            String sql = "select communicationDetail,communicationMethod from CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
            sql += " where  communicationDetail.communicationMethodId=communicationMethod.webId";
            sql += " and communicationDetail.addressId=" + contact.getAddressId();
            sql += " and communicationDetail.main = true ";
            List listInfomation;
            //search with email,phone,fax,...
            if (null != information)
            {
                String sqlInfomation = sql + " and upper(communicationDetail.information) like ?";
                listInfomation = fieldDAO.getResults(sqlInfomation, new Object[]{"%" + information + "%"});
                if (listInfomation.size() > 0)
                {
                    listInfomation = fieldDAO.getResults(sql);
                }
                else
                {
                    continue;
                }
            }
            //find All
            else
            {
                listInfomation = fieldDAO.getResults(sql);
            }

            for (int i = 0; i < listInfomation.size(); i++)
            {

                Object[] ObjectInfomation = (Object[]) listInfomation.get(i);

                CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInfomation[0];
                CommunicationMethod communicationMethod = (CommunicationMethod) ObjectInfomation[1];
                if (communicationMethod.getDescription().equals(Utils.mainPhone))
                {
                    account.setTelephoneNumber(communicationDetail.getInformation());
                }
                else if (communicationMethod.getDescription().equals(Utils.mainMail))
                {
                    account.setEmail(communicationDetail.getInformation());
                }


            }
            sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
            sql += " and (fieldValue.fieldId=16 or  fieldValue.fieldId=102) ";
            sql += " and customer.webId = ?";
            List<FieldValue> listFieldValue = customerDAO.getCustomerListFromCityOrImage(customer.getWebId());
            for (FieldValue fieldValue : listFieldValue)
            {
                if (fieldValue.getFieldId() == 16)
                {
                    account.setAddress(fieldValue.getFieldValue());
                }
                else
                {
                    account.setImgPath(fieldValue.getFieldValue());
                }
            }
            account.setMemberId(customer.getUid());
            listAccounts.add(account);
        }
        Collections.sort(listAccounts, new AccountSortByName());
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setListAccount(listAccounts);
        return accountResponse;
    }

    /**
     * get all list receive suggestion
     */
    public AccountResponse getListStaffReceiveSuggestion(Integer dealerId)
    {
        return getListUser(customerDAO.getListStaffReceiveSuggestion(dealerId), null);
    }

}
