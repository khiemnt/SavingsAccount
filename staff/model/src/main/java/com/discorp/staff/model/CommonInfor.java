package com.discorp.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 5/28/12
 * Time: 3:49 PM
 * To change this template use File | Settings | File TempltermInforates.
 */
@Entity
@Table(name = "common_infor")
public class CommonInfor {
    @Id
    @Column(unique = true, nullable = false)
    private int id;
    @Column(nullable = true, length = 255,name="term_infor")
    private String termInfor;
    @Column(nullable = true, length = 255,name="welcome_infor")
    private String welcomeInfor;
    @Column(nullable = true, length = 255,name="about_us")
    private String aboutUs;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getWelcomeInfor()
    {
        return welcomeInfor;
    }

    public void setWelcomeInfor(String welcomeInfor)
    {
        this.welcomeInfor = welcomeInfor;
    }

    public String getAboutUs()
    {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs)
    {
        this.aboutUs = aboutUs;
    }

    public String getTermInfor()
    {
        return termInfor;
    }

    public void setTermInfor(String termInfor)
    {
        this.termInfor = termInfor;
    }
}
