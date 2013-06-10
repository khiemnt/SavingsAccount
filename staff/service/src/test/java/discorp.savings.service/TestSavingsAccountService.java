package discorp.savings.service;


import com.discorp.savings.dto.CheckDepositDTO;
import com.discorp.savings.dto.OrderDTO;
import com.discorp.savings.dto.SavingsAccountDTO;
import com.discorp.savings.dto.TransactionDTO;
import com.discorp.savings.service.impl.SavingsAccountServiceImpl;
import com.discorp.staff.dao.CheckDao;
import com.discorp.staff.dao.OrderDao;
import com.discorp.staff.dao.SavingsAccountDao;
import com.discorp.staff.dao.TransactionDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 11:40 AM
 */
public class TestSavingsAccountService {
    SavingsAccountServiceImpl service;
    int ownerWebID = 3;
    Double principal = 1000.0;
    Date startDate = new Date("2013/1/1");
    private SavingsAccountDao mockSavingsAccountDao;
    private TransactionDao mockTransactionDao;
    private CheckDao checkDao;
    private OrderDao orderDao;

    @Before
    public void setUp() {
        mockSavingsAccountDao = new MockSavingsAccountDao();
        mockTransactionDao = new MockTransactionDao();
        checkDao = new MockCheckDao();
        orderDao = new MockOrderDao();
        service = new SavingsAccountServiceImpl(mockSavingsAccountDao, mockTransactionDao, checkDao, orderDao);
    }

    private SavingsAccountDTO createAccount() {
        int ownerWebID = 3;
        Double principal = 1000.0;
        Date startDate = new Date("2013/1/1");
        return service.openAccount(ownerWebID, principal, startDate);
    }


    private OrderDTO createOrderDTO(String accountNumber, Date onDate, Date dateRecurring, Boolean isApprove, OrderDTO.Frequency typeOfFrequency) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCreatedOnDate(onDate);
        orderDTO.setTransactionGenerateDate(dateRecurring);
        orderDTO.setFrequency(30);
        orderDTO.setApproved(isApprove);
        orderDTO.setAmount(2000);
        orderDTO.setValidUntilCancel(false);
        orderDTO.setAccountNumber(accountNumber);
        orderDTO.setTypeOfOrder(OrderDTO.Type.PAYROLL);
        orderDTO.setTransactionFrequency(typeOfFrequency);

        return service.createOrder(accountNumber, orderDTO);
    }

     @Test
    public void openAccountIsPersistent() {
        SavingsAccountDTO account = createAccount();

        assertNotNull(account);
        assertEquals(ownerWebID, account.getOwnerID());
        assertEquals(principal, account.getPrincipal());
        assertEquals(principal, account.getBalance());
        assertEquals(startDate, account.getStartDate());
        String accountNumber = account.getAccountNumber();
        assertNotNull(accountNumber);

        /*return account from db*/
        int ownerWebID = 3;
        Double principal = 1000.0;
        Date startDate = new Date("2013/1/1");
        SavingsAccountDTO accountDB = new SavingsAccountDTO(ownerWebID, principal, startDate);

        SavingsAccountDTO accountGotFromDB = service.getAccount(accountNumber);


        assertNotNull(accountGotFromDB);
        assertEquals(account.getOwnerID(), accountGotFromDB.getOwnerID());
        assertEquals(principal, accountGotFromDB.getPrincipal());
        assertEquals(principal, accountGotFromDB.getBalance());
        assertEquals(startDate, accountGotFromDB.getStartDate());

        TransactionDTO firstDeposit = service.getListTransactionsHistory(accountNumber).get(0);
        assertEquals(1000,firstDeposit.getAmount(),0.001);
        assertTrue(firstDeposit.isExecute());
        assertEquals(startDate, firstDeposit.getInterestDate());

    }

    @Test
    public void addTransactionIsPersistent() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        boolean isExecute = true;
        boolean isDeposit = true;
        TransactionDTO transactionDTO = service.createTransaction(accountNumber, 1000.0, new Date(), new Date(), isExecute, isDeposit);
        assertTrue(transactionDTO != null);
        TransactionDTO transaction = service.getTransaction(transactionDTO.getId(), accountNumber);
        assertTrue(transaction != null);
        assertEquals(1000.0, transaction.getAmount(), 0.0001);

    }

    @Test
    public void executeDepositTransactionUpdatesAccountAndIsPersistent() {
        //open account and add transaction for saving account
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        boolean isExecute = false;
        boolean isDeposit = true;
        Date transactionCreateDate = new Date("2013/2/1");
        Date plannedToExecuteDate = new Date("2013/2/2");
        Date actualExecutionDate = new Date("2013/2/3");
        TransactionDTO deposit = service.createTransaction(accountNumber, 1000.0, transactionCreateDate, plannedToExecuteDate, isExecute, isDeposit);

        service.executeTransaction(deposit, actualExecutionDate);

        SavingsAccountDTO accountFromDB = service.getAccount(accountNumber);
        TransactionDTO depositFromDB = service.getTransaction(deposit.getId(), accountNumber);
        assertTrue(account != accountFromDB);
        assertEquals(2000.0, accountFromDB.getBalance(), 0.001);
        assertTrue(depositFromDB.isExecute());
        assertTrue(depositFromDB.getActualExecutionDate().equals(actualExecutionDate));
    }

    @Test
    public void executeWithdrawalTransactionUpdatesAccountAndIsPersistent() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        boolean isExecute = false;
        boolean isDeposit = false;
        Date transactionCreateDate = new Date("2013/2/1");
        Date plannedToExecuteDate = new Date("2013/2/2");
        Date actualExecutionDate = new Date("2013/2/3");
        TransactionDTO deposit = service.createTransaction(accountNumber, 1000.0, transactionCreateDate, plannedToExecuteDate, isExecute, isDeposit);


        service.executeTransaction(deposit, actualExecutionDate);

        SavingsAccountDTO accountFromDB = service.getAccount(accountNumber);
        TransactionDTO depositFromDB = service.getTransaction(deposit.getId(), accountNumber);
        assertTrue(account != accountFromDB);
        assertEquals(0.0, accountFromDB.getBalance(), 0.001);
        assertTrue(depositFromDB.isExecute());
        assertTrue(depositFromDB.getActualExecutionDate().equals(actualExecutionDate));

    }

    @Test
    public void testTwoAccountsHaveDifferentAccountNumber() {
        SavingsAccountDTO acc1 = createAccount();
        SavingsAccountDTO acc2 = createAccount();
        assertNotSame(acc1.getAccountNumber(), acc2.getAccountNumber());
    }

    @Test
    public void testGetListOfTransactionsExecutedOrNotOfOneAccount() {
        String accountNumber1 = createAccount().getAccountNumber();
        String accountNumber2 = createAccount().getAccountNumber();

        boolean isExecuted = true;
        service.createTransaction(accountNumber1, 1000.0, new Date(), new Date(), isExecuted, true);
        service.createTransaction(accountNumber2, 2000.0, new Date(), new Date(), isExecuted, false);
        isExecuted = false;
        service.createTransaction(accountNumber1, 3000.0, new Date(), new Date(), isExecuted, true);
        service.createTransaction(accountNumber2, 1000.0, new Date(), new Date(), isExecuted, false);
        service.createTransaction(accountNumber2, 2000.0, new Date(), new Date(), isExecuted, true);

        // opening transaction included in the executed list
        assertEquals(2, service.getListTransactionsHistory(accountNumber1, true).size());
        assertEquals(1, service.getListTransactionsHistory(accountNumber1, false).size());
        assertEquals(2, service.getListTransactionsHistory(accountNumber2, true).size());
        assertEquals(2, service.getListTransactionsHistory(accountNumber2, false).size());
    }

    @Test
    public void transactionStateIsUpdatedAfterTransactionExecution() {
        String accountNumber = createAccount().getAccountNumber();
        boolean isExecuted = false;
        TransactionDTO deposit = service.createTransaction(accountNumber, 3000.0,
                new Date("2013/1/1"), new Date("2013/1/3"), isExecuted, true);
        TransactionDTO withdrawal = service.createTransaction(accountNumber, 3000.0,
                new Date("2013/1/1"), new Date("2013/1/5"), isExecuted, false);

        assertFalse(deposit.isExecute());
        assertFalse(withdrawal.isExecute());

        service.executeTransaction(deposit, new Date("2013/1/4"));
        service.executeTransaction(withdrawal, new Date("2013/1/4"));

        assertTrue(deposit.isExecute());
        assertTrue(withdrawal.isExecute());
    }


    //test list transaction that accountant will execute
    @Test
    public void testListOfTransactionsOfOneAccountToBeExecutedByCheckDate() {
        String accountNumber = createAccount().getAccountNumber();
        Date transactionCreateDate = new Date("2013/1/19");
        boolean isDeposit = true;

        service.createTransaction(accountNumber, 1.0, transactionCreateDate, new Date("2013/1/21"), false, isDeposit);
        service.createTransaction(accountNumber, 2.0, transactionCreateDate, new Date("2013/1/20"), true, isDeposit);
        service.createTransaction(accountNumber, 2.0, transactionCreateDate, new Date("2013/1/21"), false, isDeposit);
        service.createTransaction(accountNumber, 2.0, transactionCreateDate, new Date("2013/1/21"), true, isDeposit);
        service.createTransaction(accountNumber, 2.0, transactionCreateDate, new Date("2013/1/22"), false, isDeposit);

        assertEquals(2, service.getListTransactionsWithPlanDate(accountNumber, new Date("2013/1/21")).size());
    }

    @Ignore
    public void testAccrueInterestThatUsesAccountObject() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        Date checkDate = new Date("2013/01/10");
        service.accrueInterest(accountNumber, checkDate);
        assertEquals(1000, account.getPrincipal(), 0.001);

        //deposit
        boolean isExecute = false;
        boolean isDeposit = true;
        double amount = 2000;
        Date createDate = new Date("2013/01/15");
        Date interestDate = new Date("2013/1/16");
        Date actualExecutionDate = new Date("2013/1/17");
        Date plannedToExecuteDate = new Date("2013/1/17");
        TransactionDTO transactionDTO = service.createTransaction(accountNumber, amount, createDate, plannedToExecuteDate, isExecute, isDeposit);
        transactionDTO.setInterestDate(interestDate);
        service.executeTransaction(transactionDTO, actualExecutionDate);

        checkDate = new Date("2013/3/30");
        service.accrueInterest(accountNumber, checkDate);
        // earned but not accrued interest
        assertEquals(3000, account.getBalance(), 0.001);

    }

    @Test
    public void testAllTransactionInPeriod(){
        String accountNumber = createAccount().getAccountNumber();
        Date transactionCreateDate = new Date("2013/1/19");
        boolean isDeposit = true;
        service.executeTransaction(service.createTransaction(accountNumber, 1000.0,  new Date("2013/1/21"), new Date("2013/1/21"), false, isDeposit),new Date("2013/1/21"));
        service.executeTransaction(service.createTransaction(accountNumber, 2000.0,  new Date("2013/1/23"), new Date("2013/1/23"), false, isDeposit),new Date("2013/1/23"));
        service.executeTransaction(service.createTransaction(accountNumber, 2000.0,  new Date("2013/1/24"), new Date("2013/1/24"), false, isDeposit),new Date("2013/1/24"));
        service.executeTransaction(service.createTransaction(accountNumber, 2000.0,  new Date("2013/1/25"), new Date("2013/1/25"), false, isDeposit),new Date("2013/1/25"));
        service.executeTransaction(service.createTransaction(accountNumber, 2000.0,  new Date("2013/1/27"), new Date("2013/1/27"), false, isDeposit),new Date("2013/1/27"));
        service.createTransaction(accountNumber, 2000.0, new Date("2013/1/27"), new Date("2013/1/27"), false, isDeposit);
        assertEquals(5, service.getTransactionsExecutedInAPeriod(accountNumber, new Date("2013/1/1"), new Date("2013/1/28")).size());
    }


    @Test
    public void accrueInterestWhenTooEarlyChangesNothing() {
        String accountNumber = createAccount().getAccountNumber();
        Date checkDate = new Date("2013/01/10");
        service.accrueInterest(accountNumber, checkDate);
        SavingsAccountDTO account = service.getAccount(accountNumber);
        assertEquals(1000, account.getPrincipal(), 0.001);
    }


    @Test
    public void firstTimeAccrueInterest() {
        String accountNumber = createAccount().getAccountNumber();
        //deposit
        boolean isExecute = false;
        double amount = 2000;

        TransactionDTO transactionDTO =
                service.createTransaction(accountNumber, amount, new Date("2013/01/15"), new Date("2013/1/17"), false, true);
        List<TransactionDTO> list2=service.getListTransactionsHistory(accountNumber,true);
        transactionDTO.setInterestDate(new Date("2013/1/18"));
        service.executeTransaction(transactionDTO, new Date("2013/1/17"));


         transactionDTO =
                 service.createTransaction(accountNumber, amount, new Date("2013/02/20"), new Date("2013/21/21"), false, true);
        transactionDTO.setInterestDate(new Date("2013/2/21"));
        service.executeTransaction(transactionDTO, new Date("2013/2/22"));

        transactionDTO =
                service.createTransaction(accountNumber, amount, new Date("2013/02/22"), new Date("2013/21/22"), false, false);
        transactionDTO.setInterestDate(new Date("2013/2/23"));
        service.executeTransaction(transactionDTO, new Date("2013/2/24"));

        Date checkDate = new Date("2013/3/31");
        service.accrueInterest(accountNumber,checkDate  );
        SavingsAccountDTO account = service.getAccount(accountNumber);
        assertEquals(3064.931, account.getPrincipal(), 0.001);
        List<TransactionDTO> transactionExecuted = service.getListTransactionsHistory(accountNumber,true);
        assertEquals(5,transactionExecuted.size());
        assertTrue(transactionExecuted.get(4).getActualExecutionDate().equals(checkDate));
        assertTrue(transactionExecuted.get(4).getInterestDate().equals(checkDate));
    }

    @Test
    public void secondTimeAccrueInterestWithNoTransactionInTheLastInterestPeriod() {
        String accountNumber = createAccount().getAccountNumber();
        //deposit
        boolean isExecute = false;
        double amount = 2000;

        TransactionDTO transactionDTO =
                service.createTransaction(accountNumber, amount, new Date("2013/01/15"), new Date("2013/1/17"), false, true);
        List<TransactionDTO> list2=service.getListTransactionsHistory(accountNumber,true);
        transactionDTO.setInterestDate(new Date("2013/1/18"));
        service.executeTransaction(transactionDTO, new Date("2013/1/17"));


        service.accrueInterest(accountNumber,  new Date("2013/3/31"));
        SavingsAccountDTO account = service.getAccount(accountNumber);
        assertEquals(3063.8356, account.getPrincipal(), 0.001);

        service.accrueInterest(accountNumber, new Date("2013/12/31"));
        account = service.getAccount(accountNumber);
        assertEquals(3293.833, account.getPrincipal(), 0.001);
     }

    @Ignore
    public void secondTimeAccrueInterestWithTransactionsInTheLastInterestPeriod() {
        String accountNumber = createAccount().getAccountNumber();
        //deposit
        boolean isExecute = false;
        double amount = 2000;

        TransactionDTO transactionDTO =
                service.createTransaction(accountNumber, amount, new Date("2013/01/15"), new Date("2013/1/17"), false, true);
        transactionDTO.setInterestDate(new Date("2013/1/18"));
        service.executeTransaction(transactionDTO, new Date("2013/1/17"));


        service.accrueInterest(accountNumber,  new Date("2013/3/31"));
        SavingsAccountDTO account = service.getAccount(accountNumber);
        assertEquals(3063.8356, account.getPrincipal(), 0.001);

         transactionDTO =
                service.createTransaction(accountNumber, amount, new Date("2013/04/15"), new Date("2013/4/17"), false, true);
        transactionDTO.setInterestDate(new Date("2013/4/18"));
        service.executeTransaction(transactionDTO, new Date("2013/4/18"));


        service.accrueInterest(accountNumber, new Date("2013/12/31"));
        account = service.getAccount(accountNumber);
        assertEquals(5434.654, account.getPrincipal(), 0.001);

    }
    //test earned but not accrued interest
    @Test
    public void testEarnedBNAInterest() {
        //create account
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        Date checkDate = new Date("2013/01/10");
        Double amount = 1000.0;
        boolean isExecute = true;
        boolean isDeposit = true;
        Double earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(2.465, earnedBNA, 0.001);

        //test with deposit
        Date transactionCreateDate = new Date("2013/1/19");
        amount = 1000.0;
        Date actualExecutionDate = new Date("2013/1/20");
        Date interestDate = new Date("2013/1/19");
        TransactionDTO transactionDTO = service.createTransaction(accountNumber, amount, transactionCreateDate, new Date(), isExecute, isDeposit);
        //update interest date
        transactionDTO.setInterestDate(interestDate);
        service.executeTransaction(transactionDTO, actualExecutionDate);
        checkDate = new Date("2013/01/20");
        earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(5.479, earnedBNA, 0.001);

        //test withdraw
        Date withdrawDate = new Date("2013/1/19");
        isExecute = true;
        amount = 1000.0;
        transactionDTO = service.createTransaction(accountNumber, amount, withdrawDate, new Date(), isExecute, !isDeposit);
        transactionDTO.setInterestDate(new Date("2013/1/19"));
        service.executeTransaction(transactionDTO, actualExecutionDate);
        checkDate = new Date("2013/01/30");
        earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(7.945, earnedBNA, 0.001);

    }

    @Test
    public void testEarnedBNAInterestWithTransactionNotExecute() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        Date checkDate = new Date("2013/01/10");
        Double amount = 1000.0;
        boolean isExecute = true;
        boolean isDeposit = true;

        Double earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(2.465, earnedBNA, 0.001);

        Date depositDate = new Date("2013/01/10");
        isExecute = false;
        TransactionDTO transactionDTO = service.createTransaction(accountNumber, amount, depositDate, new Date(), isExecute, isDeposit);
        earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(2.465, earnedBNA, 0.001);

    }

    @Test
    public void testEarnedBNAInterestWithCheckDateSmallerInterestDate() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        Date checkDate = new Date("2013/01/15");
        Double amount = 1000.0;
        boolean isExecute = true;
        boolean isDeposit = true;
        Double earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(3.8356, earnedBNA, 0.001);

        //test with deposit
        Date transactionCreateDate = new Date("2013/1/19");
        amount = 1000.0;
        Date actualExecutionDate = new Date("2013/1/20");
        Date interestDate = new Date("2013/1/19");
        TransactionDTO transactionDTO = service.createTransaction(accountNumber, amount, transactionCreateDate, new Date(), isExecute, isDeposit);
        //update interest date
        transactionDTO.setInterestDate(interestDate);
        service.executeTransaction(transactionDTO, actualExecutionDate);

        checkDate = new Date("2013/01/15");
        earnedBNA = service.calculatorEarnedBNAInterest(account, account.getStartDate(), checkDate);
        assertEquals(3.8356, earnedBNA, 0.001);

    }

    @Test
    public void daysBetween2Dates() {
        Date startDate = new Date("2013/01/01");
        Date endDate = new Date("2013/01/25");
        long numberDay = service.daysBetween2Dates(startDate, endDate);
        assertEquals(24, numberDay);

        startDate = new Date("2013/01/01");
        endDate = new Date("2013/02/25");
        numberDay = service.daysBetween2Dates(startDate, endDate);
        assertEquals(55, numberDay);
    }


    @Test
    public void testCreateOrder() {
        boolean isApprove = false;
        String accountNumber = createAccount().getAccountNumber();
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date(), new Date(), isApprove, typeOfFrequency);

        assertEquals(1, service.getOrder(accountNumber, orderDTO.getId()).getId());
        assertEquals(2000, service.getOrder(accountNumber, orderDTO.getId()).getAmount(), 0.001);


        SavingsAccountDTO accountSecond = createAccount();
        String accountSecondNumber = accountSecond.getAccountNumber();
        OrderDTO orderDTOSecond = createOrderDTO(accountSecondNumber, new Date(), new Date(), isApprove, typeOfFrequency);

        assertEquals(2, service.getOrder(accountSecondNumber, orderDTOSecond.getId()).getId());
        assertEquals(accountSecondNumber, service.getOrder(accountSecondNumber, orderDTOSecond.getId()).getAccountNumber());
    }


    @Test
    public void testUpdateOrder() {
        boolean isApprove = true;
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date(), new Date(), isApprove, typeOfFrequency);

        assertEquals(true, service.getOrder(accountNumber, orderDTO.getId()).getApproved());
        Date updateDate = new Date("2013/06/03");
        orderDTO.setApproved(false);
        service.updateOrder(orderDTO);
        service.checkDepositRecurring(account, orderDTO, updateDate);
        assertEquals(false, service.getOrder(accountNumber, orderDTO.getId()).getApproved());

    }

   @Test
   public void testDepositOrderThatTooEarlyOccurredTransaction(){
       String accountNumber =  createAccount().getAccountNumber();

       boolean isDeposit = true;
       OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
       OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/10"), true, typeOfFrequency);
       Date checkDate = new Date("2013/03/05");
       SavingsAccountDTO account=service.getAccount(accountNumber);
       service.checkDepositRecurring(account, orderDTO, checkDate);
       List<TransactionDTO> transactionDTOList=service.getListTransactionsHistory(accountNumber);
       assertEquals(1,transactionDTOList.size());

       checkDate=new Date("2013/03/11");
       service.checkDepositRecurring(account,orderDTO,checkDate);
       transactionDTOList=service.getListTransactionsHistory(accountNumber);
       assertEquals(2,transactionDTOList.size());
   }
    @Test
     public void testDisApproveOneOrder(){
        String accountNumber =  createAccount().getAccountNumber();
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/10"),
                null, OrderDTO.Frequency.MONTHLY);
        service.disApproveOrder(orderDTO);
        OrderDTO orderGetFromDB=service.getOrder(accountNumber,orderDTO.getId());
        assertFalse(orderGetFromDB.getApproved());
    }
    @Test
    public void testApproveOneOrder(){
        String accountNumber =  createAccount().getAccountNumber();
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/10"),
                null, OrderDTO.Frequency.MONTHLY);
        service.approveOrder(orderDTO);

        OrderDTO orderGetFromDB=service.getOrder(accountNumber,orderDTO.getId());
        assertTrue(orderGetFromDB.getApproved());
    }
    @Test
    public void getAllPendingOrders(){
        String accountNumber1 =  createAccount().getAccountNumber();
        String accountNumber2=  createAccount().getAccountNumber();
        OrderDTO orderDTO1 = createOrderDTO(accountNumber1, new Date("2013/03/02"), new Date("2013/03/10"),
                null, OrderDTO.Frequency.MONTHLY);
        OrderDTO orderDTO2 = createOrderDTO(accountNumber2, new Date("2013/03/3"), new Date("2013/03/15"),
                null, OrderDTO.Frequency.MONTHLY);

        assertEquals(2,service.getAllPendingOrders().size());
    }
    @Test
    public void testGetAllOrdersThatShouldHaveCreatedTransactionByCheckDate(){
        String accountNumber1 =  createAccount().getAccountNumber();
        String accountNumber2 =  createAccount().getAccountNumber();
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        OrderDTO orderDTO1 = createOrderDTO(accountNumber1, new Date("2013/03/02"), new Date("2013/03/10"), false, typeOfFrequency);
        service.approveOrder(orderDTO1);
        OrderDTO orderDTO2 = createOrderDTO(accountNumber2, new Date("2013/03/02"), new Date("2013/03/10"), false, typeOfFrequency);
        service.approveOrder(orderDTO2);
        Date checkDate=new Date("2013/03/07");
        assertEquals(2,service.getAllOrderShouldHaveCreatedTransactionByCheckDate(checkDate).size());
    }
    @Test
    public void testCancelOneOrder(){
        String accountNumber =  createAccount().getAccountNumber();
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/10"), null, typeOfFrequency);
        service.cancelOrder(orderDTO);
        OrderDTO orderFromDB=service.getOrder(accountNumber,orderDTO.getId());
        assertTrue(orderFromDB.isCanceled());
    }
    @Test
    public void testGetAllOrderThatApprovedAndNotExpired(){
        String accountNumber =  createAccount().getAccountNumber();
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/10"), null, typeOfFrequency);
        service.approveOrder(orderDTO);
        orderDTO.setNumOfTimesInTotal(2);
        SavingsAccountDTO account=service.getAccount(accountNumber);
        service.checkDepositRecurring(account,orderDTO,new Date("2013/3/11"));
        Date checkDate=new Date("2013/3/12");
        assertEquals(1,service.getAllOrderThatApprovedAndNotExpired(checkDate).size());


    }
    @Test
    public void testDepositOrderByRecurringNotApproved() {

        boolean isDeposit = true;
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        OrderDTO orderDTO = createOrderDTO(accountNumber, new Date("2013/03/02"), new Date("2013/03/03"), false, typeOfFrequency);
        List<TransactionDTO> transactionDTOList = service.getListTransactionsHistory(accountNumber);
        assertEquals(30, service.getAllOrder(accountNumber).get(0).getFrequency());
        assertEquals(2000, service.getAllOrder(accountNumber).get(0).getAmount(), 0.001);
        assertEquals(1, transactionDTOList.size());
        //order not approve
        Date checkDate = new Date("2013/05/02");
        service.checkDepositRecurring(account, orderDTO, checkDate);
        transactionDTOList = service.getListTransactionsHistory(accountNumber);
        //haven't any transaction from this order
        assertEquals(30, service.getAllOrder(accountNumber).get(0).getFrequency());
        List<TransactionDTO> list = service.getListTransactionsHistory(accountNumber, isDeposit);
        assertEquals(1, list.size());
        assertEquals(1, transactionDTOList.size());

    }


    private OrderDTO createOrderForMonthlyTransactions(SavingsAccountDTO account,
                                                       String accountNumber, boolean isExecute) {
        boolean isDeposit = true;
        boolean isApprove = true;

        OrderDTO.Frequency typeOfFrequency= OrderDTO.Frequency.MONTHLY;

        Date onDate = new Date("2013/02/01");
        Date dateRecurring = new Date("2013/02/02");
        OrderDTO orderDTO = createOrderDTO(accountNumber, onDate, dateRecurring, isApprove, typeOfFrequency);
        return orderDTO;
    }

    @Test
    public void testOrderForMonthlyTransactionsUntilCanceled() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        Boolean isExecute= false;
        OrderDTO orderDTO = createOrderForMonthlyTransactions(account, accountNumber, isExecute);

        Date checkDate = new Date("2013/02/10");
        service.checkDepositRecurring(account, orderDTO, checkDate);
        List<TransactionDTO> list = service.getListTransactionsHistory(accountNumber, isExecute);
        assertEquals(1, list.size());

        //have a deposit transaction
        checkDate = new Date("2013/03/04");
        service.checkDepositRecurring(account, orderDTO, checkDate);
        list = service.getListTransactionsHistory(accountNumber, isExecute);
        assertEquals(2, list.size());
        assertEquals(2000, list.get(1).getAmount(), 0.001);

    }
    @Test
    public void testOrderForMonthlyTransactionsWithPredefinedNumberOfTransactions() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        Boolean isExecute= false;
        OrderDTO orderDTO = createOrderForMonthlyTransactions(account, accountNumber, isExecute);

        orderDTO.setNumOfTimesInTotal(1);
        Date checkDate = new Date("2013/02/03");
        service.checkDepositRecurring(account,orderDTO,checkDate);

        List<TransactionDTO> list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(1,list.size());

        checkDate = new Date("2013/04/10");
        service.checkDepositRecurring(account,orderDTO,checkDate);
        list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(1,list.size());

    }
    @Test
    public void testDepositOrderByRecurringMonthlyWithTimes() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        Date createDate = new Date("2013/01/02");
        Date dateRecurring = new Date("2013/02/02");
        boolean  isApproved=true;
        Boolean isExecute= false;
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;

        OrderDTO orderDTO = createOrderDTO(accountNumber, createDate,dateRecurring,isApproved,typeOfFrequency);
        orderDTO.setNumOfTimesInTotal(3);

        // a transaction occurred at  2013/02/02
        Date checkDate = new Date("2013/02/03");
        service.checkDepositRecurring(account,orderDTO,checkDate);

        // a transaction occurred at  2013/03/04
        checkDate = new Date("2013/03/10");
        service.checkDepositRecurring(account,orderDTO,checkDate);
        List<TransactionDTO> list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(2,list.size());

        // a transaction occurred at  2013/04/03
        checkDate = new Date("2013/04/10");
        service.checkDepositRecurring(account,orderDTO,checkDate);
         list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(3,list.size());

        checkDate = new Date("2013/05/10");
        service.checkDepositRecurring(account,orderDTO,checkDate);
        list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(3,list.size());

    }
    @Test
    public void testDepositOrderByRecurringWeeklyWithOneTime() {
        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();

        Date createDate = new Date("2013/01/02");
        Date dateRecurring = new Date("2013/01/09");
        boolean  isApproved=true;
        Boolean isExecute= false;
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.WEEKLY;

        OrderDTO orderDTO = createOrderDTO(accountNumber, createDate,dateRecurring,isApproved,typeOfFrequency);
        orderDTO.setNumOfTimesInTotal(1);
        Date checkDate = new Date("2013/01/09");
        service.checkDepositRecurring(account,orderDTO,checkDate);

        List<TransactionDTO> list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(1,list.size());

        checkDate = new Date("2013/01/16");
        service.checkDepositRecurring(account,orderDTO,checkDate);
        list=service.getListTransactionsHistory(accountNumber,isExecute)    ;
        assertEquals(1,list.size());

    }
    @Test
    public void testOrderByRecurringMonthlyWithUntilCancel() {
        boolean isDeposit = true;
        boolean isApprove = true;
        Boolean isExecute= false;
        OrderDTO.Frequency typeOfFrequency=OrderDTO.Frequency.MONTHLY;

        SavingsAccountDTO account = createAccount();
        String accountNumber = account.getAccountNumber();
        Date onDate = new Date("2013/02/01");
        Date dateRecurring = new Date("2013/02/02");
        OrderDTO orderDTO = createOrderDTO(accountNumber, onDate, dateRecurring, isApprove, typeOfFrequency);
        orderDTO.setNumOfTimesInTotal(2);

        //a transaction occurred at 2013/02/02
        Date checkDate = new Date("2013/02/10");
        service.checkDepositRecurring(account, orderDTO, checkDate);

        //user set UntilCancel->haven't any transaction
        orderDTO.setValidUntilCancel(true);
        service.updateOrder(orderDTO);
        checkDate = new Date("2013/04/05");
        service.checkDepositRecurring(account, orderDTO, checkDate);

        List<TransactionDTO> list = service.getListTransactionsHistory(accountNumber, isExecute);
        assertEquals(1, list.size());

    }

//    @Test
//    public void testDepositByCheck() {
//
//        String accountNumber = createAccount().getAccountNumber();
//        CheckDepositDTO checkDepositDTO = new CheckDepositDTO();
//       // checkDepositDTO.setAmount(1000);
//        checkDepositDTO.setAccountTo(accountNumber);
//        checkDepositDTO.setDateDeposit(new Date("2013/05/28"));
//        /**
//         * deposit for owner saving
//         *
//         * accountNumber ->
//         * account ->owner saving
//         * check of account number
//         */
//        service.addCheck(accountNumber, checkDepositDTO);
//        assertEquals(1, service.getAllCheckOrder(accountNumber).size());
//        List<CheckDepositDTO> list
//        assertEquals(1000, service.getAllCheckOrder(accountNumber).get(0).getAmount(), 0.001);
//        assertEquals(accountNumber, service.getAllCheckOrder(accountNumber).get(0).getAccountTo());
//        assertEquals(2000, account.getBalance(), 0.001);
//        Date checkDepositByDate = service.getListTransactionsHistory(accountNumber, true).get(1).getDate();
//        assertEquals(new Date("2013/05/28"), checkDepositByDate);
//
//        /**
//         * deposit for other saving
//         *
//         * accountNumber ->
//         * account ->other saving
//         * check of account number
//         */
//        SavingsAccountDTO accountSecond = createAccount();
//        String accountNumberSecond = accountSecond.getAccountNumber();
//
//        checkDepositDTO = new CheckDepositDTO();
//        checkDepositDTO.setAmount(1500);
//        checkDepositDTO.setAccountTo(accountNumberSecond);
//        checkDepositDTO.setDateDeposit(new Date("2013/05/28"));
//        service.addCheck(accountNumber, accountSecond, checkDepositDTO);
//
//        assertEquals(2, service.getAllCheckOrder(accountNumber).size());
//        assertEquals(1500, service.getAllCheckOrder(accountNumber).get(1).getAmount(), 0.001);
//        assertEquals(accountNumberSecond, service.getAllCheckOrder(accountNumber).get(1).getAccountTo());
//        assertEquals(2500, accountSecond.getBalance(), 0.001);
//
//    }



}
