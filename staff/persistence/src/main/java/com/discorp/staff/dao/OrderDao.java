package com.discorp.staff.dao;

import com.discorp.savings.dto.OrderDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 11:55 AM
 */
public interface OrderDao {
    public List<OrderDTO> getAllOrder(String accountNumber);

    public OrderDTO createOrder(String accountNumber, OrderDTO orderDTO);

    public void updateOrder(OrderDTO orderDTO);

    public OrderDTO getOrder(String accountNumber,int id);

    public List<OrderDTO>getAllOrder();

    public List<OrderDTO> getAllPendingOrders();

    public List<OrderDTO> getAllOrderShouldHaveCreatedTransactionByCheckDate(Date checkDate);

    List<OrderDTO> getAllOrderThatApprovedAndNotExpired(Date checkDate);
}
