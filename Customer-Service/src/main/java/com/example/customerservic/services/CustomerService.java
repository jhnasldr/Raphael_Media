package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Optional<Customer> findCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }
}
