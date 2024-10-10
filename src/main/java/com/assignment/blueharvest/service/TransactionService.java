package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for transaction-related operations.
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    /**
     * Repository for transaction-related database operations.
     */
    private final TransactionRepository transactionRepository;

    /**
     * Creates a new transaction associated with a given account.
     *
     * @param account the account to associate the transaction with.
     * @param amount  the amount of the transaction.
     */
    public void createTransaction(final Account account, final Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    /**
     * Retrieves transactions for a specific customer.
     *
     * @param customer the customer for whom to retrieve transactions.
     * @return a list of transactions associated with the customer.
     */
    public List<Transaction>
    getTransactionsByCustomer(final Customer customer) {
        return transactionRepository.findByAccountCustomer(customer);
    }
}
