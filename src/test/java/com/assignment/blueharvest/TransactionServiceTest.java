package com.assignment.blueharvest;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.TransactionRepository;
import com.assignment.blueharvest.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link TransactionService}.
 * <p>
 * This class contains unit tests for the methods in the {@link TransactionService},
 * including creating transactions and retrieving customer transactions. The tests use
 * Mockito to mock the TransactionRepository dependency and verify service behavior.
 */
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository; // Mocked TransactionRepository for database operations

    @InjectMocks
    private TransactionService transactionService; // Service under test with injected mock

    /**
     * Setup method to initialize mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito annotations
    }

    /**
     * Test the createTransaction method to verify that a transaction is created
     * and saved correctly.
     */
    @Test
    public void testCreateTransaction() {
        // Create an account to associate with the transaction
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0); // Set an initial balance

        // Create a transaction object to be saved
        Transaction transaction = new Transaction();
        transaction.setAccount(account); // Associate the account with the transaction
        transaction.setAmount(50.0); // Set the transaction amount

        // Mock the save operation in the repository
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Call the method under test
        transactionService.createTransaction(account, 50.0);

        // Verify that the save method was called once with any Transaction object
        verify(transactionRepository).save(any(Transaction.class));
    }

    /**
     * Test the getTransactionsByCustomer method to verify that transactions
     * are retrieved correctly for a given customer.
     */
    @Test
    public void testGetTransactionsByCustomer() {
        // Create a customer for whom we want to retrieve transactions
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Parisa");
        customer.setSurName("Ghasemian");

        // Create an account associated with the customer
        Account account = new Account();
        account.setId(1L);
        account.setCustomer(customer);

        // Create two transactions for the account
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setAccount(account);
        transaction1.setAmount(50.0); // Amount for the first transaction

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setAccount(account);
        transaction2.setAmount(30.0); // Amount for the second transaction

        // Mock the findByAccountCustomer operation in the repository
        when(transactionRepository.findByAccountCustomer(customer))
                .thenReturn(List.of(transaction1, transaction2)); // Return a list of transactions

        // Call the method under test
        List<Transaction> transactions = transactionService.getTransactionsByCustomer(customer);

        // Assert that the returned list is not null and contains 2 transactions
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals(50.0, transactions.get(0).getAmount()); // Verify the amount of the first transaction
        assertEquals(30.0, transactions.get(1).getAmount()); // Verify the amount of the second transaction

        // Verify that the findByAccountCustomer method was called once with the specified customer
        verify(transactionRepository).findByAccountCustomer(customer);
    }
}
