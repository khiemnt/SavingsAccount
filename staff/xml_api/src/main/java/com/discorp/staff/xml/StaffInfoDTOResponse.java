package com.discorp.staff.xml;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/11/12
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaffInfoDTOResponse")
public class StaffInfoDTOResponse
{
    @XmlElement(required = true)
    private String fieldName;
    @XmlElement(required = true)
    private int fieldId;
    @XmlElement(required = true)
    private String value;
    @XmlElement(required = true)
    private String type;
    @XmlElement(required = true)
    private int displayOrder;
    @XmlElement(name = "list", required = true)
    private List<StaffInfoDTOResponse> list;
    private int blockId;
    @XmlElement(required = true)
    private int show;
    @XmlElement(required = true)
    private int showIcon;
    @XmlElement(required = true)
    private int showIconHide;
    @XmlElement(required = true)
    private int hasItem;
    @XmlElement(required = true)
    private int mandatory;
    @XmlElement(required = true)
    private int group;
    @XmlElement(required = true)
    private int enableRemove;
    @XmlElement(required = true)
    private boolean visible;
     @XmlElement(required = true)
    private int confidential;
    @XmlElement(required = true)
    private int displayKey;
    public int getConfidential()
    {
        return confidential;
    }

    public void setConfidential(int confidential)
    {
        this.confidential = confidential;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public int getEnableRemove()
    {
        return enableRemove;
    }

    public void setEnableRemove(int enableRemove)
    {
        this.enableRemove = enableRemove;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    public List<StaffInfoDTOResponse> getList()
    {
        return list;
    }

    public void setList(List<StaffInfoDTOResponse> list)
    {
        this.list = list;
    }

    public int getBlockId()
    {
        return blockId;
    }

    public void setBlockId(int blockId)
    {
        this.blockId = blockId;
    }

    public int getShow()
    {
        return show;
    }

    public void setShow(int show)
    {
        this.show = show;
    }

    public int getShowIcon()
    {
        return showIcon;
    }

    public void setShowIcon(int showIcon)
    {
        this.showIcon = showIcon;
    }

    public int getShowIconHide()
    {
        return showIconHide;
    }

    public void setShowIconHide(int showIconHide)
    {
        this.showIconHide = showIconHide;
    }

    public int getHasItem()
    {
        return hasItem;
    }

    public void setHasItem(int hasItem)
    {
        this.hasItem = hasItem;
    }

    public int getMandatory()
    {
        return mandatory;
    }

    public void setMandatory(int mandatory)
    {
        this.mandatory = mandatory;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }

    public int getDisplayKey() {
        return displayKey;
    }

    public void setDisplayKey(int displayKey) {
        this.displayKey = displayKey;
    }
}
