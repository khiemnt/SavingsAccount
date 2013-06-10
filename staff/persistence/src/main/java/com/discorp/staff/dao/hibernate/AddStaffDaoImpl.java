package com.discorp.staff.dao.hibernate;

import com.discorp.staff.model.*;
import com.discorp.staff.dao.AddStaffDao;
import com.discorp.staff.xml.Account;
import com.discorp.staff.xml.AccountResponse;
import com.discorp.staff.xml.StaffDTOResponse;
import com.discorp.staff.xml.StaffInfoDTOResponse;
import com.discorp.utils.AccountSortByName;
import com.discorp.utils.Utils;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/13/12
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddStaffDaoImpl implements AddStaffDao
{
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    private String nextSessionId()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(30, random).toString(32);
    }

    public StaffDTOResponse saveStaff()
    {

        List<Block> listBlock = hibernateTemplate.find("from Block order by position asc");
        StaffDTOResponse staffDTO = new StaffDTOResponse();

        List<StaffInfoDTOResponse> listInfo = new ArrayList<StaffInfoDTOResponse>();
        staffDTO.setListInfor(listInfo);
        staffDTO.setRandom(nextSessionId());
        for (Block block : listBlock)
        {
            StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
            staffInfoDTO.setBlockId(block.getBlockId());
            staffInfoDTO.setType(block.getBlockDescription());
            staffInfoDTO.setMandatory(block.getMandatory());

            listInfo.add(staffInfoDTO);
        }
        return staffDTO;
    }


    public AccountResponse getListCustomer(int userId, String dealerId)
    {
        List<Customer> listCustomer = hibernateTemplate.find("from Customer customer where customer.webId=? and customer.uid like 'none'", userId);
        if (listCustomer.size() > 0)
        {
            Customer customer = listCustomer.get(0);
            int addressId = customer.getAddressId();
            Integer bussiness_id = customer.getBusinessEntity();
            hibernateTemplate.delete(customer);
            List<Contact> listContact = hibernateTemplate.find("from Contact contact where contact.addressId=?", addressId);
            if (listContact.size() > 0)
            {
                Contact contact = listContact.get(0);
                hibernateTemplate.delete(contact);
            }
            List<CommunicationDetail> listCommunicationDetail = hibernateTemplate.find("from CommunicationDetail communicationDetail where communicationDetail.addressId=?", addressId);
            for (CommunicationDetail communicationDetail : listCommunicationDetail)
            {
                hibernateTemplate.delete(communicationDetail);
            }

            List<Address> listAddress = hibernateTemplate.find("from Address address where address.webId=?", addressId);
            if (listAddress.size() > 0)
            {
                Address address = listAddress.get(0);
                hibernateTemplate.delete(address);
            }
            if (null != bussiness_id)
            {

                List<BusinessEntityAddress> listBusinessEntityAddresses = hibernateTemplate.find("from BusinessEntityAddress businessEntityAddress where businessEntityAddress.businessEntityId=?", bussiness_id);
                for (BusinessEntityAddress businessEntityAddress : listBusinessEntityAddresses)
                {
                    hibernateTemplate.delete(businessEntityAddress);
                }
                List<BusinessEntity> listBusinessEntity = hibernateTemplate.find("from BusinessEntity businessEntity where businessEntity.webId=?", bussiness_id);
                if (listBusinessEntity.size() > 0)
                {
                    BusinessEntity businessEntity = listBusinessEntity.get(0);
                    hibernateTemplate.delete(businessEntity);
                }
            }

            List<FieldValue> listFieldValue = hibernateTemplate.find("from FieldValue fieldValue where fieldValue.userId=?", userId);
            for (FieldValue fieldValue : listFieldValue)
            {
                hibernateTemplate.delete(fieldValue);
            }

        }


        List list = hibernateTemplate.find("select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and  customer.uid != 'none' and  customer.uid != 'archive' and customer.dealerId=?", Integer.parseInt(dealerId));
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
            String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
            sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
            sql += " and address.webId=" + contact.getAddressId();
            List listInfomation = hibernateTemplate.find(sql);
            for (int i = 0; i < listInfomation.size(); i++)
            {

                Object[] ObjectInfomation = (Object[]) listInfomation.get(i);
                Address address = (Address) ObjectInfomation[0];
                CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInfomation[1];
                CommunicationMethod communicationMethod = (CommunicationMethod) ObjectInfomation[2];
                if (communicationMethod.getDescription().equals(Utils.mainPhone))
                {
                    account.setTelephoneNumber(communicationDetail.getInformation());
                }
                else if (communicationMethod.getDescription().equals(Utils.mainMail))
                {
                    account.setEmail(communicationDetail.getInformation());
                }

                account.setAddress(address.getStreet());

            }
            sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
            sql += " and fieldValue.fieldId=16";
            sql += " and customer.webId = ?";

            List<FieldValue> listFieldValue = hibernateTemplate.find(sql, customer.getWebId());
            if (listFieldValue.size() > 0)
            {
                FieldValue fieldValue = listFieldValue.get(0);
                account.setAddress(fieldValue.getFieldValue());
            }

            account.setMemberId(customer.getUid());
            listAccounts.add(account);
        }

        AccountResponse accountResponse = new AccountResponse();
        Collections.sort(listAccounts, new AccountSortByName());
        accountResponse.setListAccount(listAccounts);
        return accountResponse;
    }
}
