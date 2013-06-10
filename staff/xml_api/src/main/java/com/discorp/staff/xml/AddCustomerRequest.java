package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/8/12
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AddCustomerRequest")
public class AddCustomerRequest {
    @XmlElement(required = true)
    private String firstName;
    @XmlElement(required = true)
    private String lastName;
    @XmlElement(required = true)
    private String companyName;
    @XmlElement(required = true)
    private String city;
    @XmlElement(required = true)
    private String stress;
    @XmlElement(required = true)
    private String zip;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String state;
    @XmlElement(required = true)
    private String uidLdap;
	@XmlElement(required = true)
	private String verificationCode;
    @XmlElement(required = true)
    private String dealerId;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUidLdap() {
        return uidLdap;
    }

    public void setUidLdap(String uidLdap) {
        this.uidLdap = uidLdap;
    }
}
