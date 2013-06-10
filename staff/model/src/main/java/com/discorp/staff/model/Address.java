package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/21/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true)
    private Integer version;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdated;
    @Column(nullable = true, length = 255, name = "city")
    private String city;
    @Column(nullable = true, length = 255, name = "country")
    private String country;
    @Column(nullable = true, length = 255, name = "internal_id")
    private String internalId;
    @Column(nullable = true, length = 255, name = "name")
    private String name;
    @Column(nullable = true, length = 255, name = "name2")
    private String name2;
    @Column(nullable = true, length = 255, name = "postalCode")
    private String postalCode;
    @Column(nullable = true, length = 255, name = "state")
    private String state;
    @Column(nullable = true, length = 255, name = "street")
    private String street;
    @Column(nullable = true, length = 255, name = "street2")
    private String street2;
    @Column(nullable = true, name = "tax_code_id")
    private Integer taxCodeId;
    @Column(nullable = false, name = "address_type")
    private Integer addressType;
    @Column(nullable = true, length = 255)
    private String county;
    @Column(nullable = false)
    private Integer active;
    @Column(nullable = true, name = "start_date")
    private Date startDate;
    @Column(nullable = true, name = "end_date")
    private Date endDate;
    @Column(nullable = true, name = "use_for_bill_to")
    private Boolean useForBillTo;
    @Column(nullable = true, name = "useForShipTo")
    private Boolean useForShipTo;
    @Column(nullable = true, name = "use_for_payment")
    private Boolean useForPayment;
    @Column(nullable = true, name = "use_for_mail_to")
    private Boolean useForMailTo;
    @Column(nullable = true, name = "use_for_main")
    private Boolean useForMain;
    @Column(nullable = true, name = "location_id")
    private Integer locationId;
    @Column(nullable = true, name = "territory_id")
    private Integer territoryId;
    @Column(nullable = true, length = 255)
    private String street3;

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public Integer getTaxCodeId() {
        return taxCodeId;
    }

    public void setTaxCodeId(Integer taxCodeId) {
        this.taxCodeId = taxCodeId;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getUseForBillTo() {
        return useForBillTo;
    }

    public void setUseForBillTo(Boolean useForBillTo) {
        this.useForBillTo = useForBillTo;
    }

    public Boolean getUseForShipTo() {
        return useForShipTo;
    }

    public void setUseForShipTo(Boolean useForShipTo) {
        this.useForShipTo = useForShipTo;
    }

    public Boolean getUseForPayment() {
        return useForPayment;
    }

    public void setUseForPayment(Boolean useForPayment) {
        this.useForPayment = useForPayment;
    }

    public Boolean getUseForMailTo() {
        return useForMailTo;
    }

    public void setUseForMailTo(Boolean useForMailTo) {
        this.useForMailTo = useForMailTo;
    }

    public Boolean getUseForMain() {
        return useForMain;
    }

    public void setUseForMain(Boolean useForMain) {
        this.useForMain = useForMain;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }
}
