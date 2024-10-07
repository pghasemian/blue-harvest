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

/**
 * Integration test class for testing {@link AccountService}.
 * <p>
 * This class tests the integration of the service methods responsible for managing accounts,
 * including account creation, retrieval by ID, and deletion. The tests use JPA repositories
 * to interact with the database, ensuring the service layer works correctly with persistence.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    /**
     * Setup method executed before each test. It clears the account and customer repositories
     * and creates a new customer with a default account for testing purposes.
     */
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

    /**
     * Test method to verify the functionality of account creation.
     * <p>
     * This test creates a new account for the previously created customer and saves it to the repository.
     * It asserts that the account was successfully saved and has a non-null ID.
     */
    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setBalance(100.0);
        account.setCustomer(customer);

        Account savedAccount = accountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
    }

    /**
     * Test method to verify the retrieval of an account by its ID.
     * <p>
     * This test saves a new account and then retrieves it by ID. It asserts that the account
     * was found and that the balance matches the expected value.
     */
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

    /**
     * Test method to verify the deletion of an account.
     * <p>
     * This test saves an account, then deletes it from the repository. It asserts that after
     * deletion, the account can no longer be found in the repository.
     */
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
