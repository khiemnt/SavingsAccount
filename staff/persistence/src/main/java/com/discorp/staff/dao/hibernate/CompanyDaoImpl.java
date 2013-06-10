package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.CompanyDao;
import com.discorp.staff.model.Company;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 9/18/12
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyDaoImpl implements CompanyDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void saveCompany(Company company)
    {
        hibernateTemplate.save(company);
    }
    public List<String> getCompanyNameByDealerId(Integer dealerId)
    {
        return (List<String>)hibernateTemplate.find("select company.description from Company company where company.dealerId=?", dealerId);
    }
    public List<Company>findAllCompany()
    {
        return hibernateTemplate.find("from Company company order by company.webId desc");
    }
}
