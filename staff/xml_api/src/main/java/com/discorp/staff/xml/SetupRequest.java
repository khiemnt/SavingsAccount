package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 5/29/12
 * Time: 8:47 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "SetupRequest")
public class SetupRequest {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String city;
    @XmlElement(required = true)
    private String st;
    @XmlElement(required = true)
    private String zip;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String website;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String name_card;
    @XmlElement(required = true)
    private String number_card;
    @XmlElement(required = true)
    private String expire_date;
    @XmlElement(required = true)
    private String csv_code;
    @XmlElement(required = true)
    private String uidLdap;
    @XmlElement(required = false)
    private String nameCompany;
    @XmlElement(required = false)
    private String cardType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName_card() {
        return name_card;
    }

    public void setName_card(String name_card) {
        this.name_card = name_card;
    }

    public String getNumber_card() {
        return number_card;
    }

    public void setNumber_card(String number_card) {
        this.number_card = number_card;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getCsv_code() {
        return csv_code;
    }

    public void setCsv_code(String csv_code) {
        this.csv_code = csv_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUidLdap() {
        return uidLdap;
    }

    public void setUidLdap(String uidLdap) {
        this.uidLdap = uidLdap;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
