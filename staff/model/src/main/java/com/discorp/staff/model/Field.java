package com.discorp.staff.model;


import javax.persistence.*;

@Entity
@Table(name = "slstf_field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "field_id")
    private int fieldId;
    @Column(nullable = true, length = 32, name = "edit_type")
    private String editType;
    @Column(nullable = true, length = 32, name = "field_name")
    private String fieldName;
    @Column(nullable = true, name = "field_level")
    private int fieldLevel;

    @Column(nullable = true)
    private int position;

    @Column(nullable = true, name = "parent_id")
    private Integer parentId;
    @Column(nullable = true, name = "block_id")
    private int blockId;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getFieldLevel() {
        return fieldLevel;
    }

    public void setFieldLevel(int fieldLevel) {
        this.fieldLevel = fieldLevel;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
