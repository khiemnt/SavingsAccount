package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 11/6/12
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "slstf_common_info")
public class TextEntry {
    @Id
    @Column(unique = true, nullable = false)
    private int id;
    @Column(nullable = true, name = "block_name")
    private String blockName;
    @Column(nullable = true, name = "block_content")
    private String blockContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockContent() {
        return blockContent;
    }

    public void setBlockContent(String blockContent) {
        this.blockContent = blockContent;
    }

}
