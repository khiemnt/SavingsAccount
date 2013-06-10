package com.discorp.staff.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tu
 * Date: 3/5/13
 * Time: 5:14 PM
 */
@Entity
@Table(name = "fl_attribute_searchable")
public class SearchableAttribute {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private Integer webId;
    @Column(nullable = true, name = "attribute_id")
    private Integer attributeId;
    @Column(nullable = true, length = 255, name = "attribute_name")
    private String attributeName;
    @Column(nullable = true, length = 255, name = "target_table")
    private String targetTable;
    @Column(nullable = true, length = 255, name = "target_join_column")
    private String targetJoinColumn;
    @Column(nullable = true, length = 255, name = "link_table")
    private String linkTable;
    @Column(nullable = true, length = 255, name = "link_join_column")
    private String linkJoinColumn;
    @Column(nullable = true, length = 255, name = "target_column")
    private String targetColumn;
    @Column(nullable = true, name = "type_data")
    private String typeData;

    @Column(nullable = true)

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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getTargetJoinColumn() {
        return targetJoinColumn;
    }

    public void setTargetJoinColumn(String targetJoinColumn) {
        this.targetJoinColumn = targetJoinColumn;
    }

    public String getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(String linkTable) {
        this.linkTable = linkTable;
    }

    public String getLinkJoinColumn() {
        return linkJoinColumn;
    }

    public void setLinkJoinColumn(String linkJoinColumn) {
        this.linkJoinColumn = linkJoinColumn;
    }

    public String getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }
}
