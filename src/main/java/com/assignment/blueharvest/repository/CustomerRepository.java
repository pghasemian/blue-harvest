package com.assignment.blueharvest.repository;

import com.assignment.blueharvest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing customers.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
