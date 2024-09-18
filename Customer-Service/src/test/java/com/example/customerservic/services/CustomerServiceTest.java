package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.exceptions.ResourceNotFoundException;
import com.example.customerservic.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private MediaInteractionsService mediaInteractionsService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setUserName("Test User");
        customer.setEmailAdress("Test@Example.com");
    }


    @Test
    void findCustomerById_ShouldFindCustomer(){
        //give
        int customerId = 1;
        List<MediaInteractions> list = new ArrayList<>();
        list.add(new MediaInteractions());
        customer.setMediaInteractions(list);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        //when
        Optional<Customer> fundCustomer = customerService.findCustomerById(customerId);


        //then
        assertEquals(1, fundCustomer.get().getMediaInteractions().size());

    }
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
    void addCustomer_shouldThrowExceptionIfUsernameExists() {

        when(customerRepository.existsByUserName(customer.getUserName())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.addCustomer(customer);
        });

        assertEquals("Customer with username Test User already exists", exception.getMessage());

        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void addCustomer_shouldThrowExceptionIfEmailAddressExists() {

        when(customerRepository.existsByUserName(customer.getUserName())).thenReturn(false);
        when(customerRepository.existsByEmailAdress(customer.getEmailAdress())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.addCustomer(customer);
        });

        assertEquals("Customer with email Test@Example.com already exists", exception.getMessage());

        verify(customerRepository, never()).save(any(Customer.class));
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

    @Test
    void TestUpdateCustomer_ThrowExceptionWhenCustomerNotFound() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(1);
        updatedCustomer.setUserName("New Name");
        updatedCustomer.setEmailAdress("New Email Address");

        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.empty());
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

        when(customerRepository.findById(1)).thenReturn(Optional.of(updatedCustomer));

        Customer result = customerService.updateCustomer(1, updatedCustomer);

        verify(customerRepository).save(updatedCustomer);
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



        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(mediaInteractionsService.addMediaInteraction(mediaInteractions)).thenReturn(mediaInteractions);

        Customer result = customerService.updateCustomer(1, updatedCustomer);

        //assertEquals("New Name", result.getUserName()
        verify(mediaInteractionsService).addMediaInteraction(mediaInteractions);
    }
}