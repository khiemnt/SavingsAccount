package com.discorp.staff.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 5/29/12
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "UpdateCreditCardRequest")
public class UpdateCreditCardRequest
{
    public String getNameCard()
    {
        return nameCard;
    }

    public void setNameCard(String nameCard)
    {
        this.nameCard = nameCard;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(String expireDate)
    {
        this.expireDate = expireDate;
    }

    public String getCsvCode()
    {
        return csvCode;
    }

    public void setCsvCode(String csvCode)
    {
        this.csvCode = csvCode;
    }

    public String getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }

    private String nameCard;
    private String creditCardNumber;
    private String expireDate;
    private String csvCode;
    private String dealerId;
}
