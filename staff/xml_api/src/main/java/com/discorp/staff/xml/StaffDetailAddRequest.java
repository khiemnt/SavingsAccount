package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/12/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffDetailAddRequest")
public class StaffDetailAddRequest
{
    @XmlElement(name = "listStaffField", required = true)
    private List<StaffField> listStaffField;

    public List<StaffField> getListStaffField()
    {
        return listStaffField;
    }

    public void setListStaffField(List<StaffField> listStaffField)
    {
        this.listStaffField = listStaffField;
    }

    @XmlElement(required = true)
    private int userId;

    @XmlElement(required = true)
    private String dealerId;

    public String getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "StaffField")
    public static class StaffField
    {
        @XmlElement(required = true)
        private int fieldId;
        @XmlElement(required = true)
        private String value;
        @XmlElement(required = true)
        private String visible;
        @XmlElement(required = true)
        private Integer group;
        @XmlElement(required = true)
        private boolean visibleField;

        public int getFieldId()
        {
            return fieldId;
        }

        public void setFieldId(int fieldId)
        {
            this.fieldId = fieldId;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getVisible()
        {
            return visible;
        }

        public void setVisible(String visible)
        {
            this.visible = visible;
        }

        public Integer getGroup()
        {
            return group;
        }

        public void setGroup(Integer group)
        {
            this.group = group;
        }

        public boolean isVisibleField()
        {
            return visibleField;
        }

        public void setVisibleField(boolean visibleField)
        {
            this.visibleField = visibleField;
        }
    }
}
