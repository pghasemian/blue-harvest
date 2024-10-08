package com.assignment.blueharvest;

import com.assignment.blueharvest.controller.AccountController;
import com.assignment.blueharvest.dto.AccountDTO;
import com.assignment.blueharvest.dto.CustomerDTO;
import com.assignment.blueharvest.exception.CustomerNotFoundException;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import com.assignment.blueharvest.repository.TransactionRepository;
import com.assignment.blueharvest.response.AccountResponse;
import com.assignment.blueharvest.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService mockAccountService;

    @BeforeEach
    public void setUp() {
        // No need to manually initialize mockAccountService
        // accountService is already autowired by Spring
    }


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

    /**
     * Test method to verify account creation via the {@link AccountController}.
     * <p>
     * This test mocks the {@link AccountService#createAccount(Long, Double)} method
     * and verifies that a successful account creation returns the correct response from the controller.
     */
    @Test
    public void testCreateAccountController_Success() {
        // Mocking AccountService behavior
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);

        // Correct use of matchers for all method arguments
        when(mockAccountService.createAccount(any(Long.class), any(Double.class))).thenReturn(account);

        // Create AccountDTO for the request
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialCredit(100.0);

        // Call the method in AccountController
        ResponseEntity<AccountResponse> responseEntity = accountController.createAccount(accountDTO);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", Objects.requireNonNull(responseEntity.getBody()).getStatus());
        assertEquals("Account created successfully", responseEntity.getBody().getMessage());
        assertEquals(100.0, responseEntity.getBody().getAccount().getBalance());
    }


    /**
     * Test method to verify the failure of account creation via the {@link AccountController}.
     * <p>
     * This test mocks the {@link AccountService#createAccount(Long, Double)} method to return
     * {@code null}, simulating an account creation failure, and verifies the response.
     */
    @Test
    public void testCreateAccountController_Failure() {
        // Mocking AccountService behavior to return null (failure case)
        when(mockAccountService.createAccount(any(Long.class), any(Double.class))).thenReturn(null);

        // Create AccountDTO for the request
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialCredit(100.0);

        // Call the method in AccountController
        ResponseEntity<AccountResponse> responseEntity = accountController.createAccount(accountDTO);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("failed", Objects.requireNonNull(responseEntity.getBody()).getStatus());
        assertEquals("Error creating account", responseEntity.getBody().getMessage());
    }

    /**
     * Test method to verify the retrieval of account information via the {@link AccountController}.
     * <p>
     * This test mocks the {@link AccountService#getCustomerAccountInfo(Long)} method and verifies
     * that a successful retrieval returns the correct customer information.
     */
    @Test
    public void testGetCustomerAccountInfoController_Success() {
        // Mocking AccountService behavior
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Parisa");
        customerDTO.setSurName("Ghasemian");
        customerDTO.setBalance(100.0);
        when(mockAccountService.getCustomerAccountInfo(any(Long.class))).thenReturn(customerDTO);

        // Call the method in AccountController
        ResponseEntity<CustomerDTO> responseEntity = accountController.getAccountInfo(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Parisa", Objects.requireNonNull(responseEntity.getBody()).getFirstName());
        assertEquals("Ghasemian", responseEntity.getBody().getSurName());
        assertEquals(100.0, responseEntity.getBody().getBalance());
    }
}
