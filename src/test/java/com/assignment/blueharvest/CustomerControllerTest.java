package com.assignment.blueharvest;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.service.CustomerService;
import com.assignment.blueharvest.controller.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    private MockMvc mockMvc;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        CustomerController customerController = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testCreateCustomer() throws Exception {
        // Given
        String firstName = "Jane";
        String surName = "Doe";
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setSurName(surName);

        when(customerService.saveCustomer(anyString(), anyString())).thenReturn(newCustomer);

        // When & Then
        mockMvc.perform(post("/api/customers/create")
                        .param("firstName", firstName)
                        .param("surName", surName))
                .andExpect(status().isCreated());

        ArgumentCaptor<String> firstNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> surNameCaptor = ArgumentCaptor.forClass(String.class);
        verify(customerService, times(1)).saveCustomer(firstNameCaptor.capture(), surNameCaptor.capture());
        assertThat(firstNameCaptor.getValue()).isEqualTo(firstName);
        assertThat(surNameCaptor.getValue()).isEqualTo(surName);
    }

    @Test
    void testCreateCustomer_Error() throws Exception {
        // Given
        when(customerService.saveCustomer(anyString(), anyString())).thenReturn(null);

        // When & Then
        mockMvc.perform(post("/api/customers/create")
                        .param("firstName", "Jane")
                        .param("surName", "Doe"))
                .andExpect(status().isBadRequest());
    }
}
