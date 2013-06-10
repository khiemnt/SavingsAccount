package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/30/12
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "InformationResponse")
public class InformationResponse
{
    @XmlElement(required = true)
    private String welCome;

    @XmlElement(required = true)
    private String term;

    @XmlElement(required = true)
    private String abouts;

    public String getWelCome() {
        return welCome;
    }

    public void setWelCome(String welCome) {
        this.welCome = welCome;
    }

    public String getTerm()
    {
        return term;
    }

    public void setTerm(String term)
    {
        this.term = term;
    }

    public String getAbouts()
    {
        return abouts;
    }

    public void setAbouts(String abouts)
    {
        this.abouts = abouts;
    }
}
