package com.discorp.staff.dao;

import com.discorp.staff.model.MailServerInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MailServerInfoDao {
    public List<MailServerInfo> findAllMailServer();
    public List<MailServerInfo> getMailServerListById(Integer web_id);
    public MailServerInfo updateMailServer(MailServerInfo mailServerInfo);
    public MailServerInfo saveMailServer(MailServerInfo mailServerInfo);
}
