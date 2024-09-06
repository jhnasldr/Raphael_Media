package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;

import java.util.Optional;

public interface CustomerServiceInterface {
    Optional<Customer> findCustomerById(int customerId);
}
