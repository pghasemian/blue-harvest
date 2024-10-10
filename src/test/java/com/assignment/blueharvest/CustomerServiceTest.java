package com.assignment.blueharvest;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.CustomerRepository;
import com.assignment.blueharvest.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final CustomerService customerService = new CustomerService(customerRepository);

    @Test
    void testSaveCustomer() {
        // Given
        String firstName = "John";
        String surName = "Doe";
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setSurName(surName);

        // When
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);
        Customer savedCustomer = customerService.saveCustomer(firstName, surName);

        // Then
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(firstName, savedCustomer.getFirstName());
        assertEquals(surName, savedCustomer.getSurName());
    }
}
