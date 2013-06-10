package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 3/7/13
 * Time: 11:15 AM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "SearchConditionResponse")
public class SearchConditionResponse {
    @XmlElement(name = "SearchAttributes", required = true)
    private List<SearchAttribute> searchAttributes;

    public List<SearchAttribute> getSearchAttributes() {
        return searchAttributes;
    }

    public void setSearchAttributes(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "SearchAttribute")
    public static class SearchAttribute {

        @XmlElement(required = true)
        private int id;
        @XmlElement(required = true)
        private String nameAttribute;
        @XmlElement(required = false)
        private List<Operator> operators;
        @XmlElement(required = true)
        private String typeData;
        @XmlElement(required = false)
        private List<AttributeValue> attributeValues;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getNameAttribute() {
            return nameAttribute;
        }

        public void setNameAttribute(String nameAttribute) {
            this.nameAttribute = nameAttribute;
        }

        public List<Operator> getOperators() {
            return operators;
        }

        public void setOperators(List<Operator> operators) {
            this.operators = operators;
        }



        public List<AttributeValue> getAttributeValues() {
            return attributeValues;
        }

        public void setAttributeValues(List<AttributeValue> attributeValues) {
            this.attributeValues = attributeValues;
        }

        public String getTypeData() {
            return typeData;
        }

        public void setTypeData(String typeData) {
            this.typeData = typeData;
        }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "Operator")
    public static class Operator {
        @XmlElement(required = true)
        private int operatorId;
        @XmlElement(required = true)
        private String operatorName;

        public int getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(int operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "AttributeValue")
    public static class AttributeValue {
        @XmlElement(required = true)
        private int attributeValueId;
        @XmlElement(required = true)
        private String attributeValue;
        @XmlElement(required = true)
        private String attributeName;

        public int getAttributeValueId() {
            return attributeValueId;
        }

        public void setAttributeValueId(int attributeValueId) {
            this.attributeValueId = attributeValueId;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        public void setAttributeValue(String attributeValue) {
            this.attributeValue = attributeValue;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }
    }
}

