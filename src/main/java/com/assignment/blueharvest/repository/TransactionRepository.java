package com.assignment.blueharvest.repository;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing transactions.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_Customer(Customer customer);
}