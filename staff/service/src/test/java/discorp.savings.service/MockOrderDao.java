package discorp.savings.service;

import com.discorp.savings.dto.OrderDTO;
import com.discorp.staff.dao.OrderDao;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 11:57 AM
 */
public class MockOrderDao implements OrderDao {
    Map<String, List<OrderDTO>> map = new HashMap<String, List<OrderDTO>>();


    public List<OrderDTO> getAllOrder(String accountNumber) {
        return map.get(accountNumber);
    }

    public OrderDTO createOrder(String accountNumber, OrderDTO orderDTO) {

        OrderDTO orderDTONew = new OrderDTO();
        updateItem(orderDTO,orderDTONew);

        if (!map.containsKey(accountNumber)) {
            List<OrderDTO> list = new ArrayList<OrderDTO>();
            list.add(orderDTONew);
            map.put(accountNumber, list);
        } else {
            List<OrderDTO> list = map.get(accountNumber);
            list.add(orderDTONew);
        }
        return orderDTO;
    }

    public OrderDTO getOrder(String accountNumber,int id)
    {
        List<OrderDTO> orderDTOList = map.get(accountNumber);
        for(OrderDTO orderDTO : orderDTOList)
        {
            if(orderDTO.getId() == id)
            {
                return orderDTO;
            }
        }
        return new OrderDTO();

    }
    public void updateOrder(OrderDTO orderDTO) {

        List<OrderDTO> orderDTOList = map.get(orderDTO.getAccountNumber());
        for(OrderDTO orderItem : orderDTOList)
        {
            if(orderItem.getId() == orderDTO.getId())
            {
               updateItem(orderDTO,orderItem);
            }
        }
    }
    public void  updateItem(OrderDTO orderDTO,OrderDTO orderItem)       {

           orderItem.setId(orderDTO.getId());
           orderItem.setAccountNumber(orderDTO.getAccountNumber());
           orderItem.setApproved(orderDTO.getApproved());
           orderItem.setAmount(orderDTO.getAmount());
           orderItem.setDeposit(orderDTO.isDeposit());
           orderItem.setFrequency(orderDTO.getFrequency());
           orderItem.setNumOfTimesInTotal(orderDTO.getNumOfTimesInTotal());
           orderItem.setCreatedOnDate(orderDTO.getCreatedOnDate());
           orderItem.setValidUntilCancel(orderDTO.isValidUntilCancel());
           orderItem.setTransactionGenerateDate(orderDTO.getTransactionGenerateDate());
           orderItem.setTransactionFrequency(orderDTO.getTransactionFrequency());
           orderItem.setTypeOfOrder(orderDTO.getTypeOfOrder());
           orderItem.setIsCanceled(orderDTO.isCanceled());


    }
    public List<OrderDTO>getAllOrder()
    {
        List<OrderDTO> orderDTOListTemp = new ArrayList<OrderDTO>();
        for (Object key : map.keySet()) {
            List<OrderDTO> orderDTOList = map.get(key);
            for(OrderDTO orderDTO:orderDTOList){
                orderDTOListTemp.add(orderDTO);
            }
        }
        return orderDTOListTemp;
    }
    public List<OrderDTO>getAllPendingOrders()
    {
        List<OrderDTO> orderDTOListTemp = new ArrayList<OrderDTO>();
        for (Object key : map.keySet()) {
            List<OrderDTO> orderDTOList = map.get(key);
            for(OrderDTO orderDTO:orderDTOList){
                if(orderDTO.getApproved()==null && !orderDTO.isCanceled()){
                orderDTOListTemp.add(orderDTO);
                }
            }
        }
        return orderDTOListTemp;
    }
   public List<OrderDTO> getAllOrderShouldHaveCreatedTransactionByCheckDate(Date checkDate){
       List<OrderDTO> orderDTOListTemp = new ArrayList<OrderDTO>();
       for (Object key : map.keySet()) {
           List<OrderDTO> orderDTOList = map.get(key);
           for(OrderDTO orderDTO:orderDTOList){
               if(orderDTO.getApproved() && orderDTO.getTransactionGenerateDate().compareTo(checkDate)>0){
                   orderDTOListTemp.add(orderDTO);
               }
           }
       }
       return orderDTOListTemp;
    }
   public List<OrderDTO> getAllOrderThatApprovedAndNotExpired(Date checkDate){
        List<OrderDTO> orderDTOListTemp = new ArrayList<OrderDTO>();
        for (Object key : map.keySet()) {
            List<OrderDTO> orderDTOList = map.get(key);
            for(OrderDTO orderDTO:orderDTOList){
                if(orderDTO.getApproved() && orderDTO.getTransactionGenerateDate().compareTo(checkDate)>0 && !orderDTO.isCanceled() && orderDTO.getNumOfTimesInTotal()>0){
                    orderDTOListTemp.add(orderDTO);
                }
            }
        }
        return orderDTOListTemp;
    }
}
