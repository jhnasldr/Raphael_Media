package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.exceptions.ResourceNotFoundException;
import com.example.customerservic.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@Service
public class CustomerService implements CustomerServiceInterface {
    Logger logger = Logger.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaInteractionsService mediaInteractionsService;

    @Override
    public Optional<Customer> findCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByUserName(customer.getUserName())) {
            throw new RuntimeException("Customer with username " + customer.getUserName() + " already exists");
        }
        if (customerRepository.existsByEmailAdress(customer.getEmailAdress())) {
            throw new RuntimeException("Customer with email " + customer.getEmailAdress() + " already exists");
        }
        customerRepository.save(customer);
        logger.log(Level.WARN, "New customer created with id:" + customer.getCustomerId());
        return customer;
    }

    @Override
    public void deleteCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
        logger.log(Level.WARN, "Customer with id: " + id + " deleted");
    }

    @Override
    public Customer updateCustomer(int customerId, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        if (customer.getUserName() != null) {
            customerToUpdate.setUserName(customer.getUserName());
        }
        if (customer.getEmailAdress() != null) {
            customerToUpdate.setEmailAdress(customer.getEmailAdress());
        }
        if (customer.getMediaInteractions() != null) {
            if (customer.getMediaInteractions().size() > customerToUpdate.getMediaInteractions().size()) {
                for (int i = customerToUpdate.getMediaInteractions().size(); i < customer.getMediaInteractions().size(); i++) {
                    System.out.println("index " + i);
                    mediaInteractionsService.addMediaInteraction(customer.getMediaInteractions().get(i));
                }
            } else {
                customerToUpdate.setMediaInteractions(customer.getMediaInteractions());
            }
        }
        customerRepository.save(customerToUpdate);
        logger.log(Level.WARN,"Updated customer with id: "+customer);
        return customerToUpdate;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setMediaInteractionsService(MediaInteractionsService mediaInteractionsService) {
        this.mediaInteractionsService = mediaInteractionsService;
    }
}