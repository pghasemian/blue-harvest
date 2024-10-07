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
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    /**
     * Creates a new account for the specified customer.
     *
     * @param customerId    the ID of the customer.
     * @param initialCredit the initial credit for the new account.
     * @return the created account.
     */
    @Transactional
    public Account createAccount(Long customerId, Double initialCredit) {
        Customer newCustomer = new Customer(customerId, "name", "surname");
        customerRepository.save(newCustomer);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found."));

        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(initialCredit);
        accountRepository.save(account);

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
    public CustomerDTO getCustomerAccountInfo(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found."));

        List<Account> accounts = accountRepository.findByCustomer(customer);
        Double balance = accounts.stream().mapToDouble(Account::getBalance).sum();
        List<Transaction> transactions = transactionService.getTransactionsByCustomer(customer);

        return new CustomerDTO(customer.getFirstName(), customer.getSurName(), balance, transactions);
    }
}
