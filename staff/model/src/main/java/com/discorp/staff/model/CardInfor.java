package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 5/28/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "card_infor")
public class CardInfor {

    @Id
    @Column(unique = true, nullable = false)
    private int id;
    @Column(nullable = true, length = 255, name = "card_name")
    private String cardName;
    @Column(nullable = true, length = 255, name = "card_number")
    private String cardNumber;
    @Column(nullable = true, length = 255, name = "expire_date")
    private String expireDate;
    @Column(nullable = true, length = 255, name = "csv_code")
    private String csvCode;
    @Column(nullable = true, length = 255, name = "card_type")
    private String cardType;
    @Column(nullable = true, name = "web_id")
    private Integer webId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCsvCode() {
        return csvCode;
    }

    public void setCsvCode(String csvCode) {
        this.csvCode = csvCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }
}
