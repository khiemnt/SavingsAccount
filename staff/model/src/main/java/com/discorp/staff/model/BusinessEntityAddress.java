package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/24/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "business_entity_address")
public class BusinessEntityAddress {

    @Column(nullable = true, name = "business_entity_id")
    private Integer businessEntityId;
    @Id
    @Column(nullable = true, name = "address_id")
    private Integer addressId;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }


}
