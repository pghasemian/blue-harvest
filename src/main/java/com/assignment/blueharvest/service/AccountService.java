package com.assignment.blueharvest.service;

import com.assignment.blueharvest.dto.CustomerDTO;
import com.assignment.blueharvest.exception.CustomerNotFoundException;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for account-related operations.
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    /**
     * Repository for account-related database operations.
     */
    private final AccountRepository accountRepository;

    /**
     * Repository for customer-related database operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Service for transaction-related operations.
     */
    private final TransactionService transactionService;

    /**
     * Creates a new account for the specified customer.
     *
     * @param customerId    the ID of the customer.
     * @param initialCredit the initial credit for the new account.
     * @return the created account.
     */
    @Transactional
    public Account createAccount(final Long customerId,
                                 final Double initialCredit) {
        // Retrieving the customer from the database using the provided ID.
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with ID " + customerId + " not found."));

        // Creating a new Account instance for the customer.
        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(initialCredit);
        accountRepository.save(account);

        // If initial credit is greater than zero, create a transaction for it.
        if (initialCredit > 0) {
            transactionService.createTransaction(account, initialCredit);
        }

        return account;
    }

    /**
     * Retrieves account information for a specific customer.
     *
     * @param customerId the ID of the customer.
     * @return DTO containing the customer's account information.
     */
    public CustomerDTO getCustomerAccountInfo(final Long customerId) {
        // Retrieving the customer from the database using the provided ID.
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer with ID " + customerId + " not found."));

        // Retrieving all accounts associated with the customer.
        List<Account> accounts = accountRepository.findByCustomer(customer);

        // Calculating the total balance of all accounts for the customer.
        Double balance =
                accounts.stream().mapToDouble(Account::getBalance).sum();

        // Retrieving transactions associated with the customer.
        List<Transaction> transactions =
                transactionService.getTransactionsByCustomer(customer);

        // Returning a DTO containing the customer's account information.
        return new CustomerDTO(customer.getFirstName(),
                customer.getSurName(), balance, transactions);
    }
}
