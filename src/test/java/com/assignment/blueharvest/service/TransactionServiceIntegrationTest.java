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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionServiceIntegrationTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setUp() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();

        account = new Account();
        account.setBalance(100.0);
        account = accountRepository.save(account);
    }

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(250.0);
        transaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(transaction);
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getId()).isNotNull();
    }

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
