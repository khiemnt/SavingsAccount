package com.discorp.staff.model;

import javax.persistence.*;

@Entity
@Table(name = "slstf_block")
public class Block
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false,name="block_id")
    private int blockId;
    @Column(nullable = true, length = 130,name="block_description")
    private String blockDescription;
    @Column(nullable = true)
    private int position;
    @Column(nullable = true)
    private Integer mandatory;
    @Column(nullable = true,name="multi_value")
    private Boolean multiValue;
    @Column(nullable = true ,name="display_key")
    private Boolean displayKey;
    public int getBlockId()
    {
        return blockId;
    }

    public void setBlockId(int blockId)
    {
        this.blockId = blockId;
    }

    public String getBlockDescription()
    {
        return blockDescription;
    }

    public void setBlockDescription(String blockDescription)
    {
        this.blockDescription = blockDescription;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public Integer getMandatory()
    {
        return mandatory;
    }

    public void setMandatory(Integer mandatory)
    {
        this.mandatory = mandatory;
    }

    public Boolean getMultiValue()
    {
        return multiValue;
    }

    public void setMultiValue(Boolean multiValue)
    {
        this.multiValue = multiValue;
    }

    public Boolean getDisplayKey() {
        return displayKey;
    }

    public void setDisplayKey(Boolean displayKey) {
        this.displayKey = displayKey;
    }
}
