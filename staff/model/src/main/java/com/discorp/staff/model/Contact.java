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
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;

    @Column(nullable = true)
    private Integer version;

    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;

    @Column(nullable = true, length = 255, name = "date_updated")
    private Timestamp dateUpdate;

    @Column(nullable = true, name = "address_id")
    private Integer addressId;

    @Column(nullable = true, length = 255)
    private String nickname;

    @Column(nullable = true, length = 255)
    private String notes;

    @Column(nullable = true, name = "language_id")
    private Integer languageId;

    @Column(nullable = true, name = "role_id")
    private Integer roleId;

    @Column(nullable = true)
    private Date birthday;

    @Column(nullable = true, length = 255, name = "first_name")
    private String firstName;

    @Column(nullable = true, length = 255, name = "last_name")
    private String lastName;

    @Column(nullable = true, length = 255, name = "middle_name")
    private String middleName;

    @Column(nullable = true, length = 255)
    private String title;

    @Column(nullable = true, length = 255)
    private String suffix;

    @Column(nullable = true, length = 255, name = "internal_id")
    private String internalId;

    @Column(nullable = true, length = 255)
    private String gender;

    @Column(nullable = true, length = 255, name = "position_name")
    private String positionName;

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

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
