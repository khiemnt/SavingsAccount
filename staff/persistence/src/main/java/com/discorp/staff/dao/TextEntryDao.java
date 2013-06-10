package com.discorp.staff.dao;
 import com.discorp.staff.model.TextEntry;

 import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 12/5/12
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TextEntryDao {
    public List<TextEntry> findAllTextEntry();
    public List<TextEntry> getTextEntryListById(Integer id);
    public void updateTextEntry(TextEntry textEntry);
}
