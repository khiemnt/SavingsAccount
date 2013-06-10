package com.discorp.staff.dao;

import com.discorp.staff.model.Block;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BlockDao {
    public List<Block> findAllBlock();
    public List<Block> getListBlock(int blockId);
}
