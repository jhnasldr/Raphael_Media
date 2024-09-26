package com.example.customerservic.repositories;

import com.example.customerservic.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setUserName("testUser");
        testCustomer.setEmailAdress("testUser@test.com");
        customerRepository.save(testCustomer);
    }

    @Test
    void existsByUserName_shouldReturnTrueWhenUserNameExists() {
        boolean exists = customerRepository.existsByUserName("testUser");
        assertTrue(exists);

    }

    @Test
    void existsByUserName_shouldReturnFalseWhenUserNameDoesNotExist() {
        boolean exists = customerRepository.existsByUserName(null);
        assertFalse(exists);
    }

    @Test
    void existsByEmailAdress_shouldReturnTrueWhenEmailAdressExists() {
        boolean exists = customerRepository.existsByEmailAdress("testUser@test.com");
        assertTrue(exists);
    }

    @Test
    void existsByEmailAdress_shouldReturnFalseWhenEmailAdressDoesNotExist() {
        boolean exists = customerRepository.existsByEmailAdress(null);
        assertFalse(exists);
    }
}