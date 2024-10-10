package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.dto.AccountDTO;
import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.repository.AccountRepository;
import com.assignment.blueharvest.repository.CustomerRepository;
import com.assignment.blueharvest.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration test class for {@link AccountController}.
 * <p>
 * This class tests the integration of the account creation and account information retrieval endpoints
 * in the {@link AccountController} using MockMvc to simulate HTTP requests and responses.
 * It verifies that the REST API endpoints are functioning correctly, including the interactions with the
 * database through repositories and service layers.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper;

    /**
     * Setup method executed before each test. It clears the database repositories for
     * transactions, accounts, and customers to ensure a clean state before every test.
     */
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        transactionRepository.deleteAll(); // Ensure to delete transactions first
        accountRepository.deleteAll();
        customerRepository.deleteAll();
    }

    /**
     * Test method to verify the functionality of the account creation endpoint.
     * <p>
     * This test creates a new customer in the database and sends a POST request to create
     * an account for the customer. It checks if the response status is '201 Created' and
     * the message confirms successful account creation.
     *
     * @throws Exception if an error occurs during the mock request.
     */
    @Test
    public void testCreateAccount() throws Exception {
        // Create a new customer
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("Parisa");
        testCustomer.setSurName("Ghasemian");
        customerRepository.save(testCustomer);

        // Create a new account request
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCustomerId(testCustomer.getId());
        accountDTO.setInitialCredit(100.0);

        // Perform POST request to create an account
        mockMvc.perform(post("/api/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Account created successfully"));
    }

}