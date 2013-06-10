package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/11/12
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffDTOResponse")
public class StaffDTOResponse
{
    @XmlElement(required = true)
    private String firstName;
    @XmlElement(required = true)
    private String lastName;
    @XmlElement(required = true)
    private String imgPath;
    @XmlElement(required = true)
    private String displayName;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String telephoneNumber;
    @XmlElement(required = true)
    private String memberId;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String join;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String title;
    @XmlElement(name = "listInfor", required = true)
    private List<StaffInfoDTOResponse> listInfor;
    @XmlElement(required = true)
    private String block;
    @XmlElement(required = true)
    private int id;
    @XmlElement(required = true)
    private String random;
    @XmlElement(required = true)
    private String role;

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getJoin()
    {
        return join;
    }

    public void setJoin(String join)
    {
        this.join = join;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<StaffInfoDTOResponse> getListInfor()
    {
        return listInfor;
    }

    public void setListInfor(List<StaffInfoDTOResponse> listInfor)
    {
        this.listInfor = listInfor;
    }

    public String getBlock()
    {
        return block;
    }

    public void setBlock(String block)
    {
        this.block = block;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRandom()
    {
        return random;
    }

    public void setRandom(String random)
    {
        this.random = random;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
