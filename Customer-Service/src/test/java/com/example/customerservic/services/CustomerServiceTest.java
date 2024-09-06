package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;


    @Test
    void TestFindCustomerByIdIfCustomerExists() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setUserName("Test User");
        customer.setEmailAdress("Test@Example.com");

        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.findCustomerById(customerId);

        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getCustomerId(), foundCustomer.get().getCustomerId());
        assertEquals("Test User", foundCustomer.get().getUserName());
        assertEquals("Test@Example.com", foundCustomer.get().getEmailAdress());

    }

    @Test
    void TestFindCustomerByIdIfCustomerDoesNotExist() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setUserName("Test User");
        customer.setEmailAdress("Test@Example.com");

        int customerId = 2;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Optional<Customer> foundCustomer = customerService.findCustomerById(customerId);

        assertTrue(foundCustomer.isEmpty());
    }
}