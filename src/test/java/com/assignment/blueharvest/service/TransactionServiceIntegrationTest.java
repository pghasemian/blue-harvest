package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test class for testing {@link TransactionService}.
 * <p>
 * This class tests the integration of the service methods responsible for managing transactions,
 * including transaction creation and retrieval by ID. The tests use JPA repositories to interact
 * with the database, ensuring the service layer functions correctly with persistence.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionServiceIntegrationTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    /**
     * Setup method executed before each test. It clears the transaction and account repositories
     * and creates a new account for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();

        account = new Account();
        account.setBalance(100.0);
        account = accountRepository.save(account);
    }

    /**
     * Test method to verify the functionality of transaction creation.
     * <p>
     * This test creates a new transaction associated with an account and saves it to the repository.
     * It asserts that the transaction was successfully saved and has a non-null ID.
     */
    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(250.0);
        transaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(transaction);
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getId()).isNotNull();
    }

    /**
     * Test method to verify the retrieval of a transaction by its ID.
     * <p>
     * This test saves a new transaction and then retrieves it by ID. It asserts that the transaction
     * was found and that the amount matches the expected value.
     */
    @Test
    public void testGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setAmount(250.0);
        transaction.setAccount(account);
        Transaction savedTransaction = transactionRepository.save(transaction);

        Transaction foundTransaction = transactionRepository.findById(savedTransaction.getId())
                .orElse(null);
        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getAmount()).isEqualTo(250.0);
    }

}
