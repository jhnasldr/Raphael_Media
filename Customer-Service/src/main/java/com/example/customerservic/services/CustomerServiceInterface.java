package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;

import java.util.Optional;

public interface CustomerServiceInterface {
    Optional<Customer> findCustomerById(int customerId);

    Customer addCustomer(Customer customer);

    void deleteCustomerById(int id);

    Customer updateCustomer(int customerId,Customer customer);
}
