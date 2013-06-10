package discorp.savings.service;


import com.discorp.savings.dto.TransactionDTO;
import com.discorp.savings.service.impl.TransactionSortByDate;
import com.discorp.staff.dao.TransactionDao;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 9:28 AM
 */
public class MockTransactionDao implements TransactionDao
{

    Map<String, List<TransactionDTO>> map = new HashMap<String, List<TransactionDTO>>();

    public TransactionDTO save(TransactionDTO transactionDTO)
    {
        if (!map.containsKey(transactionDTO.getAccountNumber()))
        {
            List<TransactionDTO> list = new ArrayList<TransactionDTO>();
            TransactionDTO transactionDTONew = new TransactionDTO();
            copyFields(transactionDTONew, transactionDTO);
            list.add(transactionDTONew);
            map.put(transactionDTO.getAccountNumber(), list);
        }
        else
        {
            List<TransactionDTO> list = map.get(transactionDTO.getAccountNumber());
            TransactionDTO transactionDTONew = new TransactionDTO();
            copyFields(transactionDTONew, transactionDTO);
            list.add(transactionDTONew);
            System.out.println("");
        }
        return transactionDTO;
    }

    public TransactionDTO getTransaction(int id, String accountNumber)
    {
        List<TransactionDTO> transactionDTOs = map.get(accountNumber);
        for (TransactionDTO transactionDTO : transactionDTOs)
        {
            if (transactionDTO.getId() == id)
            {
                TransactionDTO transactionDTONew = new TransactionDTO();
                copyFields(transactionDTONew, transactionDTO);
                return transactionDTONew;
            }
        }
        return new TransactionDTO();
    }

    public List<TransactionDTO> getAllTransaction()
    {
        List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();
        for (Object key : map.keySet())
        {
            List<TransactionDTO> transactionDTOs = map.get(key);
            for (TransactionDTO transactionDTO : transactionDTOs)
            {
                transactionDTOList.add(transactionDTO);
            }
        }
        Collections.sort(transactionDTOList, new TransactionSortByDate());
        return transactionDTOList;
    }

    public List<TransactionDTO> getListTransactionsHistory(String accountNumber)
    {
        //   Collections.sort(map.get(accountNumber), new TransactionSortByDate());
        return map.get(accountNumber);
    }

    public List<TransactionDTO> getListTransactionsWithPlanDate(String accountNumber, Date dateReviewTransaction)
    {
        Collections.sort(map.get(accountNumber), new TransactionSortByDate());
        List<TransactionDTO> list = new ArrayList<TransactionDTO>();
        for (TransactionDTO transactionDTO : map.get(accountNumber))
        {

            if (!transactionDTO.isExecute() && dateReviewTransaction.compareTo(transactionDTO.getPlannedToExecuteDate()) >= 0)
            {
                list.add(transactionDTO);
            }

        }
        return list;
    }

    public void update(TransactionDTO transactionDTO)
    {
        List<TransactionDTO> transactionDTOList = map.get(transactionDTO.getAccountNumber());
        for (TransactionDTO transactionItem : transactionDTOList)
        {
            if (transactionItem.getId() == transactionDTO.getId())
            {
                copyFields(transactionItem, transactionDTO);
            }
        }
    }

    public void copyFields(TransactionDTO target, TransactionDTO source)
    {
        target.setId(source.getId());
        target.setExecute(source.isExecute());
        target.setAccountNumber(source.getAccountNumber());
        target.setAmount(source.getAmount());
        target.setDate(source.getDate());
        target.setDeposit(source.isDeposit());
        target.setDescription(source.getDescription());
        target.setTypeOfOrder(source.getTypeOfOrder());
        target.setActualExecutionDate(source.getActualExecutionDate());
        target.setPlannedToExecuteDate(source.getPlannedToExecuteDate());
        target.setInterestDate(source.getInterestDate());
    }

}
