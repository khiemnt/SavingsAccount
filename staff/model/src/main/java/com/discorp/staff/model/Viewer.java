package com.discorp.staff.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: tulh
 * Date: 04/17/2012
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name = "sldoc_viewer")
public class Viewer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false, name = "doc_id")
    private String docId;

    @Column(nullable = true, length = 255, name = "viewer_id")
    private String viewerId;

    @Column(nullable = true, name = "view_count")
    private int viewCount;

    @Column(nullable = true, name = "last_visit")
    private String lastVisit;

    @Column(nullable = true)
    private Boolean required;

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public String getViewerId() {
        return viewerId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

}
