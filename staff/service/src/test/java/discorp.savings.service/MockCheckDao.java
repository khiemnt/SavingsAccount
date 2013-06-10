package discorp.savings.service;


import com.discorp.savings.dto.CheckDepositDTO;
import com.discorp.staff.dao.CheckDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 10:28 AM
 */
public class MockCheckDao implements CheckDao
{

    Map<String, List<CheckDepositDTO>> map = new HashMap<String, List<CheckDepositDTO>>();

    public void add(String accountNumber, CheckDepositDTO checkDepositDTO)
    {
        if (!map.containsKey(accountNumber))
        {
            List<CheckDepositDTO> list = new ArrayList<CheckDepositDTO>();
            list.add(checkDepositDTO);
            map.put(accountNumber, list);
        }
        else
        {
            List<CheckDepositDTO> list = map.get(accountNumber);
            list.add(checkDepositDTO);
        }

    }

    public List<CheckDepositDTO> getAllCheckOrder(String accountNumber)
    {
        return map.get(accountNumber);
    }

}
