//package com.discorp.staff.service.impl;
//
//import com.discorp.staff.dto.CustomerInfoDTO;
//import com.discorp.staff.model.*;
//import com.discorp.staff.dao.*;
//import com.discorp.staff.service.FirstLightService;
//import com.discorp.staff.xml.*;
//import com.discorp.utils.Utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: tiepnm
// * Date: 12/8/12
// * Time: 11:36 AM
// * To change this template use File | Settings | File Templates.
// */
//public class FirstLightServiceImpl implements FirstLightService
//{
//    private CommunicationDetailDao communicationDetailDAO;
//    private DealerDao dealerDAO;
//    private AddressDao addressDAO;
//    private ContactDao contactDAO;
//    private BusinessEntityDao businessEntityDAO;
//    private CustomerDao customerDAO;
//    private FirstLightDao firstLightDAO;
//    private FieldDao fieldDAO;
//    private AttributeValueDao attributeValueDAO;
//    private SearchableAttributeDao searchableAttributeDAO;
//
//
//    public SearchableAttributeDao getSearchableAttributeDAO()
//    {
//        return searchableAttributeDAO;
//    }
//
//    public void setSearchableAttributeDAO(SearchableAttributeDao searchableAttributeDAO)
//    {
//        this.searchableAttributeDAO = searchableAttributeDAO;
//    }
//
//    public AttributeValueDao getAttributeValueDAO()
//    {
//        return attributeValueDAO;
//    }
//
//    public void setAttributeValueDAO(AttributeValueDao attributeValueDAO)
//    {
//        this.attributeValueDAO = attributeValueDAO;
//    }
//
//    public FirstLightDao getFirstLightDAO()
//    {
//        return firstLightDAO;
//    }
//
//    public void setFirstLightDAO(FirstLightDao firstLightDAO)
//    {
//        this.firstLightDAO = firstLightDAO;
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
//    public AddressDao getAddressDAO()
//    {
//        return addressDAO;
//    }
//
//    public void setAddressDAO(AddressDao addressDAO)
//    {
//        this.addressDAO = addressDAO;
//    }
//
//    public ContactDao getContactDAO()
//    {
//        return contactDAO;
//    }
//
//    public void setContactDAO(ContactDao contactDAO)
//    {
//        this.contactDAO = contactDAO;
//    }
//
//    public BusinessEntityDao getBusinessEntityDAO()
//    {
//        return businessEntityDAO;
//    }
//
//    public void setBusinessEntityDAO(BusinessEntityDao businessEntityDAO)
//    {
//        this.businessEntityDAO = businessEntityDAO;
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
//    public AddCustomerResponse registerCustomer(AddCustomerRequest addCustomerRequest)
//    {
//        AddCustomerResponse addCustomerResponse = new AddCustomerResponse();
//        List listInformation = communicationDetailDAO.getCommunicationDetailByEmail(addCustomerRequest.getEmail());
//        if (listInformation.size() > 0)
//        {
//            addCustomerResponse.setStatus(0);
//        }
//        else
//        {
//            Integer webId = 0;
//            Integer dealerId = Integer.parseInt(addCustomerRequest.getDealerId());
//
//            // create information for default user
//            // create an address
//            // get max of webId
//            webId = 0;
//            List<Address> listAddress = addressDAO.findAllAddress();
//            if (listAddress.size() > 0)
//            {
//                webId = listAddress.get(0).getWebId();
//            }
//            Address address = new Address();
//            // address field
//            address.setWebId(webId + 1);
//            address.setCity(addCustomerRequest.getCity());
//            address.setState(addCustomerRequest.getState());
//            address.setPostalCode(addCustomerRequest.getZip());
//            address.setVersion(1);
//            address.setAddressType(0);
//            address.setActive(0);
//            address.setDealerId(dealerId);
//            addressDAO.saveAddress(address);
//
//            // create a contact
//            // get max of webId
//            webId = 0;
//            List<Contact> listContact = contactDAO.findAllContact();
//            if (listContact.size() > 0)
//            {
//                webId = listContact.get(0).getWebId();
//            }
//            Contact contact = new Contact();
//            contact.setWebId(webId + 1);
//            contact.setFirstName(addCustomerRequest.getFirstName());
//            contact.setLastName("");
//            contact.setAddressId(address.getWebId());
//            contact.setDealerId(dealerId);
//            contactDAO.saveContact(contact);
//
//            // create a communication detail to store email
//            // get max of webId
//            webId = 0;
//            List<CommunicationDetail> listCommunicationDetail = communicationDetailDAO.findAllCommunicationDetail();
//            if (listCommunicationDetail.size() > 0)
//            {
//                webId = listCommunicationDetail.get(0).getWebId();
//            }
//            CommunicationDetail email = new CommunicationDetail();
//            email.setWebId(webId + 1);
//            email.setDealerId(dealerId);
//            email.setInformation(addCustomerRequest.getEmail());
//            email.setCommunicationMethodId(3); // email method is 3
//            email.setAddressId(address.getWebId());
//            email.setMain(true);
//            communicationDetailDAO.saveCommunicationDetail(email);
//
//            // create business entity
//            // get max of webId
//            webId = 0;
//            List<BusinessEntity> listBusinessEntity = businessEntityDAO.findAllBusinessEntity();
//            if (listBusinessEntity.size() > 0)
//            {
//                webId = listBusinessEntity.get(0).getWebId();
//            }
//            BusinessEntity businessEntity = new BusinessEntity();
//            businessEntity.setWebId(webId + 1);
//            businessEntity.setDealerId(dealerId);
//            // when user not yet verify code, he still is deactivate
//            businessEntity.setActive(false);
//            businessEntity.setVerificationCode(addCustomerRequest.getVerificationCode());
//            businessEntityDAO.saveBusinessEntity(businessEntity);
//
//            // create web_user
//            // get max of webId
//            webId = 0;
//
//            List<Customer> listWebUser = customerDAO.findAllCustomer();
//            if (listWebUser.size() > 0)
//            {
//                webId = listWebUser.get(0).getWebId();
//            }
//            Customer webUser = new Customer();
//            webUser.setWebId(webId + 1);
//            webUser.setBusinessEntity(businessEntity.getWebId());
//            webUser.setDealerId(dealerId);
//            webUser.setAddressId(address.getWebId());
//            webUser.setUid(addCustomerRequest.getUidLdap());
//            customerDAO.saveCustomer(webUser);
//
//
//            addCustomerResponse.setStatus(1);
//            addCustomerResponse.setDealerId(dealerId);
//        }
//        return addCustomerResponse;
//
//    }
//
//    public Account getAccount(CustomerRequest customerRequest)
//    {
//        Account account = new Account();
//        // get dealer's customer
//        Dealer dealer = firstLightDAO.getDealerByUidUser(customerRequest.getUid());
//        List list = fieldDAO.getResults("select customer,contact,communicationdetail, business from Customer customer ,Contact contact ,Address address , CommunicationDetail communicationdetail , BusinessEntity business  where customer.addressId=contact.addressId  and customer.uid=? and customer.businessEntity = business.webId and customer.addressId = address.webId and address.webId = communicationdetail.addressId and communicationdetail.communicationMethodId =3", new Object[]{customerRequest.getUid()});
//        // check dealer's  status activate
//        Integer roleId = Integer.parseInt(customerRequest.getRoleId());
//        if (null == dealer)
//        {
//            account.setActive(false);
//        }
//        else
//        {
//            if (dealer.getActive())
//            {
//                Object[] objects = (Object[]) list.get(0);
//                Customer webUser = (Customer) objects[0];
//                Contact contact = (Contact) objects[1];
//                BusinessEntity businessEntity = (BusinessEntity) objects[3];
//                CommunicationDetail communicationDetail = (CommunicationDetail) objects[2];
//                if ((roleId == 1) || (roleId == 2))
//                {
//                    // if user is staff. Staff always active
//                    account.setActive(true);
//                }
//                else
//                {
//                    account.setActive(businessEntity.getActive());
//                }
//                String dealerId = webUser.getDealerId().toString();
//                account.setDealerId(dealerId);
//                account.setUserName(communicationDetail.getInformation());
//                account.setFirstName(contact.getFirstName());
//                account.setMemberId(customerRequest.getUid());
//
//            }
//            else
//            {
//                account.setActive(false);
//            }
//
//        }
//        return account;
//    }
//
//    public AddCustomerResponse validateVerificationCode(ValidateVerificationCodeRequest validateVerificationCodeRequest)
//    {
//        String uid = validateVerificationCodeRequest.getUid();
//        String verificationCode = validateVerificationCodeRequest.getVerificationCode();
//        int result = firstLightDAO.validateVerificationCode(uid, verificationCode);
//        AddCustomerResponse addCustomerResponse = new AddCustomerResponse();
//        addCustomerResponse.setStatus(result);
//        return addCustomerResponse;
//    }
//
//    // get conditions search to build UI search
//    public SearchConditionResponse getConditionSearch(SearchConditionRequest searchConditionRequest)
//    {
//
//        SearchConditionResponse response = new SearchConditionResponse();
//        List<SearchableAttribute> listSearchAtt = searchableAttributeDAO.findAllSeachabel();
//        List<SearchConditionResponse.SearchAttribute> listSearchAttributeRes = new ArrayList<SearchConditionResponse.SearchAttribute>();
//        SearchConditionResponse.SearchAttribute searchAttributeRes;
//        List<SearchConditionResponse.Operator> listOperator;
//        List<SearchConditionResponse.AttributeValue> listValueRes;
//        for (SearchableAttribute attribute : listSearchAtt)
//        {
//            searchAttributeRes = new SearchConditionResponse.SearchAttribute();
//            searchAttributeRes.setId(attribute.getAttributeId());
//            searchAttributeRes.setNameAttribute(attribute.getAttributeName());
//            searchAttributeRes.setTypeData(attribute.getTypeData());
//            listOperator = new ArrayList<SearchConditionResponse.Operator>();
//            SearchConditionResponse.Operator operatorRes;
//            // check type_data to get list operator
//            if (attribute.getTypeData().equals("1"))
//            {
//                // 1 is string
//                for (Utils.OPERATOR_DATE operator : Utils.OPERATOR_DATE.values())
//                {
//                    operatorRes = new SearchConditionResponse.Operator();
//                    operatorRes.setOperatorId(1);
//                    operatorRes.setOperatorName(operator.toString());
//                    listOperator.add(operatorRes);
//                }
//
//            }
//            else if (attribute.getTypeData().equals("2"))
//            {
//                // 2 is integer
//                for (Utils.OPERATOR_INTEGER operatorInteger : Utils.OPERATOR_INTEGER.values())
//                {
//                    operatorRes = new SearchConditionResponse.Operator();
//                    operatorRes.setOperatorId(2);
//                    operatorRes.setOperatorName(operatorInteger.toString());
//                    listOperator.add(operatorRes);
//                }
//
//            }
//            else if (attribute.getTypeData().equals("3"))
//            {
//                // 3 is date
//                for (Utils.OPERATOR_STRING operatorString : Utils.OPERATOR_STRING.values())
//                {
//                    operatorRes = new SearchConditionResponse.Operator();
//                    operatorRes.setOperatorId(3);
//                    operatorRes.setOperatorName(operatorString.toString());
//                    listOperator.add(operatorRes);
//                }
//
//            }
//            searchAttributeRes.setOperators(listOperator);
//
//            List<AttributeValue> listValue = attributeValueDAO.findAttributeValueById(attribute.getAttributeId());
//            if (listValue.size() > 0)
//            {
////                attribute.set
//                listValueRes = new ArrayList<SearchConditionResponse.AttributeValue>();
//                SearchConditionResponse.AttributeValue attributeValuerRes;
//                if (listValue.size() > 0)
//                {
//                    for (AttributeValue value : listValue)
//                    {
//                        attributeValuerRes = new SearchConditionResponse.AttributeValue();
//                        attributeValuerRes.setAttributeValueId(value.getAttributeId());
//                        attributeValuerRes.setAttributeName(value.getValueName());
//                        attributeValuerRes.setAttributeValue(value.getValue());
//                        listValueRes.add(attributeValuerRes);
//                    }
//                    searchAttributeRes.setAttributeValues(listValueRes);
//                }
//
//            }
//            listSearchAttributeRes.add(searchAttributeRes);
//
//        }
//        response.setSearchAttributes(listSearchAttributeRes);
//        return response;
//    }
//
//    public String getOperatorQuery(String operator, String type, String value)
//    {
//        // String
//        if (type.equals("1"))
//        {
//            if (operator.equals(Utils.OPERATOR_STRING.IS.toString()))
//            {
//                return "like";
//            }
//            else if (operator.equals(Utils.OPERATOR_STRING.IS_NOT.toString()))
//            {
//                return "not like";
//            }
//        }
//        // Integer
//        else if (type.equals("2"))
//        {
//            if (operator.equals(Utils.OPERATOR_INTEGER.GREATER.toString()))
//            {
//                return ">";
//            }
//            else if (operator.equals(Utils.OPERATOR_INTEGER.LESS.toString()))
//            {
//                return "<";
//            }
//        }
//        // Date
//        else if (type.equals("3") && operator.equals(Utils.OPERATOR_DATE.EQUAL.toString()))
//        {
//            if (value.equals(Utils.OPERATOR_DATE.BEFORE_YEAR.toString()))
//            {
//                return ">= now() - INTERVAL '1 years'";
//            }
//            else if (value.equals(Utils.OPERATOR_DATE.BEFORE_MONTH.toString()))
//            {
//                return ">= now() - INTERVAL '1 month'";
//            }
//            else if (value.equals(Utils.OPERATOR_DATE.BEFORE_WEEK.toString()))
//            {
//                return ">= now() - INTERVAL '1 week'";
//            }
//        }
//        return "";
//
//    }
//
//    public List<CustomerInfoDTO> executeDynamicSearchQuery(DynamicSearchRequest dynamicSearchRequest)
//    {
//        List<String> listTable = new ArrayList<String>();
//        List<String> listJoinCondition = new ArrayList<String>();
//        List<String> listUserInput = new ArrayList<String>();
//        String sqlSearch = "select web_user.address_id from";
//        // the syntax of sql search query
//        //  SELECT web_user.uid FROM + listTable + WHERE + listJoinCondition + listUserInput
//        for (DynamicSearchRequest.Request request : dynamicSearchRequest.getRequestList())
//        {
//            String inputValue = request.getValue();
//            String operator = request.getOperator();
//            String attribute = request.getAttribute();
//            List<SearchableAttribute> list = searchableAttributeDAO.searchByAttributeName(attribute);
//            // get list table
//            for (SearchableAttribute s : list)
//            {
//                if (!listTable.contains(s.getTargetTable()))
//                {
//                    listTable.add(s.getTargetTable());
//                }
//                if (!listTable.contains(s.getLinkTable()))
//                {
//                    listTable.add(s.getLinkTable());
//                }
//            }
//            // get list join condition
//            for (SearchableAttribute searchableAttributeItem : list)
//            {
//                if (!operator.equals("ALL"))
//                {
//                    listJoinCondition.add(searchableAttributeItem.getTargetTable() + "."
//                            + searchableAttributeItem.getTargetJoinColumn()
//                            + "=" + searchableAttributeItem.getLinkTable() + "." + searchableAttributeItem.getLinkJoinColumn());
//                }
//                if (null != searchableAttributeItem.getTargetColumn() && !operator.equals(Utils.OPERATOR_STRING.ALL.toString()))
//                {
//                    // get list user input value
//                    String typeData = searchableAttributeItem.getTypeData();
//                    if (!operator.equals("ALL"))
//                    {
//                        if (typeData.equals("1"))
//                        {
//                            listUserInput.add("UPPER(" + searchableAttributeItem.getTargetTable() + "."
//                                    + searchableAttributeItem.getTargetColumn()
//                                    + ") " + getOperatorQuery(operator, "1", inputValue) + " UPPER('%" + inputValue + "%')");
//                        }
//                        else if (typeData.equals("2"))
//                        {
//                            listUserInput.add(searchableAttributeItem.getTargetTable() + "." + searchableAttributeItem.getTargetColumn()
//                                    + getOperatorQuery(operator, "2", inputValue) + inputValue);
//                        }
//                        else if (typeData.equals("3"))
//                        {
//                            listUserInput.add("to_timestamp(" + searchableAttributeItem.getTargetTable() + "."
//                                    + searchableAttributeItem.getTargetColumn() + "," + " 'YYYY-MM-DD')"
//                                    + getOperatorQuery(operator, "3", inputValue));
//
//                        }
//                    }
//
//                }
//            }
//        }
//        // combine search query
//        if (listTable.size() > 0)
//        {
//            for (int i = 0; i < listTable.size() - 1; i++)
//            {
//                sqlSearch += " " + listTable.get(i) + ",";
//            }
//            sqlSearch += " " + listTable.get(listTable.size() - 1) + " where web_user.dealer_id = " + dynamicSearchRequest.getDealerId().toString();
//        }
//        if (listJoinCondition.size() > 0)
//        {
//            sqlSearch += " and ";
//            for (int i = 0; i < listJoinCondition.size() - 1; i++)
//            {
//                sqlSearch += listJoinCondition.get(i) + " and ";
//            }
//            sqlSearch += listJoinCondition.get(listJoinCondition.size() - 1);
//        }
//        if (listUserInput.size() > 0)
//        {
//            sqlSearch += " and ";
//            for (int i = 0; i < listUserInput.size() - 1; i++)
//            {
//                sqlSearch += listUserInput.get(i) + " and ";
//            }
//            sqlSearch += listUserInput.get(listUserInput.size() - 1);
//        }
//        System.out.println(sqlSearch);
//        List<CustomerInfoDTO> customerInfoDTOs = new ArrayList<CustomerInfoDTO>();
//        List<String> listAddressId = searchableAttributeDAO.searchCustomerMarketing(sqlSearch);
//        List<String> listAddress = new ArrayList<String>();
//        if (listAddressId.size() > 0)
//        {
//            for (String addressId : listAddressId)
//            {
//                if (!listAddress.contains(addressId))
//                {
//                    CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
//                    //get first,last contactDAO
//                    List<Contact> contactList = contactDAO.getContactAddressListByAddressId(Integer.parseInt(addressId));
//                    if (contactList.size() > 0)
//                    {
//                        customerInfoDTO.setFirstName(contactList.get(0).getFirstName());
//                        customerInfoDTO.setLastName(contactList.get(0).getLastName());
//                    }
//                    //get city addressDAO
//                    List<Address> addressList = addressDAO.getAddressListById(Integer.parseInt(addressId));
//                    if (addressList.size() > 0)
//                    {
//                        customerInfoDTO.setCity(addressList.get(0).getCity());
//                    }
//                    // get phone, email communicationDetailDAO
//                    List<CommunicationDetail> communicationDetailList = communicationDetailDAO.getCommunicationDetailByAddressId(Integer.parseInt(addressId));
//                    if (communicationDetailList.size() > 0)
//                    {
//                        for (CommunicationDetail com : communicationDetailList)
//                        {
//                            if (com.getCommunicationMethodId() == 1)
//                            {
//                                customerInfoDTO.setPhoneNumber(com.getInformation());
//                            }
//                            if (com.getCommunicationMethodId() == 3)
//                            {
//                                customerInfoDTO.setEmail(com.getInformation());
//                            }
//                        }
//                    }
//                    customerInfoDTOs.add(customerInfoDTO);
//                    listAddress.add(addressId);
//                }
//
//            }
//        }
//        return customerInfoDTOs;
//    }
//}
