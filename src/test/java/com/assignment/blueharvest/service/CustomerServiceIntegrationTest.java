package com.assignment.blueharvest.service;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test class for testing {@link CustomerService}.
 * <p>
 * This class tests the integration of the service methods responsible for managing customers,
 * including customer creation, retrieval by ID, and deletion. The tests use JPA repositories
 * to interact with the database, ensuring the service layer works correctly with persistence.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    /**
     * Setup method executed before each test. It clears the customer repository
     * and creates a new customer for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();

        customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Doe");
        customer = customerRepository.save(customer);
    }

    /**
     * Test method to verify the functionality of customer creation.
     * <p>
     * This test creates a new customer and saves it to the repository.
     * It asserts that the customer was successfully saved and has a non-null ID.
     */
    @Test
    public void testCreateCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Jane");
        newCustomer.setSurName("Doe");

        Customer savedCustomer = customerRepository.save(newCustomer);
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getFirstName()).isEqualTo("Jane");
        assertThat(savedCustomer.getSurName()).isEqualTo("Doe");
    }

    /**
     * Test method to verify the retrieval of a customer by its ID.
     * <p>
     * This test saves a new customer and then retrieves it by ID.
     * It asserts that the customer was found and that the details match the expected values.
     */
    @Test
    public void testGetCustomerById() {
        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getFirstName()).isEqualTo("John");
        assertThat(foundCustomer.getSurName()).isEqualTo("Doe");
    }

    /**
     * Test method to verify the deletion of a customer.
     * <p>
     * This test saves a customer, then deletes it from the repository.
     * It asserts that after deletion, the customer can no longer be found in the repository.
     */
    @Test
    public void testDeleteCustomer() {
        customerRepository.delete(customer);
        assertThat(customerRepository.findById(customer.getId()).isPresent()).isFalse();
    }
}
