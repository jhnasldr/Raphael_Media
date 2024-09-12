package com.example.customerservic.services;

import com.example.customerservic.entities.Customer;
import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.exceptions.ResourceNotFoundException;
import com.example.customerservic.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

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

        if(customer.getUserName() != null){
            customerToUpdate.setUserName(customer.getUserName());
        }
        if(customer.getEmailAdress() != null){
            customerToUpdate.setEmailAdress(customer.getEmailAdress());
        }

        if(customer.getMediaInteractions() != null) {
            if (customer.getMediaInteractions().size() > customerToUpdate.getMediaInteractions().size()) {
                System.out.println("Jag inne i storlek");
                int count = 0;

                for (MediaInteractions m : customer.getMediaInteractions()) {
                    if (m.getMediaInteractionId() != customerToUpdate.getMediaInteractions().get(count).getMediaInteractionId()) {
                        mediaInteractionsService.addMediaInteraction(m);
                    }
                    count++;
                }
            }
            else {
                customerToUpdate.setMediaInteractions(customer.getMediaInteractions());
            }

        }

        //customerToUpdate.setMediaInteractions(customer.getMediaInteractions());
        customerRepository.save(customerToUpdate);
        return customerToUpdate;
    }


}
