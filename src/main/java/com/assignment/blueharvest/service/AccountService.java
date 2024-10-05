package com.assignment.blueharvest.service;

import com.assignment.blueharvest.dto.CustomerAccInfo;
import com.assignment.blueharvest.exception.CustomerNotFoundException;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    public Account createAccount(Long customerId, Double initialCredit) {
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

    public CustomerAccInfo getCustomerAccountInfo(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found."));

        List<Account> accounts = accountRepository.findByCustomer(customer);
        Double balance = accounts.stream().mapToDouble(Account::getBalance).sum();
        List<Transaction> transactions = transactionService.getTransactionsByCustomer(customer);

        return new CustomerAccInfo(customer.getFirstName(), customer.getSurName(), balance, transactions);
    }
}
