package com.discorp.staff.dao.hibernate;

import com.discorp.staff.dao.BlockDao;
import com.discorp.staff.model.Block;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockDaoImpl implements BlockDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    public List<Block>findAllBlock()
    {
        return hibernateTemplate.find("from Block order by position asc");
    }


    public List<Block> getListBlock(int blockId)
    {
        List<Block> listBlock = hibernateTemplate.find("from Block block where block.blockId=?", blockId);
        return listBlock;
    }
}
