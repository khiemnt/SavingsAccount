package com.discorp.staff.model;

import javax.persistence.*;

@Entity
@Table(name = "slstf_field_value")
public class FieldValue
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false,name="field_value_id")
    private int fieldValueId;
    @Column(nullable = true,name="user_id")
    private int userId;
    @Column(nullable = true,name="field_id")
    private int fieldId;
    @Column(nullable = true, length = 32,name="field_value")
    private String fieldValue;
    @Column(nullable = true,name="field_visible")
    private Boolean fieldVisible;
    @Column(name="group_multi",nullable = true)
    private int groupMulti;


    public int getFieldValueId()
    {
        return fieldValueId;
    }

    public void setFieldValueId(int fieldValueId)
    {
        this.fieldValueId = fieldValueId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getFieldId()
    {
        return fieldId;
    }

    public void setFieldId(int fieldId)
    {
        this.fieldId = fieldId;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue)
    {
        this.fieldValue = fieldValue;
    }

    public Boolean getFieldVisible()
    {
        return fieldVisible;
    }

    public void setFieldVisible(Boolean fieldVisible)
    {
        this.fieldVisible = fieldVisible;
    }

    public int getGroupMulti()
    {
        return groupMulti;
    }

    public void setGroupMulti(int groupMulti)
    {
        this.groupMulti = groupMulti;
    }
}
