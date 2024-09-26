package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.exceptions.ResourceNotFoundException;
import com.example.customerservic.repositories.CustomerRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    private CustomerRepository mockCustomerRepository;
    private MediaInteractionsService mockMediaInteractionsService;
    private Logger mockLogger;
    private CustomerService customerService;

    private Customer customer;
    private List<MediaInteractions> mediaInteractionsList;

    @BeforeEach
    void setUp() {
        mockCustomerRepository = mock(CustomerRepository.class);
        mockMediaInteractionsService = mock(MediaInteractionsService.class);
        mockLogger = mock(Logger.class);
        customerService = new CustomerService();
        customerService.setCustomerRepository(mockCustomerRepository);
        customerService.setMediaInteractionsService(mockMediaInteractionsService);
        customerService.setLogger(mockLogger);

        customer = new Customer();
        customer.setCustomerId(1);
        customer.setUserName("Test User");
        customer.setEmailAdress("Test@Example.com");

        mediaInteractionsList = new ArrayList<>();
        mediaInteractionsList.add(new MediaInteractions());
        customer.setMediaInteractions(mediaInteractionsList);
    }

    @Test
    void findCustomerById_ShouldFindCustomer() {
        int customerId = 1;
        List<MediaInteractions> list = new ArrayList<>();
        list.add(new MediaInteractions());
        customer.setMediaInteractions(list);
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> fundCustomer = customerService.findCustomerById(customerId);

        assertEquals(1, fundCustomer.get().getMediaInteractions().size());
    }

    @Test
    void TestFindCustomerByIdIfCustomerExists() {
        int customerId = 1;
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.of(customer));

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
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.empty());

        Optional<Customer> foundCustomer = customerService.findCustomerById(customerId);

        assertTrue(foundCustomer.isEmpty());
    }

    @Test
    void addCustomer_ShouldAddCustomer() {
        customerService.addCustomer(customer);
        verify(mockCustomerRepository).save(customer);
        verify(mockLogger).log(eq(Level.WARN), contains("New customer created with id:"));
    }

    @Test
    void addCustomer_shouldThrowExceptionIfUsernameExists() {
        when(mockCustomerRepository.existsByUserName(customer.getUserName())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.addCustomer(customer);
        });

        assertEquals("Customer with username Test User already exists", exception.getMessage());

        verify(mockCustomerRepository, never()).save(any(Customer.class));
        verify(mockLogger, never()).log(any(Level.class), anyString());
    }

    @Test
    void addCustomer_shouldThrowExceptionIfEmailAddressExists() {
        when(mockCustomerRepository.existsByUserName(customer.getUserName())).thenReturn(false);
        when(mockCustomerRepository.existsByEmailAdress(customer.getEmailAdress())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.addCustomer(customer);
        });

        assertEquals("Customer with email Test@Example.com already exists", exception.getMessage());

        verify(mockCustomerRepository, never()).save(any(Customer.class));
        verify(mockLogger, never()).log(any(Level.class), anyString());
    }

    @Test
    void deleteCustomerById_ShouldDeleteCustomer() {
        when(mockCustomerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));
        customerService.deleteCustomerById(1);
        verify(mockCustomerRepository).delete(customer);
    }

    @Test
    void deleteCustomerById_ThrowExceptionWhenCustomerNotFound() {
        when(mockCustomerRepository.findById(1)).thenReturn(Optional.empty());
        String expectation = "Customer with id '1' was not found";

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.deleteCustomerById(1);
        });
        assertEquals(expectation, exception.getMessage());
    }

    @Test
    void TestUpdateCustomer_ThrowExceptionWhenCustomerNotFound() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(1);
        updatedCustomer.setUserName("New Name");
        updatedCustomer.setEmailAdress("New Email Address");

        when(mockCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.empty());
        String exception = "Customer with id '1' was not found";

        ResourceNotFoundException exception1 = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.updateCustomer(customer.getCustomerId(), updatedCustomer);
        });

        assertEquals(exception, exception1.getMessage());
    }

    @Test
    void TestUpdateCustomerSuccess() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setUserName("New Name");
        updatedCustomer.setEmailAdress("New Email Address");
        updatedCustomer.setCustomerId(1);
        List<MediaInteractions> list = new ArrayList<>();
        list.add(new MediaInteractions());
        updatedCustomer.setMediaInteractions(list);

        when(mockCustomerRepository.findById(1)).thenReturn(Optional.of(updatedCustomer));

        customerService.updateCustomer(1, updatedCustomer);

        verify(mockCustomerRepository).save(updatedCustomer);
        verify(mockLogger).log(eq(Level.WARN), contains("Updated customer with id:"));
    }

    @Test
    void TestUpdate_ShouldAddMediaInteractionIfListOfUpdatedCustomerIsBigger() {
        MediaInteractions mediaInteractions = new MediaInteractions();
        Customer updatedCustomer = new Customer();
        updatedCustomer.setUserName("New Name");
        updatedCustomer.setEmailAdress("New Email Address");
        updatedCustomer.setCustomerId(1);

        List<MediaInteractions> list = new ArrayList<>();
        List<MediaInteractions> list2 = new ArrayList<>();

        list2.add(new MediaInteractions());
        customer.setMediaInteractions(list2);

        list.add(new MediaInteractions());
        list.add(mediaInteractions);
        updatedCustomer.setMediaInteractions(list);

        when(mockCustomerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(mockMediaInteractionsService.addMediaInteraction(mediaInteractions)).thenReturn(mediaInteractions);
        customerService.updateCustomer(1, updatedCustomer);

        verify(mockMediaInteractionsService).addMediaInteraction(mediaInteractions);
        verify(mockLogger).log(eq(Level.WARN), contains("Updated customer with id:"));
    }
}