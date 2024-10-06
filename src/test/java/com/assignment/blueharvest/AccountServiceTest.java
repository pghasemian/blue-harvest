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

    @Test
    public void testCreateAccount() {
        Customer customer = new Customer(1L, "Parisa", "Ghasemian");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Account account = accountService.createAccount(1L, 85.0);
        assertEquals(85.0, account.getBalance());
        assertNotNull(account);
    }

    @Test
    public void testCreateAcc_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            accountService.createAccount(1L, 85.0);
        });

        assertEquals("Customer with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testGetCustomerAccInfo() {
        Customer customer = new Customer(1L, "Parisa", "Ghasemian");
        Account account = new Account(1L, 85.0, customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepository.findByCustomer(customer)).thenReturn(List.of(account));
        when(transactionRepository.findByAccount_Customer(customer)).thenReturn(List.of());

        CustomerDTO accInfo = accountService.getCustomerAccountInfo(1L);

        assertNotNull(accInfo);
        assertEquals("Parisa", accInfo.getFirstName());
        assertEquals("Ghasemian", accInfo.getSurName());
        assertEquals(85.0, accInfo.getBalance());
        assertTrue(accInfo.getTransactions().isEmpty());
    }

    @Test
    public void testGetCustomerAccInfo_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            accountService.getCustomerAccountInfo(1L);
        });

        assertEquals("Customer with ID 1 not found.", exception.getMessage());
    }
}
