package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.exceptions.ResourceNotFoundException;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setUserName("Test User");
        customer.setEmailAdress("Test@Example.com");
    }



    //TODO eventuellt lägga till testning i FindCustomerById och kolla att mediaInteractionsListan kommer med rätt också
    @Test
    void TestFindCustomerByIdIfCustomerExists() {

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

    @Test
    void addCustomer_ShouldAddCustomer() {
        //given
        //when
        customerService.addCustomer(customer);
        //then
        verify(customerRepository).save(customer);
    }

    @Test
    void deleteCustomerById_ShouldDeleteCustomer() {
        //give
        when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));
        //when
        customerService.deleteCustomerById(1);
        //the
        verify(customerRepository).delete(customer);
    }

    @Test
    void deleteCustomerById_ThrowExceptionWhenCustomerNotFound() {
        //give
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        String expectation = "Customer with id '1' was not found";

        //when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.deleteCustomerById(1);
        });
        //then
        assertEquals(expectation, exception.getMessage());
    }
}