package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/21/12
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "communication_method")
public class CommunicationMethod {
    @Id
    @Column(unique = true, nullable = false, name = "web_id")
    private int webId;
    @Column(nullable = true)
    private Integer version;
    @Column(nullable = true, name = "dealer_id")
    private Integer dealerId;
    @Column(nullable = true, length = 255)
    private String description;

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
