package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.TextEntryDao;
import com.discorp.staff.model.TextEntry;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextEntryDaoImpl implements TextEntryDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<TextEntry> findAllTextEntry() {
        return hibernateTemplate.find("from TextEntry c");
    }

    public List<TextEntry> getTextEntryListById(Integer id) {
        return hibernateTemplate.find("from TextEntry c where c.id=?", id);

    }
    public void updateTextEntry(TextEntry textEntry) {
       hibernateTemplate.update(textEntry);

    }
}
