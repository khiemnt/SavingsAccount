package com.discorp.staff.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tu
 * Date: 3/5/13
 * Time: 5:30 PM
 */
@Entity
@Table(name = "fl_attribute_value")
public class AttributeValue {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private Integer webId;
    @Column(nullable = true, name = "attribute_id")
    private Integer attributeId;
    @Column(nullable = true, length = 1000)
    private String value;
    @Column(nullable = true, length = 1000, name = "value_name")
    private String valueName;

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
