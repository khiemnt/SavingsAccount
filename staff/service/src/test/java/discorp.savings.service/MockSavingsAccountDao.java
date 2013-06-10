package discorp.savings.service;


import com.discorp.savings.dto.SavingsAccountDTO;
import com.discorp.staff.dao.SavingsAccountDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: khiemnt
 * Date: 5/24/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockSavingsAccountDao implements SavingsAccountDao
{


    private Map<String, SavingsAccountDTO> accountList = new HashMap<String, SavingsAccountDTO>();

    public SavingsAccountDTO getAccountByAccountNumber(String accountNumber)
    {
        return new SavingsAccountDTO(accountList.get(accountNumber));
    }

    public void save(SavingsAccountDTO account)
    {
        SavingsAccountDTO accountFromDB = accountList.get(account.getAccountNumber());
        if (accountFromDB == null)
        {
            accountList.put(account.getAccountNumber(), new SavingsAccountDTO(account));
        }
        else
        {
            accountFromDB.copy(account);
        }
    }

}
