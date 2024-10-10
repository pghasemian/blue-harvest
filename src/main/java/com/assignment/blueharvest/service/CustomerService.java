package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing customer-related operations.
 * This class is final to prevent subclassing.
 */
@Service
@RequiredArgsConstructor
public final class CustomerService {

    /**
     * Repository for accessing and persisting {@link Customer} entities.
     */
    private final CustomerRepository customerRepository;

    /**
     * Saves a new customer to the repository with the provided
     * first name and surname.
     *
     * @param firstName the first name of the new customer
     * @param surName   the surname of the new customer
     * @return the newly created {@link Customer} object
     */
    public Customer saveCustomer(final String firstName,
                                 final String surName) {
        Customer newCustomer = new Customer();
        newCustomer.setSurName(surName);
        newCustomer.setFirstName(firstName);
        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
