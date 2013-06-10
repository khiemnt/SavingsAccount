//package com.discorp.staff.service.impl;
//
//
//import com.discorp.staff.model.*;
//import com.discorp.staff.dao.*;
//import com.discorp.staff.service.StaffService;
//import com.discorp.staff.xml.*;
//import com.discorp.utils.AccountSortByName;
//import com.discorp.utils.Utils;
//
//import java.math.BigInteger;
//import java.net.URLDecoder;
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: tiepnm
// * Date: 9/17/12
// * Time: 9:32 AM
// * To change this template use File | Settings | File Templates.
// */
//public class StaffServiceImpl implements StaffService
//{
//    private FieldDao fieldDAO;
//    private FieldValueDao fieldValueDAO;
//    private CustomerDao customerDAO;
//    private AddressDao addressDAO;
//    private ContactDao contactDAO;
//    private BusinessEntityDao businessEntityDAO;
//    private CommunicationDetailDao communicationDetailDAO;
//    private BlockDao blockDao;
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
//    public BlockDao getBlockDao()
//    {
//        return blockDao;
//    }
//
//    public void setBlockDao(BlockDao blockDao)
//    {
//        this.blockDao = blockDao;
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
//
//    private void getListField(String userLogin, int id, int mandatory, int userId, String memberId, String block_name, List results, List<StaffInfoDTOResponse> listInfo, StaffInfoDTOResponse staffInfoDTO, int mutil, int i)
//    {
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        for (int j = 0; j < results.size(); j++)
//        {
//
//
//            Object[] objResult = (Object[]) results.get(j);
//            String name = objResult[0].toString();
//            String value = objResult[1].toString();
//            Integer group = (Integer) objResult[2];
//            Integer fieldId = (Integer) objResult[4];
//
//
//            String sql = "select field.fieldName,value.fieldValue,value.groupMulti,value.fieldVisible,field.fieldId from Field field,FieldValue value,BiographyBlock block";
//            sql += " where  field.fieldId=value.fieldId and block.blockId=field.blockId";
//            sql += " and block.blockId=" + id + " and value.userId =" + userId;
//            sql += " and field.parentId=" + fieldId + " and value.groupMulti=" + group;
//
//
//            String role = Utils.map.get(userLogin);
//            if (null != role && !role.equals(""))
//            {
//
//
//                if (role.equals(Utils.StaffMember) && !memberId.equals(userLogin))
//                {
//                    sql += " and value.fieldVisible='t'";
//                }
//            }
//            sql += " order by field.position asc";
//
//            List resultsChildren = fieldDAO.getResults(sql);
//
//            StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//            if (resultsChildren.size() > 0)
//            {
//                //Recursive
//                getListField(userLogin, id, mandatory, userId, memberId, block_name, resultsChildren, new ArrayList<StaffInfoDTOResponse>(), infoDTOTemp, group, i + 7);
//            }
//            if (staffInfoDTO.getEnableRemove() == 1)
//            {
//                infoDTOTemp.setFieldName("<font id='field_lv" + group + "&" + fieldId + "' style='margin-left: " + i + "px'>" + name + "</font>");
//            }
//            else
//            {
//                infoDTOTemp.setFieldName("<font style='margin-left: " + i + "px'>" + name + "</font>");
//            }
//
//            infoDTOTemp.setValue(value);
//            infoDTOTemp.setGroup(group);
//            infoDTOTemp.setFieldId(fieldId);
//            listField.add(infoDTOTemp);
//
//        }
//
//        staffInfoDTO.setType(block_name + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(id);
//        staffInfoDTO.setMandatory(mandatory);
//        staffInfoDTO.setGroup(mutil);
//        listInfo.add(staffInfoDTO);
//
//
//    }
//
//    public StaffDTOResponse getStaffDetail(String memberId, int userId, String userLogin, String role)
//    {
//
//
//        List<BiographyBlock> listBiographyBlock = blockDao.findAllBlock();
//        StaffDTOResponse staffDTO = new StaffDTOResponse();
//        staffDTO.setId(userId);
//        List<StaffInfoDTOResponse> listInfo = new ArrayList<StaffInfoDTOResponse>();
//        staffDTO.setListInfor(listInfo);
//
//        boolean isShow = true; //visible or invisible icon edit (HR,owner)
//        boolean isShowIcon = false;//visible or invisible icon show or hide
//        for (BiographyBlock biographyBlock : listBiographyBlock)
//        {
//
//            String sqlBlock = "select field.fieldName,value.fieldValue,value.groupMulti,value.fieldVisible,field.fieldId from Field field,FieldValue value,BiographyBlock biographyBlock";
//            sqlBlock += " where field.fieldId=value.fieldId and biographyBlock.blockId=field.blockId";
//            sqlBlock += " and field.parentId is null and biographyBlock.blockId=" + biographyBlock.getBlockId() + " and value.userId =" + userId;
//
//
//            if (role != null && !role.equals(""))
//            {
//                if (biographyBlock.getMandatory() == 2)
//                {
//                    if (!role.equals(Utils.ROLE.HR.toString()) && !memberId.equals(userLogin))
//                    {
//                        continue;
//                    }
//                }
//                if (!role.equals(Utils.ROLE.HR.toString()) && biographyBlock.getBlockId() == 100)
//                {
//                    continue;
//                }
//                if (role.equals(Utils.StaffMember) && !memberId.equals(userLogin))
//                {
//                    sqlBlock += " and value.fieldVisible='t'";
//                    isShow = false;
//                }
//                if (role.equals(Utils.ROLE.Manager.toString()) && !memberId.equals(userLogin))
//                {
//
//                    isShow = false;
//                }
//                if (memberId.equals(userLogin))
//                {
//                    isShowIcon = true;
//                }
//            }
//            sqlBlock += " order by field.position,value.groupMulti asc";
//            List results = fieldDAO.getResults(sqlBlock);
//
//            //check size of biographyBlock
//            String sqlSize = "select field.fieldName,biographyBlock.blockDescription from Field field,BiographyBlock biographyBlock";
//            sqlSize += " where biographyBlock.blockId=field.blockId and field.blockId=" + biographyBlock.getBlockId();
//            boolean hasItems = false;
//            List resultSize = fieldDAO.getResults(sqlSize);
//            if (biographyBlock.getMandatory() == 1 || resultSize.size() == 1)
//            {
//
//                if (!biographyBlock.getMultiValue())
//                {
//                    hasItems = true;
//                }
//            }
//            for (int j = 0; j < results.size(); j++)
//            {
//
//                Object[] objResult = (Object[]) results.get(j);
//                String name = objResult[0].toString();
//                String value = objResult[1].toString();
//                if (name.equals("Imgpath") && biographyBlock.getBlockId() == 102)
//                {
//                    staffDTO.setImgPath(value);
//                    break;
//                }
//            }
//
//            StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//            if (results.size() > 0)
//            {
//
//                Object[] objResult = (Object[]) results.get(0);
//
//                if (Boolean.valueOf(objResult[3].toString()))
//                {   //display icon visible or invisible
//                    staffInfoDTO.setShow(1);
//                }
//
//            }
//            if (isShow)
//            {
//                staffInfoDTO.setShowIcon(1);
//            }
//            //check biographyBlock has more one childrens
//            if (hasItems)
//            {
//                staffInfoDTO.setHasItem(1);
//            }
//            //check visible  show icon and hide icon
//            if (isShowIcon)
//            {
//                staffInfoDTO.setShowIconHide(1);
//            }
//            if (null != biographyBlock.getMultiValue() && biographyBlock.getMultiValue() == true && isShow)
//            {
//                staffInfoDTO.setEnableRemove(1);
//            }
//
//
//            if (null != biographyBlock.getDisplayKey() && biographyBlock.getDisplayKey().equals(true))
//            {
//                staffInfoDTO.setDisplayKey(1);
//            }
//            else
//            {
//                staffInfoDTO.setDisplayKey(0);
//            }
//            //biographyBlock name
//            if (biographyBlock.getBlockDescription().equals(Utils.name))
//            {
//                setDisplayName(staffDTO, staffInfoDTO, biographyBlock, listInfo, userId);
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.emailAddress))
//            {
//                setDisplayInfomation(staffInfoDTO, biographyBlock, listInfo, userId, Utils.mainMail);
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.phoneNumber))
//            {
//                setDisplayInfomation(staffInfoDTO, biographyBlock, listInfo, userId, Utils.mainPhone);
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.family))
//            {
//                getListFieldFamily(userLogin, biographyBlock.getBlockId(), biographyBlock.getMandatory(), userId, memberId, biographyBlock.getBlockDescription(), results, listInfo, staffInfoDTO, 0, 0);
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.address))
//            {
//
//                if (role.equals(Utils.StaffMember) && !memberId.equals(userLogin))
//                {
//                    setDisplayAddress(staffInfoDTO, biographyBlock, listInfo, userId, false);
//                }
//                else
//                {
//                    setDisplayAddress(staffInfoDTO, biographyBlock, listInfo, userId, true);
//                }
//
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.gender))
//            {
//                setDisplayGender(staffInfoDTO, biographyBlock, listInfo, userId);
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.invationCode))
//            {
//                continue;
//
//            }
//            else if (biographyBlock.getBlockDescription().equals(Utils.imagePath))
//            {
//                continue;
//
//            }
//            else
//            {
//                getListField(userLogin, biographyBlock.getBlockId(), biographyBlock.getMandatory(), userId, memberId, biographyBlock.getBlockDescription(), results, listInfo, staffInfoDTO, 0, 0);
//            }
//
//        }
//
//        return staffDTO;
//    }
//
//    public void setDisplayAddress(StaffInfoDTOResponse staffInfoDTO, BiographyBlock biographyBlock, List<StaffInfoDTOResponse> listInfo, int userId, boolean isShow)
//    {
//
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        List<Customer> listAccount = customerDAO.getCustomerListByUserId(userId);
//
//        if (listAccount.size() > 0)
//        {
//
//
//            Customer customer = listAccount.get(0);
//            Integer business_entity = 0;
//            business_entity = customer.getBusinessEntity();
//            List<BusinessEntityAddress> listBusinessEntityAddress = addressDAO.getBusinessEntityAddressList(business_entity);
//
//            List<Address> listAddress;
//            if (!isShow)
//            {
//                listAddress = addressDAO.getAddressListActiveById(customer.getAddressId());
//            }
//            else
//            {
//                listAddress = addressDAO.getAddressListById(customer.getAddressId());
//            }
//
//            if (listAddress.size() > 0)
//            {
//                Address address = (Address) listAddress.get(0);
//                StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//                infoDTOTemp.setValue(address.getCity());
//                infoDTOTemp.setFieldId(40);
//                infoDTOTemp.setFieldName("City");
//                infoDTOTemp.setGroup(address.getWebId());
//                if (address.getActive() == 1)
//                {
//                    staffInfoDTO.setShow(1);
//
//                }
//                if (null != address.getCity())
//                {
//
//                    listField.add(infoDTOTemp);
//                }
//
//                List<StaffInfoDTOResponse> listInfoChird = new ArrayList<StaffInfoDTOResponse>();
//                StaffInfoDTOResponse infoDTOChild = new StaffInfoDTOResponse();
//                if (null != address.getStreet() && !"".equals(address.getStreet()))
//                {
//
//
//                    infoDTOChild.setValue(address.getStreet());
//                    infoDTOChild.setFieldName("Street");
//                    infoDTOChild.setGroup(0);
//
//                    listInfoChird.add(infoDTOChild);
//                }
//                if (null != address.getState() && !"".equals(address.getState()))
//                {
//                    infoDTOChild = new StaffInfoDTOResponse();
//                    infoDTOChild.setValue(address.getState());
//                    infoDTOChild.setFieldName("State");
//                    infoDTOChild.setGroup(0);
//                    listInfoChird.add(infoDTOChild);
//                }
//                if (null != address.getPostalCode() && !"".equals(address.getPostalCode()))
//                {
//                    infoDTOChild = new StaffInfoDTOResponse();
//                    infoDTOChild.setValue(address.getPostalCode());
//                    infoDTOChild.setFieldName("Zip");
//                    infoDTOChild.setGroup(0);
//                    listInfoChird.add(infoDTOChild);
//
//
//                }
//
//                infoDTOTemp.setList(listInfoChird);
//            }
//
//            for (BusinessEntityAddress businessEntityAddress : listBusinessEntityAddress)
//            {
//                if (!isShow)
//                {
//                    listAddress = addressDAO.getAddressListActiveById(businessEntityAddress.getAddressId());
//                }
//                else
//                {
//                    listAddress = addressDAO.getAddressListById(businessEntityAddress.getAddressId());
//                }
//                if (listAddress.size() > 0)
//                {
//                    Address address = (Address) listAddress.get(0);
//
//                    StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//                    infoDTOTemp.setValue(address.getCity());
//                    infoDTOTemp.setFieldId(40);
//                    infoDTOTemp.setFieldName("City");
//                    infoDTOTemp.setGroup(businessEntityAddress.getAddressId());
//                    if (null != address.getCity())
//                    {
//
//                        listField.add(infoDTOTemp);
//                    }
//
//                    List<StaffInfoDTOResponse> listInfoChird = new ArrayList<StaffInfoDTOResponse>();
//                    StaffInfoDTOResponse infoDTOChild = new StaffInfoDTOResponse();
//                    if (null != address.getStreet() && !"".equals(address.getStreet()))
//                    {
//
//
//                        infoDTOChild.setValue(address.getStreet());
//                        infoDTOChild.setFieldName("Street");
//                        infoDTOChild.setGroup(0);
//
//                        listInfoChird.add(infoDTOChild);
//                    }
//                    if (null != address.getState() && !"".equals(address.getState()))
//                    {
//                        infoDTOChild = new StaffInfoDTOResponse();
//                        infoDTOChild.setValue(address.getState());
//                        infoDTOChild.setFieldName("State");
//                        infoDTOChild.setGroup(0);
//                        listInfoChird.add(infoDTOChild);
//                    }
//                    if (null != address.getPostalCode() && !"".equals(address.getPostalCode()))
//                    {
//                        infoDTOChild = new StaffInfoDTOResponse();
//                        infoDTOChild.setValue(address.getPostalCode());
//                        infoDTOChild.setFieldName("Zip");
//                        infoDTOChild.setGroup(0);
//                        listInfoChird.add(infoDTOChild);
//
//
//                    }
//
//                    infoDTOTemp.setList(listInfoChird);
//                }
//            }
//
//        }
//
//        staffInfoDTO.setType(biographyBlock.getBlockDescription() + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(biographyBlock.getBlockId());
//        staffInfoDTO.setMandatory(biographyBlock.getMandatory());
//        staffInfoDTO.setGroup(0);
//        listInfo.add(staffInfoDTO);
//    }
//
//    private void getListFieldFamily(String userLogin, int id, int mandatory, int userId, String memberId, String block_name, List results, List<StaffInfoDTOResponse> listInfo, StaffInfoDTOResponse staffInfoDTO, int mutil, int i)
//    {
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        for (int j = 0; j < results.size(); j++)
//        {
//
//
//            Object[] objResult = (Object[]) results.get(j);
//            String name = objResult[0].toString();
//            String value = objResult[1].toString();
//            Integer group = (Integer) objResult[2];
//
//            Integer fieldId = (Integer) objResult[4];
//
//
//            String sql = "select field.fieldName,value.fieldValue,value.groupMulti,value.fieldVisible,field.fieldId from Field field,FieldValue value,BiographyBlock block";
//            sql += " where  field.fieldId=value.fieldId and block.blockId=field.blockId";
//            sql += " and block.blockId=" + id + " and value.userId =" + userId;
//            sql += " and field.parentId=" + fieldId + " and value.groupMulti=" + group;
//            StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//            String role = Utils.map.get(userLogin);
//
//            // String role = Utils.map.get(userLogin);
//            if (null != role && !role.equals(""))
//            {
//
//
//                if (role.equals(Utils.StaffMember) && !memberId.equals(userLogin))
//                {
//                    sql += " and value.fieldVisible='t'";
//                }
//            }
//            sql += " order by field.position asc";
//
//            List resultsChildren = fieldDAO.getResults(sql);
//            if (resultsChildren.size() > 0)
//            {
//                getListFieldFamily(userLogin, id, mandatory, userId, memberId, block_name, resultsChildren, new ArrayList<StaffInfoDTOResponse>(), infoDTOTemp, group, i + 7);
//            }
//            if (staffInfoDTO.getEnableRemove() == 1)
//            {
//                infoDTOTemp.setFieldName("<font id='field_lv" + group + "&" + fieldId + "' style='margin-left: " + i + "px'>" + name + "</font>");
//            }
//            else
//            {
//                infoDTOTemp.setFieldName("<font style='margin-left: " + i + "px'>" + name + "</font>");
//            }
//
//            if (!name.equals("Birth date") && !name.equals("Married"))
//            {
//
//                infoDTOTemp.setHasItem(1);
//            }
//
//            if (name.equals("Relationship"))
//            {
//                String sql2 = "select field.fieldName,value.fieldValue,value.groupMulti,value.fieldVisible,field.fieldId from Field field,FieldValue value,BiographyBlock block";
//                sql2 += " where  field.fieldId=value.fieldId and block.blockId=field.blockId";
//                sql2 += " and block.blockId=" + id + " and value.userId =" + userId;
//                sql2 += " and field.parentId=" + fieldId + " and value.groupMulti=" + group;
//
//
//                if (null != role && !role.equals(""))
//                {
//
//
//                    if (role.equals(Utils.StaffMember) && !memberId.equals(userLogin))
//                    {
//                        sql2 += " and value.fieldVisible='t'";
//                    }
//                }
//                sql2 += " order by field.position asc";
//
//                List resultsChildrenLevel2 = fieldDAO.getResults(sql2);
//                value += "-";
//                String preFix = "";
//                String suffix = "";
//                String firstName = "";
//                String midName = "";
//                String lastName = "";
//
//                for (int k = 0; k < resultsChildrenLevel2.size(); k++)
//                {
//                    Object[] objResult2 = (Object[]) resultsChildrenLevel2.get(k);
//                    String name2 = objResult2[0].toString();
//
//
//                    if (!name2.equals("Birth date") && !name2.equals("Married"))
//                    {
//                        if ("Prefix".equals(name2))
//                        {
//                            preFix = objResult2[1].toString();
//                        }
//                        else if ("Suffix".equals(name2))
//                        {
//                            suffix = objResult2[1].toString();
//                        }
//                        else if ("First".equals(name2))
//                        {
//                            firstName = objResult2[1].toString();
//                        }
//                        else if ("Middle".equals(name2))
//                        {
//                            midName = objResult2[1].toString();
//                        }
//                        else if ("Last".equals(name2))
//                        {
//                            lastName = objResult2[1].toString();
//                        }
//
//                    }
//
//
//                }
//                if (preFix.equals("") && suffix.equals("") && firstName.equals("") && midName.equals("") && lastName.equals(""))
//                {
//                    value = "";
//                }
//                if (preFix.equals(""))
//                {
//                    value += firstName + " " + midName + " " + lastName + " " + suffix;
//                }
//                else
//                {
//                    value += preFix + " " + firstName + " " + midName + " " + lastName + " " + suffix;
//                }
//
//
//            }
//
//            if (name.equals("Birth date") || name.equals("Married") || name.equals("Relationship"))
//            {
//
//                infoDTOTemp.setValue(value);
//                infoDTOTemp.setGroup(group);
//                infoDTOTemp.setFieldId(fieldId);
//                listField.add(infoDTOTemp);
//            }
//
//
//        }
//        staffInfoDTO.setType(block_name + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(id);
//        staffInfoDTO.setMandatory(mandatory);
//        staffInfoDTO.setGroup(mutil);
//        listInfo.add(staffInfoDTO);
//
//
//    }
//
//    public void setDisplayGender(StaffInfoDTOResponse staffInfoDTO, BiographyBlock biographyBlock, List<StaffInfoDTOResponse> listInfo, int userId)
//    {
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        List listAccount = customerDAO.getCustomerContactListByUserId(userId);
//
//        if (listAccount.size() > 0)
//        {
//            Object[] objResults = (Object[]) listAccount.get(0);
//            Contact contact = (Contact) objResults[1];
//            StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//            infoDTOTemp.setFieldName("Gender");
//            infoDTOTemp.setGroup(0);
//            if ("M".equals(contact.getGender()))
//            {
//                infoDTOTemp.setValue("Male");
//            }
//            else if ("F".equals(contact.getGender()))
//            {
//                infoDTOTemp.setValue("Female");
//            }
//            if (!"".equals(contact.getGender()) && null != contact.getGender())
//            {
//                listField.add(infoDTOTemp);
//            }
//        }
//
//        staffInfoDTO.setType(biographyBlock.getBlockDescription() + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(biographyBlock.getBlockId());
//        staffInfoDTO.setMandatory(biographyBlock.getMandatory());
//        staffInfoDTO.setGroup(0);
//        listInfo.add(staffInfoDTO);
//    }
//
//    public void setDisplayInfomation(StaffInfoDTOResponse staffInfoDTO, BiographyBlock biographyBlock, List<StaffInfoDTOResponse> listInfo, int userId, String name)
//    {
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        List listAccount = customerDAO.getCustomerContactListByUserId(userId);
//
//        if (listAccount.size() > 0)
//        {
//
//            Object[] objects = (Object[]) listAccount.get(0);
//            Contact contact = (Contact) objects[1];
//            String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
//            sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
//            sql += " and address.webId=" + contact.getAddressId() + " and communicationMethod.description like '" + name + "'";
//            sql += " and communicationDetail.main = true '";
//
//            List listInformation = fieldDAO.getResults(sql);
//            for (int i = 0; i < listInformation.size(); i++)
//            {
//                Object[] ObjectInformation = (Object[]) listInformation.get(i);
//                CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInformation[1];
//
//                StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//                infoDTOTemp.setValue(communicationDetail.getInformation());
//                infoDTOTemp.setFieldName(name);
//                infoDTOTemp.setGroup(0);
//                listField.add(infoDTOTemp);
//
//            }
//        }
//
//        staffInfoDTO.setType(biographyBlock.getBlockDescription() + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(biographyBlock.getBlockId());
//        staffInfoDTO.setMandatory(biographyBlock.getMandatory());
//        staffInfoDTO.setGroup(0);
//        listInfo.add(staffInfoDTO);
//    }
//
//    public void setDisplayName(StaffDTOResponse staffDTO, StaffInfoDTOResponse staffInfoDTO, BiographyBlock biographyBlock, List<StaffInfoDTOResponse> listInfo, int userId)
//    {
//        List<StaffInfoDTOResponse> listField = new ArrayList<StaffInfoDTOResponse>();
//        List listAccount = customerDAO.getCustomerContactListByUserId(userId);
//
//        if (listAccount.size() > 0)
//        {
//            Object[] objResults = (Object[]) listAccount.get(0);
//            Contact contact = (Contact) objResults[1];
//            StaffInfoDTOResponse infoDTOTemp = new StaffInfoDTOResponse();
//
//            infoDTOTemp.setFieldName("Title");
//            infoDTOTemp.setGroup(0);
//            infoDTOTemp.setValue(contact.getTitle());
//            if (!"".equals(contact.getTitle()))
//            {
//                listField.add(infoDTOTemp);
//            }
//
//            infoDTOTemp = new StaffInfoDTOResponse();
//            infoDTOTemp.setFieldName("First");
//            infoDTOTemp.setGroup(0);
//            infoDTOTemp.setValue(contact.getFirstName());
//            if (!"".equals(contact.getFirstName()))
//            {
//                listField.add(infoDTOTemp);
//                staffDTO.setDisplayName(contact.getFirstName());
//            }
//
//            infoDTOTemp = new StaffInfoDTOResponse();
//            infoDTOTemp.setFieldName("Middle");
//            infoDTOTemp.setGroup(0);
//            infoDTOTemp.setValue(contact.getMiddleName());
//            if (!"".equals(contact.getInternalId()))
//            {
//                listField.add(infoDTOTemp);
//            }
//
//            infoDTOTemp = new StaffInfoDTOResponse();
//            infoDTOTemp.setFieldName("Last");
//            infoDTOTemp.setGroup(0);
//            infoDTOTemp.setValue(contact.getLastName());
//            if (!"".equals(contact.getLastName()))
//            {
//                listField.add(infoDTOTemp);
//                staffDTO.setDisplayName(staffDTO.getDisplayName() + " " + contact.getLastName());
//            }
//
//            infoDTOTemp = new StaffInfoDTOResponse();
//            infoDTOTemp.setFieldName("Suffix");
//            infoDTOTemp.setGroup(0);
//            infoDTOTemp.setValue(contact.getSuffix());
//            if (!"".equals(contact.getSuffix()))
//            {
//                listField.add(infoDTOTemp);
//            }
//        }
//
//
//        staffInfoDTO.setType(biographyBlock.getBlockDescription() + "");
//        staffInfoDTO.setList(listField);
//        staffInfoDTO.setBlockId(biographyBlock.getBlockId());
//        staffInfoDTO.setMandatory(biographyBlock.getMandatory());
//        staffInfoDTO.setGroup(0);
//        listInfo.add(staffInfoDTO);
//    }
//
//    public StaffDTOResponse getListBlockField(StaffBlockRequest staffBlockRequest)
//    {
//
//        String sql = "";
//        int mandatory = 0;
//        int blockId = staffBlockRequest.getBlockId();
//        int userId = staffBlockRequest.getUserId();
//        List<BiographyBlock> listBiographyBlock = blockDao.getListBlock(blockId);
//        if (listBiographyBlock.size() > 0)
//        {
//            mandatory = listBiographyBlock.get(0).getMandatory();
//        }
//        List results = fieldDAO.getListField(blockId);
//
//        List<Integer> listGroup = new ArrayList<Integer>();
//        HashMap<Integer, List<StaffInfoDTOResponse>> map = new HashMap<Integer, List<StaffInfoDTOResponse>>();
//        listGroup.add(0);
//        for (int j = 0; j < results.size(); j++)
//        {
//            Object[] objResult = (Object[]) results.get(j);
//            Integer fieldId = (Integer) objResult[0];
//            List<Integer> resultsValue = fieldValueDAO.getGroupListByUserId(fieldId, userId);
//            if (resultsValue.size() > 0)
//            {
//                for (Integer temp : resultsValue)
//                {
//                    if (!listGroup.contains(temp))
//                    {
//
//                        listGroup.add(temp);
//                    }
//
//                }
//            }
//
//        }
//        List listAccount = new ArrayList();
//        if (blockId == 0 || blockId == 1 || blockId == 2 || blockId == 20 || blockId == 14 || blockId == 99)
//        {
//            listAccount = contactDAO.getContactListByUserId(userId);
//        }
//        if (blockId == 14)
//        {
//            List<StaffInfoDTOResponse> listValue = new ArrayList<StaffInfoDTOResponse>();
//            if (listAccount.size() > 0)
//            {
//                Object[] objects = (Object[]) listAccount.get(0);
//                Customer customer = (Customer) objects[0];
//
//                List<Address> listAddressMain = addressDAO.getAddressListById(customer.getAddressId());
//                if (listAddressMain.size() > 0)
//                {
//                    Address address = (Address) listAddressMain.get(0);
//                    for (int j = 0; j < results.size(); j++)
//                    {
//                        Object[] objResult = (Object[]) results.get(j);
//                        Integer fieldId = (Integer) objResult[0];
//                        String fieldName = objResult[1].toString();
//                        String edit_type = objResult[2].toString();
//                        StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//                        staffInfoDTO.setFieldId(fieldId);
//                        staffInfoDTO.setFieldName(fieldName);
//                        staffInfoDTO.setType(edit_type);
//                        staffInfoDTO.setMandatory(mandatory);
//                        staffInfoDTO.setGroup(address.getWebId());
//                        if (fieldName.equals("City"))
//                        {
//                            staffInfoDTO.setValue(address.getCity());
//                        }
//                        else if (fieldName.equals("Street"))
//                        {
//                            staffInfoDTO.setValue(address.getStreet());
//                        }
//                        if (fieldName.equals("Zip"))
//                        {
//                            staffInfoDTO.setValue(address.getPostalCode());
//                        }
//                        if (fieldName.equals("State"))
//                        {
//                            staffInfoDTO.setValue(address.getState());
//                        }
//                        if (address.getActive() == 1)
//                        {
//                            staffInfoDTO.setVisible(true);
//                        }
//                        listValue.add(staffInfoDTO);
//                    }
//
//                    map.put(0, listValue);
//                }
//                //
//                List listBussinessAddress = businessEntityDAO.getBusinessEntityListByUserId(userId);
//                for (int k = 0; k < listBussinessAddress.size(); k++)
//                {
//
//                    BusinessEntityAddress businessEntityAddress = (BusinessEntityAddress) listBussinessAddress.get(k);
//                    List<Address> listAddress = addressDAO.getAddressListById(businessEntityAddress.getAddressId());
//                    if (listAddress.size() > 0)
//                    {
//                        Address address = (Address) listAddress.get(0);
//                        for (int j = 0; j < results.size(); j++)
//                        {
//                            Object[] objResult = (Object[]) results.get(j);
//                            Integer fieldId = (Integer) objResult[0];
//                            String fieldName = objResult[1].toString();
//                            String edit_type = objResult[2].toString();
//                            StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//                            staffInfoDTO.setFieldId(fieldId);
//                            staffInfoDTO.setFieldName(fieldName);
//                            staffInfoDTO.setType(edit_type);
//                            staffInfoDTO.setMandatory(mandatory);
//                            staffInfoDTO.setGroup(businessEntityAddress.getAddressId());
//                            if (fieldName.equals("City"))
//                            {
//                                staffInfoDTO.setValue(address.getCity());
//                            }
//                            else if (fieldName.equals("Street"))
//                            {
//                                staffInfoDTO.setValue(address.getStreet());
//                            }
//                            if (fieldName.equals("Zip"))
//                            {
//                                staffInfoDTO.setValue(address.getPostalCode());
//                            }
//                            if (fieldName.equals("State"))
//                            {
//                                staffInfoDTO.setValue(address.getState());
//                            }
//                            if (null != address.getCity())
//                            {
//                                listValue.add(staffInfoDTO);
//                            }
//                        }
//
//                        map.put(1, listValue);
//                    }
//
//
//                }
//            }
//            else
//            {
//                for (int j = 0; j < results.size(); j++)
//                {
//                    Object[] objResult = (Object[]) results.get(j);
//                    Integer fieldId = (Integer) objResult[0];
//                    String fieldName = objResult[1].toString();
//                    String edit_type = objResult[2].toString();
//                    StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//                    staffInfoDTO.setFieldId(fieldId);
//                    staffInfoDTO.setFieldName(fieldName);
//                    staffInfoDTO.setType(edit_type);
//                    staffInfoDTO.setMandatory(mandatory);
//                    staffInfoDTO.setGroup(-1);
//                    if (fieldName.equals("City"))
//                    {
//                        staffInfoDTO.setValue("");
//                    }
//                    else if (fieldName.equals("Street"))
//                    {
//                        staffInfoDTO.setValue("");
//                    }
//                    if (fieldName.equals("Zip"))
//                    {
//                        staffInfoDTO.setValue("");
//                    }
//                    if (fieldName.equals("State"))
//                    {
//                        staffInfoDTO.setValue("");
//                    }
//                    listValue.add(staffInfoDTO);
//                }
//                map.put(0, listValue);
//
//            }
//
//
//        }
//        if (blockId != 14)
//        {
//            for (Integer i : listGroup)
//            {
//
//                List<StaffInfoDTOResponse> listValue = new ArrayList<StaffInfoDTOResponse>();
//                for (int j = 0; j < results.size(); j++)
//                {
//                    Object[] objResult = (Object[]) results.get(j);
//                    Integer fieldId = (Integer) objResult[0];
//                    String fieldName = objResult[1].toString();
//                    String edit_type = objResult[2].toString();
//
//                    sql = "select value.fieldValue,value.groupMulti,value.fieldVisible from FieldValue value where value.fieldId=" + fieldId;
//                    sql += " and value.userId=" + userId + " and value.groupMulti=" + i;
//                    List resultsValue = fieldDAO.getResults(sql);
//                    StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//                    staffInfoDTO.setFieldId(fieldId);
//                    staffInfoDTO.setFieldName(fieldName);
//                    staffInfoDTO.setType(edit_type);
//                    staffInfoDTO.setMandatory(mandatory);
//                    if (blockId == 0 || blockId == 1 || blockId == 2 || blockId == 20 || blockId == 99)
//                    {
//
//                        if (listAccount.size() > 0)
//                        {
//                            Object[] objects = (Object[]) listAccount.get(0);
//                            Contact contact = (Contact) objects[1];
//                            if (blockId == 0 || blockId == 20 || blockId == 99)
//                            {
//                                if (fieldName.equals("Title"))
//                                {
//                                    staffInfoDTO.setValue(contact.getTitle());
//                                }
//                                else if (fieldName.equals("First"))
//                                {
//                                    staffInfoDTO.setValue(contact.getFirstName());
//                                }
//                                else if (fieldName.equals("Middle"))
//                                {
//                                    staffInfoDTO.setValue(contact.getMiddleName());
//                                }
//                                else if (fieldName.equals("Last"))
//                                {
//                                    staffInfoDTO.setValue(contact.getLastName());
//                                }
//                                else if (fieldName.equals("Suffix"))
//                                {
//                                    staffInfoDTO.setValue(contact.getSuffix());
//                                }
//                                else if (fieldName.equals("Sex"))
//                                {
//                                    staffInfoDTO.setValue(contact.getGender());
//                                }
//
//                            }
//                            else if (blockId == 1 || blockId == 2)
//                            {
//                                String name = "";
//                                if (blockId == 1)
//                                {
//                                    name = Utils.mainMail;
//                                }
//                                if (blockId == 2)
//                                {
//                                    name = Utils.mainPhone;
//                                }
//                                String sqlInformation = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
//                                sqlInformation += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
//                                sqlInformation += " and address.webId=" + contact.getAddressId();
//                                sqlInformation += " and communicationMethod.description like '" + name + "'";
//                                sqlInformation += " and communicationDetail.main = true '";
//
//                                List listInformation = fieldDAO.getResults(sqlInformation);
//                                if (listInformation.size() > 0)
//                                {
//                                    Object[] objectInformations = (Object[]) listInformation.get(0);
//                                    CommunicationDetail detail = (CommunicationDetail) objectInformations[1];
//                                    staffInfoDTO.setValue(detail.getInformation());
//                                }
//
//                            }
//
//                        }
//                        listValue.add(staffInfoDTO);
//
//                    }
//                    else if (resultsValue.size() > 0)
//                    {
//
//                        Object[] objResultValue = (Object[]) resultsValue.get(0);
//                        String fieldValue = objResultValue[0].toString();
//                        Integer group = (Integer) objResultValue[1];
//                        Boolean visible = (Boolean) objResultValue[2];
//                        staffInfoDTO.setValue(fieldValue);
//                        staffInfoDTO.setGroup(group);
//                        staffInfoDTO.setVisible(visible);
//                        listValue.add(staffInfoDTO);
//                    }
//                    else
//                    {
//                        staffInfoDTO.setValue("");
//                        staffInfoDTO.setGroup(i);
//                        listValue.add(staffInfoDTO);
//                    }
//
//                }
//                map.put(i, listValue);
//            }
//        }
//
//
//        StaffDTOResponse staffDTOResponse = new StaffDTOResponse();
//        List<StaffInfoDTOResponse> listAdd = new ArrayList<StaffInfoDTOResponse>();
//        staffDTOResponse.setListInfor(listAdd);
//
//        if (map.size() > 0)
//        {
//
//            for (Integer key : map.keySet())
//            {
//                List<StaffInfoDTOResponse> listMap = map.get(key);
//                if (null != listMap)
//                {
//                    for (StaffInfoDTOResponse dto : listMap)
//                    {
//                        listAdd.add(dto);
//                    }
//                }
//            }
//
//        }
//        return staffDTOResponse;
//    }
//
//    public StaffDTOResponse saveListField(StaffDetailAddRequest staffDetailAddRequest)
//    {
//
//        StaffDTOResponse staffDTOResponse = new StaffDTOResponse();
//        List<StaffDetailAddRequest.StaffField> list = staffDetailAddRequest.getListStaffField();
//        int userId = staffDetailAddRequest.getUserId();
//        int dealerId = Integer.parseInt(staffDetailAddRequest.getDealerId());
//        if (userId == 0)
//        {
//
//            Integer webId = 0;
//            List<Address> listAddress = addressDAO.findAllAddress();
//            if (listAddress.size() > 0)
//            {
//                webId = listAddress.get(0).getWebId();
//            }
//            Address address = new Address();
//            address.setWebId(webId + 1);
//            address.setVersion(1);
//            address.setAddressType(1);
//            address.setActive(0);
//            address.setDealerId(dealerId);
//            addressDAO.saveAddress(address);
//
//            webId = 0;
//            List<BusinessEntity> listBusinessEntity = businessEntityDAO.findAllBusinessEntity();
//            if (listBusinessEntity.size() > 0)
//            {
//                webId = listBusinessEntity.get(0).getWebId();
//            }
//
//            BusinessEntity businessEntity = new BusinessEntity();
//            businessEntity.setWebId(webId + 1);
//            businessEntity.setDealerId(dealerId);
//            businessEntityDAO.saveBusinessEntity(businessEntity);
//
//            webId = 0;
//            List<Customer> listCustomer = customerDAO.findAllCustomer();
//            if (listCustomer.size() > 0)
//            {
//                webId = listCustomer.get(0).getWebId();
//            }
//            Customer customer = new Customer();
//            customer.setWebId(webId + 1);
//            customer.setRoleId(35);
//            customer.setBusinessEntity(businessEntity.getWebId());
//            customer.setAddressId(address.getWebId());
//            customer.setUid("none");
//            customer.setDealerId(dealerId);
//            customerDAO.saveCustomer(customer);
//
//            webId = 0;
//            List<Contact> listContact = contactDAO.findAllContact();
//            if (listContact.size() > 0)
//            {
//                webId = listContact.get(0).getWebId();
//            }
//
//            Contact contact = new Contact();
//            contact.setWebId(webId + 1);
//            contact.setAddressId(address.getWebId());
//            contact.setDealerId(dealerId);
//            contactDAO.saveContact(contact);
//
//            userId = customer.getWebId();
//            staffDTOResponse.setId(customer.getWebId());
//        }
//        else
//        {
//            staffDTOResponse.setId(userId);
//        }
//        List<Address> listAddressAnother = new ArrayList<Address>();
//        Object[] objResults = null;
//        List<Integer> listAddressId = new ArrayList<Integer>();
//        Address addressAnother = null;
//        for (StaffDetailAddRequest.StaffField staffField : list)
//        {
//            int fieldId = staffField.getFieldId();
//            String value = staffField.getValue();
//            String visible = staffField.getVisible();
//            int group = staffField.getGroup();
//            boolean visibleField = true;
//            //reports To
//            if (fieldId == 15 || fieldId == 55)
//            {
//
//                if (null != value && !"".equals(value))
//                {
//                    List listAccount = contactDAO.getContactListByUserId(Integer.parseInt(value));
//                    if (listAccount.size() > 0)
//                    {
//                        Object[] objects = (Object[]) listAccount.get(0);
//                        Contact contact = (Contact) objects[1];
//                        value = contact.getFirstName() + ", " + contact.getLastName();
//                    }
//                }
//
//            }
//            if (visible.equals("FALSE"))
//            {
//                visibleField = false;
//            }
//            //
//            Field field = fieldDAO.getField(fieldId);
//            value = value.replaceAll("%20", " ");
//            if (field.getBlockId() == 0 || field.getBlockId() == 1 || field.getBlockId() == 2 || field.getBlockId() == 14 || field.getBlockId() == 20)
//            {
//
//                List listAccount = contactDAO.getContactListByUserId(userId);
//                if (listAccount.size() > 0)
//                {
//                    objResults = (Object[]) listAccount.get(0);
//                    Contact contact = (Contact) objResults[1];
//                    //name and gender
//                    if (field.getBlockId() == 0 || field.getBlockId() == 20)
//                    {
//
//                        if (field.getFieldName().equals("Title"))
//                        {
//                            contact.setTitle(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        else if (field.getFieldName().equals("First"))
//                        {
//                            if (null != value && !"".equals(value))
//                            {
//                                value = value.replaceFirst(String.valueOf(value.charAt(0)), String.valueOf(Character.toUpperCase(value.charAt(0))));
//                            }
//                            contact.setFirstName(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        else if (field.getFieldName().equals("Middle"))
//                        {
//                            contact.setMiddleName(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        else if (field.getFieldName().equals("Last"))
//                        {
//                            contact.setLastName(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        else if (field.getFieldName().equals("Suffix"))
//                        {
//                            contact.setSuffix(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        else if (field.getFieldName().equals("Sex"))
//                        {
//                            contact.setGender(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        }
//                        contactDAO.updateContact(contact);
//                    }
//                    //Phone and mail
//                    else if (field.getBlockId() == 1 || field.getBlockId() == 2)
//                    {
//
//                        String name = "";
//                        if (field.getBlockId() == 1)
//                        {
//                            name = Utils.mainMail;
//                        }
//                        if (field.getBlockId() == 2)
//                        {
//                            name = Utils.mainPhone;
//                        }
//                        String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
//                        sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
//                        sql += " and address.webId=" + contact.getAddressId();
//                        sql += " and communicationMethod.description like '" + name + "'";
//
//                        List listInfomation = fieldDAO.getResults(sql);
//                        if (listInfomation.size() > 0)
//                        {
//
//                            Object[] objects = (Object[]) listInfomation.get(0);
//                            CommunicationDetail detail = (CommunicationDetail) objects[1];
//                            detail.setInformation(value);
//                            communicationDetailDAO.updateCommunicationDetail(detail);
//                        }
//                        else
//                        {
//                            CommunicationDetail detail = communicationDetailDAO.findAllCommunicationDetail().get(0);
//                            CommunicationDetail communicationDetail = new CommunicationDetail();
//                            communicationDetail.setAddressId(contact.getAddressId());
//                            communicationDetail.setWebId(detail.getWebId() + 1);
//                            communicationDetail.setInformation(value);
//                            communicationDetail.setMain(true);
//                            if (field.getBlockId() == 1)
//                            {
//                                //set id for mail
//                                communicationDetail.setCommunicationMethodId(3);
//                            }
//                            else if (field.getBlockId() == 2)
//                            {
//                                //set id for phone
//                                communicationDetail.setCommunicationMethodId(1);
//                            }
//                            communicationDetail.setDealerId(dealerId);
//                            communicationDetailDAO.saveCommunicationDetail(communicationDetail);
//                        }
//                    }
//                    //Address
//                    else if (field.getBlockId() == 14)
//                    {
//                        if (group == 0 || group == -1)
//                        {
//                            List<Address> listAddress = addressDAO.getAddressListById(contact.getAddressId());
//                            if (listAddress.size() > 0)
//                            {
//                                Address address = (Address) listAddress.get(0);
//                                if (field.getFieldName().equals("City"))
//                                {
//                                    address.setCity(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                else if (field.getFieldName().equals("Street"))
//                                {
//                                    address.setStreet(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("Zip"))
//                                {
//                                    address.setPostalCode(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("State"))
//                                {
//                                    address.setState(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (!visibleField)
//                                {
//                                    address.setActive(0);
//                                }
//                                else
//                                {
//                                    address.setActive(1);
//                                }
//                                addressDAO.updateAddress(address);
//                            }
//
//                        }
//                        else
//                        {
//                            List<Address> listAddress = addressDAO.getAddressListById(group);
//                            if (listAddress.size() > 0)
//                            {
//                                Address address = (Address) listAddress.get(0);
//                                if (field.getFieldName().equals("City"))
//                                {
//                                    address.setCity(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                else if (field.getFieldName().equals("Street"))
//                                {
//                                    address.setStreet(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("Zip"))
//                                {
//                                    address.setPostalCode(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("State"))
//                                {
//                                    address.setState(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (!visibleField)
//                                {
//                                    address.setActive(0);
//                                }
//                                else
//                                {
//                                    address.setActive(1);
//                                }
//
//
//                                addressDAO.updateAddress(address);
//                            }
//                            else
//                            {
//                                if (!listAddressId.contains(group))
//                                {
//
//                                    addressAnother = new Address();
//                                    addressAnother.setWebId(group);
//                                    addressAnother.setVersion(1);
//                                    addressAnother.setAddressType(1);
//                                    addressAnother.setActive(1);
//                                    if (!visibleField)
//                                    {
//                                        addressAnother.setActive(0);
//                                    }
//                                    listAddressId.add(group);
//                                    listAddressAnother.add(addressAnother);
//                                }
//                                for (Address address : listAddressAnother)
//                                {
//                                    if (address.getWebId() == group)
//                                    {
//                                        addressAnother = address;
//                                    }
//                                }
//                                if (field.getFieldName().equals("City"))
//                                {
//                                    addressAnother.setCity(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                else if (field.getFieldName().equals("Street"))
//                                {
//                                    addressAnother.setStreet(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("Zip"))
//                                {
//                                    addressAnother.setPostalCode(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                if (field.getFieldName().equals("State"))
//                                {
//                                    addressAnother.setState(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                                }
//                                addressAnother.setDealerId(dealerId);
//
//
//                            }
//
//                        }
//
//
//                    }
//                }
//
//            }
//            else
//            {
//                List results = fieldValueDAO.getIdListByUserIdAndGroup(fieldId, userId, group);
//                if (results.size() > 0)
//                {
//                    Integer fieldValueId = (Integer) results.get(0);
//                    FieldValue fieldValue = fieldValueDAO.getFieldValue(fieldValueId);
//                    fieldValue.setFieldValue(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                    fieldValue.setFieldVisible(visibleField);
//                    fieldValueDAO.updateFieldValue(fieldValue);
//                }
//                else
//                {
//                    if (!value.equals(""))
//                    {
//
//                        /**
//                         *  check field parent
//                         *  if it is child of another field then will  field parent has value=blank
//                         */
//                        Field child = fieldDAO.getField(fieldId);
//                        if (child.getParentId() != null)
//                        {
//
//                            List resultsExist = fieldValueDAO.getFieldIdListByUserIdAndGroup(child.getParentId(), userId, group);
//                            if (resultsExist.size() == 0)
//                            {
//                                FieldValue fieldValueParent = new FieldValue();
//                                fieldValueParent.setUserId(userId);
//                                fieldValueParent.setFieldId(child.getParentId());
//                                fieldValueParent.setFieldValue("");
//                                fieldValueParent.setFieldVisible(visibleField);
//                                fieldValueParent.setGroupMulti(group);
//                                fieldValueDAO.saveFieldValue(fieldValueParent);
//
//                            }
//                        }
//
//                        FieldValue fieldValue = new FieldValue();
//                        fieldValue.setUserId(userId);
//                        fieldValue.setFieldId(fieldId);
//                        fieldValue.setFieldValue(URLDecoder.decode(value.replaceAll("%", "&#037")));
//                        fieldValue.setFieldVisible(visibleField);
//                        fieldValue.setGroupMulti(group);
//                        fieldValueDAO.saveFieldValue(fieldValue);
//                    }
//
//                }
//            }
//
//        }
//        for (Address address : listAddressAnother)
//        {
//            Integer webId = 0;
//            List<Address> listAddress = addressDAO.findAllAddress();
//            if (listAddress.size() > 0)
//            {
//                webId = listAddress.get(0).getWebId();
//            }
//            address.setWebId(webId + 1);
//            addressDAO.saveAddress(address);
//
//            BusinessEntityAddress businessEntityAddress = new BusinessEntityAddress();
//            businessEntityAddress.setAddressId(address.getWebId());
//            Customer customer = (Customer) objResults[0];
//            businessEntityAddress.setBusinessEntityId(customer.getBusinessEntity());
//
//            addressDAO.saveBusinessEntityAddress(businessEntityAddress);
//        }
//        return staffDTOResponse;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public StaffDTOResponse getRole(String memberId)
//    {
//        StaffDTOResponse staffDTOResponse = new StaffDTOResponse();
//        if (customerDAO.getCustomerListByMemberId(memberId).size() == 0)
//        {
//            staffDTOResponse.setRole("0");
//            return staffDTOResponse;
//        }
//
//        List list = fieldValueDAO.getListFieldValueByMemberId(memberId);
//
//        if (list.size() > 0)
//        {
//            FieldValue fieldValue = (FieldValue) list.get(0);
//            staffDTOResponse.setRole(fieldValue.getFieldValue());
//        }
//        else
//        {
//            staffDTOResponse.setRole(Utils.StaffMember);
//        }
//
//
//        return staffDTOResponse;
//    }
//
//    public StaffDTOResponse getInformationAccount(int userId)
//    {
//
//
//        List listAccount = customerDAO.getCustomerContactListByUserId(userId);
//
//        String email = null;
//        String firstName = null;
//        String lastName = null;
//        if (listAccount.size() > 0)
//        {
//            Object[] objects = (Object[]) listAccount.get(0);
//            Contact contact = (Contact) objects[1];
//            firstName = contact.getFirstName();
//            lastName = contact.getLastName();
//            String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
//            sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
//            sql += " and address.webId=" + contact.getAddressId();
//            List listInformation = fieldDAO.getResults(sql);
//            for (int i = 0; i < listInformation.size(); i++)
//            {
//                Object[] ObjectInformation = (Object[]) listInformation.get(i);
//                CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInformation[1];
//                CommunicationMethod communicationMethod = (CommunicationMethod) ObjectInformation[2];
//                if (communicationMethod.getDescription().equals(Utils.mainMail))
//                {
//                    email = communicationDetail.getInformation();
//                    break;
//                }
//            }
//        }
//
//
//        StaffDTOResponse staffDTOResponse = new StaffDTOResponse();
//        staffDTOResponse.setEmail(email);
//        staffDTOResponse.setFirstName(firstName);
//        staffDTOResponse.setLastName(lastName);
//        return staffDTOResponse;
//    }
//
//    public BlockResponse isAddAnother(int blockId)
//    {
//        BlockResponse blockResponse = new BlockResponse();
//        List<BiographyBlock> results = blockDao.getListBlock(blockId);
//        if (results.size() > 0)
//        {
//
//            if (null != results.get(0).getMultiValue() && results.get(0).getMultiValue())
//            {
//                blockResponse.setAddAnother(1);
//                return blockResponse;
//            }
//        }
//        blockResponse.setAddAnother(0);
//        return blockResponse;
//    }
//
//    public StaffDTOResponse getListFieldMutileRow(int blockId)
//    {
//
//        StaffDTOResponse staffDTOResponse = new StaffDTOResponse();
//        List<Field> results = fieldDAO.getListFieldOrderAsc(blockId);
//        List<StaffInfoDTOResponse> list = new ArrayList<StaffInfoDTOResponse>();
//        for (Field field : results)
//        {
//            StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//            staffInfoDTO.setFieldId(field.getFieldId());
//            staffInfoDTO.setFieldName(field.getFieldName());
//            staffInfoDTO.setType(field.getEditType());
//            staffInfoDTO.setValue("");
//            staffInfoDTO.setGroup(0);
//            list.add(staffInfoDTO);
//
//        }
//        staffDTOResponse.setListInfor(list);
//        return staffDTOResponse;
//    }
//
//    public StaffDTOResponse deleteMultiField(int userId, int fieldId, int group)
//    {
//
//        Field fieldBlock = fieldDAO.getField(fieldId);
//        if (fieldBlock.getBlockId() == 14)
//        {
//            List<Address> listAddress = addressDAO.getAddressListById(group);
//            if (listAddress.size() > 0)
//            {
//                Address address = listAddress.get(0);
//                address.setCity(null);
//                address.setPostalCode(null);
//                address.setState(null);
//                address.setStreet(null);
//                addressDAO.updateAddress(address);
//            }
//        }
//        else
//        {
//            List<Field> listChild = fieldDAO.getFieldParent(fieldId);
//            for (Field field : listChild)
//            {
//                List<FieldValue> list = fieldValueDAO.getListFieldValueUser(userId, field.getFieldId(), group);
//                if (null != list && list.size() > 0)
//                {
//                    FieldValue fieldValue = list.get(0);
//                    fieldValueDAO.deleteFieldValue(fieldValue);
//                }
//            }
//            List<FieldValue> list = fieldValueDAO.getListFieldValueUser(userId, fieldId, group);
//            if (null != list && list.size() > 0)
//            {
//                FieldValue fieldValue = list.get(0);
//                fieldValueDAO.deleteFieldValue(fieldValue);
//
//            }
//        }
//
//        return new StaffDTOResponse();
//    }
//
//    private String nextSessionId()
//    {
//        SecureRandom random = new SecureRandom();
//        return new BigInteger(30, random).toString(32);
//    }
//
//    public StaffDTOResponse addStaff()
//    {
//
//        List<BiographyBlock> listBiographyBlock = blockDao.findAllBlock();
//        StaffDTOResponse staffDTO = new StaffDTOResponse();
//
//        List<StaffInfoDTOResponse> listInfo = new ArrayList<StaffInfoDTOResponse>();
//        staffDTO.setListInfor(listInfo);
//        staffDTO.setRandom(nextSessionId());
//        for (BiographyBlock biographyBlock : listBiographyBlock)
//        {
//            if (biographyBlock.getBlockId() == 100)
//            {
//                continue;
//            }
//            else
//            {
//                StaffInfoDTOResponse staffInfoDTO = new StaffInfoDTOResponse();
//                staffInfoDTO.setBlockId(biographyBlock.getBlockId());
//                staffInfoDTO.setType(biographyBlock.getBlockDescription());
//                staffInfoDTO.setMandatory(biographyBlock.getMandatory());
//                if (null != biographyBlock.getDisplayKey() && biographyBlock.getDisplayKey().equals(true))
//                {
//                    staffInfoDTO.setDisplayKey(1);
//                }
//                else
//                {
//                    staffInfoDTO.setDisplayKey(0);
//                }
//                listInfo.add(staffInfoDTO);
//            }
//        }
//        return staffDTO;
//    }
//
//
//    public AccountResponse getListCustomer(int userId, String dealerId)
//    {
//
//        List<Customer> listCustomer = customerDAO.getCustomerListByUserId(userId);
//        if (listCustomer.size() > 0)
//        {
//            Customer customer = listCustomer.get(0);
//            int addressId = customer.getAddressId();
//            Integer businessId = customer.getBusinessEntity();
//            customerDAO.deleteCustomer(customer);
//            List<Contact> listContact = contactDAO.getContactAddressListByAddressId(addressId);
//            if (listContact.size() > 0)
//            {
//                Contact contact = listContact.get(0);
//                contactDAO.deleteContact(contact);
//            }
//            List<CommunicationDetail> listCommunicationDetail = communicationDetailDAO.getCommunicationDetailByAddressId(addressId);
//            for (CommunicationDetail communicationDetail : listCommunicationDetail)
//            {
//                communicationDetailDAO.deleteCommunicationDetail(communicationDetail);
//            }
//
//            List<Address> listAddress = addressDAO.getAddressListById(addressId);
//            if (listAddress.size() > 0)
//            {
//                Address address = listAddress.get(0);
//                addressDAO.deleteAddress(address);
//            }
//            if (null != businessId)
//            {
//
//                List<BusinessEntityAddress> listBusinessEntityAddresses = addressDAO.getBusinessEntityAddressList(businessId);
//                for (BusinessEntityAddress businessEntityAddress : listBusinessEntityAddresses)
//                {
//                    addressDAO.deleteBusinessEntityAddress(businessEntityAddress);
//
//                }
//                List<BusinessEntity> listBusinessEntity = businessEntityDAO.getListBusinessEntityFromId(businessId);
//                if (listBusinessEntity.size() > 0)
//                {
//                    BusinessEntity businessEntity = listBusinessEntity.get(0);
//                    businessEntityDAO.deleteBusinessEntity(businessEntity);
//                }
//            }
//
//            List<FieldValue> listFieldValue = fieldValueDAO.getListFieldValueByUserId(userId);
//            for (FieldValue fieldValue : listFieldValue)
//            {
//                fieldValueDAO.deleteFieldValue(fieldValue);
//            }
//
//        }
//
//
//        List list = customerDAO.getCustomerContactListByDealerId(Integer.parseInt(dealerId));//hibernateTemplate.find("select customer,contact from Customer customer,Contact contact where customer.addressId=contact.addressId and customer.uid is not null and  customer.uid != 'none' and  customer.uid != 'archive' and customer.dealerId=?", new Object[]{Integer.parseInt(dealerId)});
//        List<Account> listAccounts = new ArrayList<Account>();
//        for (int j = 0; j < list.size(); j++)
//        {
//            Object[] objects = (Object[]) list.get(j);
//            Customer customer = (Customer) objects[0];
//            Contact contact = (Contact) objects[1];
//            Account account = new Account();
//            account.setFirstName(contact.getFirstName());
//            account.setLastName(contact.getLastName());
//            account.setDisplayName(account.getFirstName() + ", " + account.getLastName());
//            account.setId(customer.getWebId());
//            String sql = "select address,communicationDetail,communicationMethod from Address address,CommunicationMethod communicationMethod,CommunicationDetail communicationDetail ";
//            sql += " where address.webId=communicationDetail.addressId and communicationDetail.communicationMethodId=communicationMethod.webId";
//            sql += " and address.webId=" + contact.getAddressId();
//            List listInformation = fieldDAO.getResults(sql);
//            for (int i = 0; i < listInformation.size(); i++)
//            {
//
//                Object[] ObjectInformation = (Object[]) listInformation.get(i);
//                Address address = (Address) ObjectInformation[0];
//                CommunicationDetail communicationDetail = (CommunicationDetail) ObjectInformation[1];
//                CommunicationMethod communicationMethod = (CommunicationMethod) ObjectInformation[2];
//                if (communicationMethod.getDescription().equals(Utils.mainPhone))
//                {
//                    account.setTelephoneNumber(communicationDetail.getInformation());
//                }
//                else if (communicationMethod.getDescription().equals(Utils.mainMail))
//                {
//                    account.setEmail(communicationDetail.getInformation());
//                }
//
//                account.setAddress(address.getStreet());
//
//            }
//            sql = "select fieldValue from Customer customer,FieldValue fieldValue where fieldValue.userId=customer.webId ";
//            sql += " and fieldValue.fieldId=16";
//            sql += " and customer.webId = ?";
//
//            List<FieldValue> listFieldValue = customerDAO.getCustomerFieldValueListByWebId(customer.getWebId());
//            if (listFieldValue.size() > 0)
//            {
//                FieldValue fieldValue = listFieldValue.get(0);
//                account.setAddress(fieldValue.getFieldValue());
//            }
//
//            account.setMemberId(customer.getUid());
//            listAccounts.add(account);
//        }
//
//        AccountResponse accountResponse = new AccountResponse();
//        Collections.sort(listAccounts, new AccountSortByName());
//        accountResponse.setListAccount(listAccounts);
//        return accountResponse;
//    }
//}
