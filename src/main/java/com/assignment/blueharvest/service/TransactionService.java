package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import com.assignment.blueharvest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Account account, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByCustomer(Customer customer) {
        return transactionRepository.findByAccount_Customer(customer);
    }
}

