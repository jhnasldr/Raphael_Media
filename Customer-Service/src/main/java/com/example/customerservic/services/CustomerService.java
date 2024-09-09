package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.exceptions.ResourceNotFoundException;
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

    @Override
    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomerById(int id) {
      Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
      customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomer(int customerId, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customerToUpdate.setUserName(customer.getUserName());
        customerToUpdate.setEmailAdress(customer.getEmailAdress());

        return customerRepository.save(customerToUpdate);
    }


}
