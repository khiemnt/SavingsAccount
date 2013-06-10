package com.discorp.staff.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tu
 * Date: 11/9/12
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "slstf_mailserver_info")
public class MailServerInfo {
    @Id
    @Column(unique = true, nullable = false)
    private int id;
    @Column(nullable = true)
    private String host;
    @Column(nullable = true)
    private int port;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true, name = "web_id")
    private int webId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }
}
