package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.SearchableAttributeDao;
import com.discorp.staff.model.SearchableAttribute;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dungpx
 * Date: 3/7/13
 * Time: 11:33 AM
 */
public class SearchableAttributeDaoImpl implements SearchableAttributeDao{
    private HibernateTemplate hibernateTemplate;
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<SearchableAttribute> findAllSeachabel() {
        return hibernateTemplate.find("from SearchableAttribute seach order by seach.webId asc");

    }

	public List<SearchableAttribute> searchByAttributeName(String attribute) {
		return hibernateTemplate.find("from SearchableAttribute seach where seach.attributeName = ?", attribute);
	}

	public List<String> searchCustomerMarketing(String query) {
		Session session  = SessionFactoryUtils.getSession(hibernateTemplate.getSessionFactory(), true);
		Query searchQuery = session.createSQLQuery(query);
		List result = searchQuery.list();
		List<String> returnValue = new ArrayList<String>();
		if(result.size()>0) {
			for(int i=0; i<result.size(); i++) {
				returnValue.add(result.get(i).toString());
			}
		}
        session.close();
		/*List<Integer> list = new ArrayList<Integer>();
		try{
			list = hibernateTemplate.find(query);
		}
		catch (HibernateException e){
			e.printStackTrace();
		}*/
		return returnValue;

	}
}
