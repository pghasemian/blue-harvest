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
public interface TransactionRepository extends
        JpaRepository<Transaction, Long> {

    /**
     * Retrieves a list of transactions associated with a specific customer.
     *
     * @param customer the customer whose transactions are to be retrieved
     * @return a list of transactions belonging to the specified customer
     */
    List<Transaction> findByAccountCustomer(Customer customer);
}
