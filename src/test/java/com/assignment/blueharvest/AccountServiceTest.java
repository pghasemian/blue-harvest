package com.assignment.blueharvest;

import com.assignment.blueharvest.dto.CustomerDTO;
import com.assignment.blueharvest.exception.CustomerNotFoundException;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import com.assignment.blueharvest.repository.TransactionRepository;
import com.assignment.blueharvest.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link AccountService}.
 * <p>
 * This class contains unit tests for the methods in the {@link AccountService},
 * including creating accounts and retrieving customer account information. The tests use
 * Mockito to mock the repository dependencies and verify service behavior.
 */
@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Test method to verify account creation for an existing customer.
     * <p>
     * This test mocks the {@link CustomerRepository} to return a valid customer and checks
     * if the account created has the correct balance. The test verifies that the account is
     * not null and that the balance is set correctly.
     */
    @Test
    public void testCreateAccount() {
        Customer customer = new Customer(1L, "Parisa", "Ghasemian");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Account account = accountService.createAccount(1L, 85.0);
        assertEquals(85.0, account.getBalance());
        assertNotNull(account);
    }

    /**
     * Test method to handle the scenario when account creation is attempted for a customer
     * that does not exist.
     * <p>
     * This test ensures that a {@link CustomerNotFoundException} is thrown when trying to create
     * an account for a non-existent customer, and verifies the exception message.
     */
    @Test
    public void testCreateAcc_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () ->
                accountService.createAccount(1L, 85.0));

        assertEquals("Customer with ID 1 not found.", exception.getMessage());
    }

    /**
     * Test method to verify the retrieval of customer account information.
     * <p>
     * This test mocks the necessary repositories to return a valid customer and their accounts.
     * It verifies that the returned {@link CustomerDTO} contains the correct customer details
     * and balance and checks that the transactions list is empty.
     */
    @Test
    public void testGetCustomerAccInfo() {
        Customer customer = new Customer(1L, "Parisa", "Ghasemian");
        Account account = new Account(1L, 85.0, customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepository.findByCustomer(customer)).thenReturn(List.of(account));
        when(transactionRepository.findByAccountCustomer(customer)).thenReturn(List.of());

        CustomerDTO accInfo = accountService.getCustomerAccountInfo(1L);

        assertNotNull(accInfo);
        assertEquals("Parisa", accInfo.getFirstName());
        assertEquals("Ghasemian", accInfo.getSurName());
        assertEquals(85.0, accInfo.getBalance());
        assertTrue(accInfo.getTransactions().isEmpty());
    }

    /**
     * Test method to handle the scenario when account information retrieval is attempted
     * for a customer that does not exist.
     * <p>
     * This test ensures that a {@link CustomerNotFoundException} is thrown when trying to retrieve
     * account information for a non-existent customer, and verifies the exception message.
     */
    @Test
    public void testGetCustomerAccInfo_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () ->
                accountService.getCustomerAccountInfo(1L));

        assertEquals("Customer with ID 1 not found.", exception.getMessage());
    }
}
