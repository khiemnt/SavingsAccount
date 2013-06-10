package com.discorp.staff.dao;

import com.discorp.staff.model.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 4/11/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FieldDao
{
    public List getListField(int blockId);
    public Field getField(Integer fieldId);
    public List<Field> getFieldParent(Integer fieldId);
    public List<Field> getListFieldOrderAsc(Integer blockId);
    public List checkValidateInvitationCode(String invitationCode);
    public List getResults(String sql);
    public List getResults(String sql,Object[] objects);
}
