package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
        customerRepository.deleteAll();

        customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Doe");
        customer = customerRepository.save(customer);

        Account account = new Account();
        account.setBalance(100.0);
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setBalance(100.0);
        account.setCustomer(customer);

        Account savedAccount = accountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
    }

    @Test
    public void testGetAccountById() {
        Account account = new Account();
        account.setBalance(100.0);
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);

        Account foundAccount = accountRepository.findById(savedAccount.getId()).orElse(null);
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getBalance()).isEqualTo(100.0);
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account();
        account.setBalance(100.0);
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);

        accountRepository.delete(savedAccount);
        assertThat(accountRepository.findById(savedAccount.getId()).isPresent()).isFalse();
    }
}
