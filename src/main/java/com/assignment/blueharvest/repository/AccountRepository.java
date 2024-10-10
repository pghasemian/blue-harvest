package com.assignment.blueharvest.repository;

import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing accounts.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Retrieves a list of accounts associated with a specific customer.
     *
     * @param customer the customer whose accounts are to be retrieved
     * @return a list of accounts belonging to the specified customer
     */
    List<Account> findByCustomer(Customer customer);
}
